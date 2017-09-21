package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.RemindBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 当前关注
 * Created by WeiQingFeng on 2017/4/13.
 */

public class ActivityRemindData extends BaseListDataSource {


    public ActivityRemindData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        RemindBean bean = AppUtil.getCarApiClient(ac).selectCarRemindlist();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
