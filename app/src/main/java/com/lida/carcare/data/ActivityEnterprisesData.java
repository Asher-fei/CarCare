package com.lida.carcare.data;

import android.app.Activity;
import android.content.Context;

import com.lida.carcare.activity.ActivityEnterprises;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.PerformanceBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 绩效管理
 * Created by Administrator on 2017/4/5.
 */

public class ActivityEnterprisesData extends BaseListDataSource
{
    private String month = "";
    private String year = "";

    public void setPartams(String month){
        this.month = month;
    }

    public ActivityEnterprisesData(Context context, String year, String month)
    {
        super(context);
        this.year = year;
        this.month = month;
    }

    @Override
    protected ArrayList load(int page) throws Exception
    {
        String userId = "0".equals(ac.user_type) ? null : ac.userId;
        ArrayList<NetResult> models = new ArrayList<>();
        final PerformanceBean bean = AppUtil.getCarApiClient(ac).getPerformance(year, month, userId);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(bean!=null) {
                    if (bean.getData() != null) {
                        ActivityEnterprises.tvMoney.setText("￥" + bean.getData().getAmount());
                    } else {
                        ActivityEnterprises.tvMoney.setText("￥0.0");
                    }
                }
            }
        });
        if(bean!=null) {
            if (bean.getData() != null) {
                models.addAll(bean.getData().getDocument());
            }
        }
        hasMore=false;
        return models;
    }
}
