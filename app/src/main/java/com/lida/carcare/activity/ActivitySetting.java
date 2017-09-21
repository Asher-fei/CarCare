package com.lida.carcare.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TResult;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.updater.Version;
import com.lida.carcare.updater.VersionUpdate;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogChangePhone;
import com.lida.carcare.widget.DialogCheckCode;
import com.lida.carcare.widget.DialogChoosePicType;
import com.lida.carcare.widget.DialogIfSignOut;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.FileUtils;
import com.midian.base.util.ImageUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.dialog.PicChooserDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.annotations.Beta;

/**
 * 设置
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivitySetting extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.llChangePhone)
    LinearLayout llChangePhone;
    @BindView(R.id.tvChangePass)
    TextView tvChangePass;
    @BindView(R.id.tvZhuXiao)
    TextView tvZhuXiao;
    @BindView(R.id.tvUpdate)
    TextView tvUpdate;
    @BindView(R.id.tvAgreement)
    TextView tvAgreement;
    @BindView(R.id.tvSignOut)
    Button tvSignOut;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llChangeHead)
    LinearLayout llChangeHead;

    private Activity _activity;
    private AppContext ac;

    private Uri origUri;
    private Uri cropUri;
    private File protraitFile;
    private Bitmap protraitBitmap;
    private String protraitPath;
    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/midianbaselib/pic/";
    private final static int CROP = 200;
    private File mhead = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(com.bishilai.thirdpackage.R.color.colorPrimary));
            StatusBarUtil.setTranslucentForImageViewInFragment(_activity, 0, null);
        }
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        _activity = this;
        ac = (AppContext) getApplication();
        topbar.setTitle("设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        if(ac.getProperty("head_img").contains("upload")){
            ac.setImage(ivHead, Constant.BASE+ac.getProperty("head_img"));//服务器
        }else{
            ivHead.setImageBitmap(BitmapFactory.decodeFile(ac.getProperty("head_img")));
        }
        tvPhone.setText(ac.phone);
    }

    @OnClick({R.id.llChangeHead, R.id.llChangePhone, R.id.tvChangePass, R.id.tvZhuXiao, R.id.tvUpdate, R.id.tvAgreement, R.id.tvSignOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llChangeHead:
                takePhoto();
                break;
            case R.id.llChangePhone:
                new DialogChangePhone(_activity).show();
                break;
            case R.id.tvChangePass:
                new DialogCheckCode(_activity, "").show();
                break;
            case R.id.tvZhuXiao:
                UIHelper.jump(_activity, ActivityZhuXiao.class);
                break;
            case R.id.tvUpdate:

               // UIHelper.t(_activity, "当前版本为最新版本！");
                checkUpdateVersion();
                break;
            case R.id.tvAgreement:
                break;
            case R.id.tvSignOut:
                new DialogIfSignOut(_activity).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA:
                    startActionCrop(origUri);// 拍照后裁剪
                    break;
                case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                    startActionCrop(data.getData());// 选图后裁剪
                    break;
                case ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD:
                    // 获取头像缩略图
                    if (!TextUtils.isEmpty(protraitPath) && protraitFile.exists()) {
                        protraitBitmap = ImageUtils.loadImgThumbnail(protraitPath,
                                200, 200);
                        outputBitmap(protraitBitmap, protraitPath);
                    }
                    break;
            }
        }
    }

    public void outputBitmap(Bitmap bitmap, final String path) {
        mhead = new File(path);
        AppUtil.getCarApiClient(ac).uploadPersonalAvatar(ac.userId, path, new BaseApiCallback() {
            @Override
            public void onApiStart(String tag) {
                super.onApiStart(tag);
                showLoadingDlg();
                llChangeHead.setClickable(false);
            }

            @Override
            public void onApiSuccess(NetResult res, String tag) {
                super.onApiSuccess(res, tag);
                hideLoadingDlg();
                llChangeHead.setClickable(true);
                LogUtils.e("onApiSuccess");
                if (res.isOK()) {
                    UIHelper.t(_activity, "头像修改成功！");
                    ivHead.setImageBitmap(BitmapFactory.decodeFile(path));
                    ac.setProperty("head_img", path);
                }else {
                    UIHelper.t(_activity,res.getMsg());
                }
            }

            @Override
            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                super.onApiFailure(t, errorNo, strMsg, tag);
                llChangeHead.setClickable(true);
                hideLoadingDlg();
            }
        });
    }

    public void takePhoto() {
        PicChooserDialog dialog = new PicChooserDialog(_activity,
                R.style.bottom_dialog);
        dialog.setListner(new PicChooserDialog.OnPicChooserListener() {
            @Override
            public void onClickFromGallery(View view) {
                if(Build.VERSION.SDK_INT >= 23){
                    if (ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1002);
                    } else {
                        startImagePick();
                    }
                }else{
                    startImagePick();
                }
            }

            @Override
            public void onClickFromCamera(View view) {
                if(Build.VERSION.SDK_INT >= 23){
                    if (ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
                    } else {
                        startActionCamera();
                    }
                }else{
                    startActionCamera();
                }
            }
        });
        dialog.show();
    }

    public void startImagePick() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        this.startActivityForResult(
                Intent.createChooser(intent, getString(R.string.select_pic)),
                ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
    }

    private void startActionCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, this.getCameraTempFile());
        this.startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYCAMERA);
    }

    private Uri getCameraTempFile() {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            UIHelper.t(_activity, getString(R.string.submit_head_pic_error));
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        // 照片命名
        String cropFileName = "osc_camera_" + timeStamp + ".jpg";
        // 裁剪头像的绝对路径
        protraitPath = FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);
        cropUri = Uri.fromFile(protraitFile);
        this.origUri = this.cropUri;
        return this.cropUri;
    }

    private Uri getUploadTempFile(Uri uri) {
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            File savedir = new File(FILE_SAVEPATH);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        } else {
            UIHelper.t(_activity, getString(R.string.submit_head_pic_error));
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String thePath = ImageUtils.getAbsolutePathFromNoStandardUri(uri);

        // 如果是标准Uri
        if (!TextUtils.isEmpty(thePath)) {
            thePath = ImageUtils.getAbsoluteImagePath(_activity, uri);
        }
        String ext = FileUtils.getFileFormat(thePath);
        ext = TextUtils.isEmpty(ext) ? "jpg" : ext;
        // 照片命名
        String cropFileName = "osc_crop_" + timeStamp + "." + ext;
        // 裁剪头像的绝对路径
        protraitPath = FILE_SAVEPATH + cropFileName;
        protraitFile = new File(protraitPath);

        cropUri = Uri.fromFile(protraitFile);
        return this.cropUri;
    }

    private void startActionCrop(Uri data) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*");
        intent.putExtra("output", this.getUploadTempFile(data));
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);// 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", CROP);// 输出图片大小
        intent.putExtra("outputY", CROP);
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        startActivityForResult(intent,
                ImageUtils.REQUEST_CODE_GETIMAGE_BYSDCARD);
    }

   private void checkUpdateVersion(){
       if(Build.VERSION.SDK_INT >= 23){
           if (ContextCompat.checkSelfPermission(_activity,
                   Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
               requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1003);
           } else {
              updateVersion();
           }
       }else{
          updateVersion();
       }
   }

   private void updateVersion(){
       AppUtil.getCarApiClient(ac).upgradeData(new BaseApiCallback(){
           @Override
           public void onApiStart(String tag) {
               super.onApiStart(tag);
               showLoadingDlg();
           }

           @Override
           public void onApiSuccess(NetResult res, String tag) {
               super.onApiSuccess(res, tag);
               hideLoadingDlg();
               if(res!=null){
                   Version version = (Version)res;
                   if(res!=null){
                       if(version.getData().getVersionCode()!=null&&version.getData().getVersionCode().intValue()>getVersionCode(_activity)){
                           VersionUpdate versionUpdate = new VersionUpdate(_activity);
                           versionUpdate.checkUpdate(version, VersionUpdate.DialogType.NONE);
                       }else {
                           UIHelper.t(_activity,"当前版本为最新版本！");
                       }

                   }
               }
           }

           @Override
           public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
               super.onApiFailure(t, errorNo, strMsg, tag);
               hideLoadingDlg();
           }
       });
   }

    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            PackageInfo pi = context.getPackageManager().getPackageInfo(this.getPackageName(),1);
            versionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}









