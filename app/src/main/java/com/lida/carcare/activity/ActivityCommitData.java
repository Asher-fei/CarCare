package com.lida.carcare.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TResult;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterPic;
import com.lida.carcare.adapter.AdapterShopPics;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogChoosePicType;
import com.lida.carcare.widget.InnerGridView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.dialog.LoadingDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店主提交审核资料
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityCommitData extends TakePhotoActivity
{

    @BindView(R.id.gvPic)
    InnerGridView gvPic;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etShopName)
    EditText etShopName;
    @BindView(R.id.etIDNumber)
    EditText etIDNumber;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.ivCardA)
    RoundedImageView ivCardA;
    @BindView(R.id.btnA)
    Button btnA;
    @BindView(R.id.ivCardB)
    RoundedImageView ivCardB;
    @BindView(R.id.btnB)
    Button btnB;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    private List<String> pics = new ArrayList<>();
    private AdapterShopPics adapterPic;
    private AppContext ac;
    private TakePhotoActivity _activity;
    private DialogChoosePicType dialog;

    public static int flag = 0;//0 正面  1 反面  2 营业执照

    private String userid = "";//接收注册页返回的参数
    private String password = "";
    private String name = null;
    private String phone = null;
    private String shopName = null;
    private String cardFrontPath = "";
    private String cardNegativePath = "";

    private LoadingDialog dlg;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    private String address="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commitdata);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else
        {
            StatusBarUtil.setColor(this, getResources().getColor(com.bishilai.thirdpackage.R.color.colorPrimary));
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        }
        topbar.setTitle("提交资料");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        userid = getIntent().getExtras().getString("userid");
        password = getIntent().getExtras().getString("password");
        shopName = getIntent().getExtras().getString("shopName");
        phone = getIntent().getExtras().getString("phone");
        name = getIntent().getExtras().getString("name");
        ac = (AppContext) getApplication();
        _activity = this;
        ButterKnife.bind(this);
        adapterPic = new AdapterShopPics(this, pics);
        gvPic.setAdapter(adapterPic);

        etName.setText(name);
        etShopName.setText(shopName);
        etPhone.setText(phone);

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(_activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
            } else {
                startLocation();
            }
        } else {
            startLocation();
        }
    }

    DialogChoosePicType.onTypeSelectedListener listener = new DialogChoosePicType.onTypeSelectedListener()
    {
        @Override
        public void onOpenCamera()
        {
            TakePhoto takePhoto = _activity.getTakePhoto();
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            CompressConfig config;
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config = CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config, false);
            takePhoto.onPickFromCapture(imageUri);
        }

        @Override
        public void onOpenPic()
        {
            TakePhoto takePhoto = _activity.getTakePhoto();
            CompressConfig config;
            LubanOptions option = new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config = CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config, false);
            takePhoto.onPickFromGallery();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        LogUtils.e(requestCode);
        LogUtils.e(resultCode);
        LogUtils.e(data);
        if (requestCode == 1003 || requestCode == 1006)
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void takeSuccess(final TResult result) {
        super.takeSuccess(result);
        LogUtils.e("flag:" + flag);
        if (!"".equals(result.getImage().getCompressPath())) {
            if (flag == 0)
            {
                cardFrontPath = result.getImage().getCompressPath();
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ivCardA.setImageBitmap(BitmapFactory.decodeFile(result.getImage().getCompressPath()));
                    }
                });
            }
            if (flag == 1){
                cardNegativePath = result.getImage().getCompressPath();
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        ivCardB.setImageBitmap(BitmapFactory.decodeFile(result.getImage().getCompressPath()));
                    }
                });
            }
            if (flag == 2) {
                pics.add(result.getImage().getCompressPath());
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        adapterPic.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    @Override
    public void takeFail(TResult result, String msg)
    {
        super.takeFail(result, msg);
        LogUtils.e(result);
    }

    @OnClick({R.id.btnA, R.id.btnB, R.id.btnCommit})
    public void onViewClicked(View view)
    {
        dialog = new DialogChoosePicType(_activity);
        dialog.setTypeSelectedListener(listener);
        switch (view.getId())
        {
            case R.id.btnA:
                flag = 0;
                dialog.show();
                break;
            case R.id.btnB:
                flag = 1;
                dialog.show();
                break;
            case R.id.btnCommit:
                String name = etName.getText().toString();
                String shopName = etShopName.getText().toString();
                String idCard = etIDNumber.getText().toString();
                String mobilephone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                /*if ("".equals(name) || "".equals(shopName) || "".equals(mobilephone) || "".equals(address)
                        || "".equals(cardFrontPath) || "".equals(cardNegativePath))
                {
                    UIHelper.t(_activity, "资料填写不完整！");
                    return;
                }*/
                if ("".equals(name) || "".equals(shopName) || "".equals(mobilephone) || "".equals(address)||pics.size()==0)
                {
                    UIHelper.t(_activity, "资料填写不完整！");
                    return;
                }
                if (!Func.isMobileNO(mobilephone))
                {
                    UIHelper.t(_activity, "手机号码格式不正确！");
                    return;
                }
                AppUtil.getCarApiClient(ac).userAubmitAudit(name, shopName, idCard, mobilephone, address, cardFrontPath,
                        cardNegativePath, pics, userid, password, callback);
                break;
        }
    }

    BaseApiCallback callback = new BaseApiCallback() {

        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
            btnCommit.setClickable(false);
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            btnCommit.setClickable(true);
            if (res.isOK())
            {
                UIHelper.t(_activity, "资料提交成功！");
                finish();
            }else{
                ac.handleErrorCode(_activity,res.getMsg());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
            btnCommit.setClickable(true);
        }
    };

    public void showLoadingDlg() {
        if (dlg != null && dlg.isShowing()) {
            return;
        }
        if (dlg == null) {
            dlg = new LoadingDialog(this, com.bishilai.thirdpackage.R.layout.dialog_msg,
                    com.bishilai.thirdpackage.R.style.dialog_msg);
        }
        dlg.setCanceledOnTouchOutside(true);
        dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                if (!true) {
                    finish();
                }
            }
        });
        dlg.showMessage("");
    }

    public void hideLoadingDlg() {
        if (dlg != null) {
            dlg.dismiss();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                startLocation();
            } else {
                UIHelper.t(_activity,"定位失败...");
                // 没有获取到权限，做特殊处理
            }
        }
    }

    private void startLocation() {
        if (mLocationClient != null) {
            mLocationClient.start();
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);

    }


    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);

            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
                address = location.getAddrStr();
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
                address = location.getAddrStr();

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

                address = location.getAddrStr();

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                UIHelper.t(_activity,"定位失败...");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
                UIHelper.t(_activity,"定位失败...");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                UIHelper.t(_activity,"定位失败...");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息
            if (mLocationClient != null) {
                mLocationClient.stop();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    etAddress.setText(address);
                }
            });

            LogUtils.d(sb.toString());

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }
}
