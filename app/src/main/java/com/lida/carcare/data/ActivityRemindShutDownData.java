package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.RemindShutDownBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;


/**
 * 已关闭提醒列表
 * Created by Administrator on 2017/7/5.
 */

public class ActivityRemindShutDownData extends BaseListDataSource {

    public ActivityRemindShutDownData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        RemindShutDownBean bean = AppUtil.getCarApiClient(ac).selectCarRemindShutDownlist("关闭");
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
