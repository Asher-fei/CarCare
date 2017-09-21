package com.lida.carcare.app;

import com.midian.base.app.AppContext;

/**
 * Created by Administrator on 2017/3/7.
 */

public class AppUtil {
    public static BaseAppContext getBaseAppContext(AppContext ac){
        return (BaseAppContext) ac;
    }

    public static CarApiClient getCarApiClient(AppContext ac){
        return ac.api.getApiClient(CarApiClient.class);
    }
}
