package com.lida.carcare.retrofit;

import com.apkfuns.logutils.LogUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class BaseSubcriber extends Subscriber {

    @Override
    public void onCompleted() {
        LogUtils.e("onCompleted:");
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e(e);
    }

    @Override
    public void onNext(Object o) {
        LogUtils.e("请求结果:");
        LogUtils.e(o);
    }
}
