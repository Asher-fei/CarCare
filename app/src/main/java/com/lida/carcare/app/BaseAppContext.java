package com.lida.carcare.app;

import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.midian.base.app.AppContext;
import com.midian.base.util.ShareUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class BaseAppContext extends AppContext {

    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtil.init();
        CarApiClient.init(this);
        ZXingLibrary.initDisplayOpinion(this);
        SDKInitializer.initialize(this);
        MultiDex.install(this);
    }
}
