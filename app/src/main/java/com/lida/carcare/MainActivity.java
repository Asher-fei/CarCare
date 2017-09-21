package com.lida.carcare;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.login.ActivityLogin;
import com.lida.carcare.temp.PieChart;
import com.lida.carcare.widget.EditCarNumber;
import com.lida.carcare.widget.ShareDialog;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.carNumber)
    EditCarNumber carNumber;

    String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_activity_main);
        ButterKnife.bind(this);
        topbar.setTitle("测试单元");
//        carNumber.setDefaultText(new String[]{"粤","T"});
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                UIHelper.jump(_activity, PieChart.class);
                break;
            case R.id.button2:
                LogUtils.e(carNumber.getNumbers());
                break;
            case R.id.button3:
                new ShareDialog(_activity,"title","content","imageUrl","targetUrl").show();
                break;
            case R.id.button4:
//                new CarClassDialog(_activity).show();
                break;
            case R.id.button5:
                permissionHelper.requestAfterExplanation(permissions);
                break;
            case R.id.button6:
                UIHelper.jump(_activity, ActivityLogin.class);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
        LogUtils.e(permissionName);
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        LogUtils.e(permissionName);
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 1001);
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
        LogUtils.e(permissionsName);
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 1001);
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
        LogUtils.e(permissionName);
        permissionHelper.requestAfterExplanation(permissionName);
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        UIHelper.t(_activity,"请开启"+permissionName+"权限");
    }
}
