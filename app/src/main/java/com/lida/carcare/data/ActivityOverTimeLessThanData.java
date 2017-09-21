package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.LackOfBalanceBean;
import com.lida.carcare.bean.OverTimeLessThanBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 余次不足列表
 * Created by Administrator on 2017/7/3.
 */

public class ActivityOverTimeLessThanData extends BaseListDataSource {

    private String onceCardNo="";
    public ActivityOverTimeLessThanData(Context context) {
        super(context);
    }

    public void setParams(String onceCardNo) {
        this.onceCardNo = onceCardNo;
    }

        @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        OverTimeLessThanBean bean = AppUtil.getCarApiClient(ac).selectOverTimeLessThan(onceCardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
