package com.lida.carcare.data;

import android.app.Activity;
import android.content.Context;

import com.lida.carcare.activity.ActivityGoodList;
import com.lida.carcare.activity.ActivityServiceList;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceGoodBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 服务列表
 * Created by Administrator on 2017/4/5.
 */

public class ActivityServiceListData extends BaseListDataSource {

    private String code;

    public ActivityServiceListData(Context context, String code) {
        super(context);
        this.code = code;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        final ServiceGoodBean bean = AppUtil.getCarApiClient(ac).getGoodsByCode(ac.shopId,code);
        models.addAll(bean.getData().getJfomService());
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ActivityServiceList.tvCount.setText("服务数量："+bean.getData().getCount());
            }
        });
        hasMore=false;
        return models;
    }
}
