package com.lida.carcare.updater;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lida.carcare.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/11.
 */

public class VersionUpdate {

    public enum DialogType {
        NONE, TOAST
    }

    private Context mContext;

    private Version mVersion = null;
    private String mCurrentVersion = "1.0";
    private int mCurrentVersionCode = 1;

    public int getCurrentVersionCode() {
        return mCurrentVersionCode;
    }

    public VersionUpdate(Context context) {
        this.mContext = context;
        mCurrentVersionCode = getVersionCode(context);
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(Version version, DialogType type) {
        if (isUpdate(version)) {
            mVersion = version;
            // 显示提示对话框
            showNoticeDialog();
            return;
        } else {
            showVersionInfo(type);
        }

    }

    public void showVersionInfo(DialogType type) {
        String msg = String.format("您使用的应用已经是最新版本[%s]!", mCurrentVersion);
        if (type == DialogType.TOAST)
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 检查软件是否有更新版本
     * versionCode 是否大于当前应用版本值
     *
     * @return
     */
    private boolean isUpdate(Version version) {

        if (version != null && version.getData().getVersionCode() != null
                && version.getData().getVersionCode().intValue() > mCurrentVersionCode) {
            return true;
        }
        return false;
    }


    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            PackageInfo pi = context.getPackageManager().getPackageInfo(mContext.getPackageName(),1);
            versionCode = pi.versionCode;
            mCurrentVersion = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog() {
       if(mVersion!=null) {
           new DialogVersionUpdate(mContext, mVersion).show();
       }
    }

    class DialogVersionUpdate extends Dialog {

        Context context;
        Version version;

        @BindView(R.id.versionName)
        TextView versionName;
        @BindView(R.id.size)
        TextView size;
        @BindView(R.id.updateTime)
        TextView updateTime;
        @BindView(R.id.updateRemark)
        TextView updateRemark;
        @BindView(R.id.btn_cancel)
        Button btnCancel;
        @BindView(R.id.btn_ok)
        Button btnOk;

        public DialogVersionUpdate(Context context,Version version) {
            super(context, R.style.diy_dialog);
            this.version = version;
            init(context);
        }

        public DialogVersionUpdate(Context context, int themeResId) {
            super(context, R.style.diy_dialog);
            init(context);
        }

        private void init(Context context) {
            this.context = context;
            Window w = this.getWindow();
            WindowManager.LayoutParams lp = w.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            w.setAttributes(lp);
            this.setCanceledOnTouchOutside(false);//按对话框以外的地方不起作用。按返回键还起作用
            View v = View.inflate(context, R.layout.dialog_version_update, null);
            this.setContentView(v);
            ButterKnife.bind(this, v);

            versionName.setText(version.getData().getVersionName()==null?"":version.getData().getVersionName());
            size.setText(version.getData().getSize()==null?"":version.getData().getSize());
            updateTime.setText(version.getData().getUpdateTime()==null?"":version.getData().getUpdateTime());
            updateRemark.setText(version.getData().getUpdateRemark()==null?"":version.getData().getUpdateRemark());
        }

        @OnClick({R.id.btn_cancel, R.id.btn_ok})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_cancel:
                    this.cancel();
                    break;
                case R.id.btn_ok:
                    if(!version.getData().getDownload_url().equals("")){
                        //调用下载管理器下载
                        UpdaterConfig config = new UpdaterConfig.Builder(context)
                                .setTitle(context.getResources().getString(R.string.app_name))
                                .setDescription(context.getString(R.string.system_download_description))
                                .setFileUrl(version.getData().getDownload_url())
                                .setCanMediaScanner(true)
                                .build();
                        Updater.get().showLog(true).download(config);
                    }
                    this.cancel();
                    break;
            }
        }
    }



}
