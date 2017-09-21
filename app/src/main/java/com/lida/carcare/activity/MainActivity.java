package com.lida.carcare.activity;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import com.apkfuns.logutils.LogUtils;
import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.camera.RectCameraActivity;
import com.lida.carcare.fragment.FragmentHome;
import com.lida.carcare.fragment.FragmentPersonalBoss;
import com.lida.carcare.fragment.FragmentPersonalWorker;
import com.lida.carcare.updater.Version;
import com.lida.carcare.updater.VersionUpdate;
import com.lida.carcare.widget.grandienttab.GradientTabStrip;
import com.lida.carcare.widget.grandienttab.GradientTabStripAdapter;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import am.widget.basetabstrip.BaseTabStrip;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/5.
 */

public class MainActivity extends BaseFragmentActivity implements BaseTabStrip.OnItemClickListener, OnPermissionCallback
{

    //    @BindView(R.userId.topbar)
//    BaseLibTopbarView topbar;
    @BindView(R.id.gts_vp_fragments)
    ViewPager vpFragments;
    @BindView(R.id.gradientTabStrip)
    GradientTabStrip tabStrip;

    private GradientTabStripAdapter adapter;
    private List<Fragment> fragments = new ArrayList<>();

    private long waitTime = 2000;
    private long touchTime = 0;
    private PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        checkDay();
        permissionHelper = PermissionHelper.getInstance(_activity);
    }


    private void checkDay(){

        //判断今天是否检测过更新了,每天只检测一次
        SharedPreferences sp = getSharedPreferences("CheckUpdate", Context.MODE_PRIVATE);
        String checkDate = sp.getString("checkDate",null);
        Log.i("TAG","checkDate = "+checkDate);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int  month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        if(checkDate==null||!(String.valueOf(year)+String.valueOf(month)+String.valueOf(day)).equals(checkDate)){
            check();
        }
    }

    private void initView()
    {

        vpFragments.setOffscreenPageLimit(2);
        fragments.add(new FragmentHome());
        if ("0".equals(ac.user_type) || "2".equals(ac.user_type)||"4".equals(ac.user_type))
        {//老板
            fragments.add(new FragmentPersonalBoss());
        } else
        {
            fragments.add(new FragmentPersonalWorker());
        }
        adapter = new GradientTabStripAdapter(getSupportFragmentManager(), fragments);
        vpFragments.setAdapter(adapter);
        tabStrip.setAdapter(adapter);
        tabStrip.bindViewPager(vpFragments);
        tabStrip.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position)
    {
    }

    @Override
    public void onSelectedClick(int position)
    {
    }

    @Override
    public void onDoubleClick(int position)
    {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode)
            {
                long currentTime = System.currentTimeMillis();
                if ((currentTime - touchTime) >= waitTime)
                {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    touchTime = currentTime;
                } else
                {
                    finish();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.ivCamera)
    public void onViewClicked()
    {
        if (permissionHelper.isPermissionGranted(Manifest.permission.CAMERA) &&
                permissionHelper.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            Bundle bundle = new Bundle();
            bundle.putString("flag", "MainActivity");
            UIHelper.jump(_activity, RectCameraActivity.class, bundle);
        } else
        {
            permissionHelper = PermissionHelper.getInstance(_activity);
            permissionHelper.requestAfterExplanation(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE});
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode==1008){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.getCarApiClient(ac).upgradeData(this);
            }else {

            }
        }else {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName)
    {
        UIHelper.jump(_activity, RectCameraActivity.class);
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName)
    {
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName)
    {
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName)
    {
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName)
    {
        LogUtils.e(permissionName);
        if (permissionName.contains("CAMERA"))
        {
            UIHelper.t(_activity, "扫描车牌需要开启照相机权限");
            return;
        }
        if (permissionName.contains("READ_EXTERNAL_STORAGE"))
        {
            UIHelper.t(_activity, "扫描车牌需要开启文件读取权限");
            return;
        }
    }

    @Override
    public void onNoPermissionNeeded()
    {
        UIHelper.jump(_activity, RectCameraActivity.class);
    }

    private void check(){
        //Android 6.0后需动态申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {  //存储权限没有得到授权
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1008);
        }else {  //6.0以下系统或者存储权限已授权
            AppUtil.getCarApiClient(ac).upgradeData(this);
        }

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res!=null){
            Version version = (Version)res;
            if(res!=null){
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int  month = c.get(Calendar.MONTH)+1;
                int day = c.get(Calendar.DAY_OF_MONTH);
                SharedPreferences sp = getSharedPreferences("CheckUpdate", Context.MODE_PRIVATE);
                sp.edit().putString("checkDate", String.valueOf(year)+String.valueOf(month)+String.valueOf(day)).commit();

                VersionUpdate versionUpdate = new VersionUpdate(_activity);
                versionUpdate.checkUpdate(version, VersionUpdate.DialogType.NONE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
