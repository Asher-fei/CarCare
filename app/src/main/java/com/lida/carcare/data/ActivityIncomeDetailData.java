package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.IncomeDetailsBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 当日收入详情
 * Created by Administrator on 2017/6/30.
 */

public class ActivityIncomeDetailData extends BaseListDataSource {

     String dateTime;
    public ActivityIncomeDetailData(Context context,String dateTime) {
        super(context);
        this.dateTime = dateTime;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
       // IncomeDetailsBean bean = AppUtil.getCarApiClient(ac).selectCarIncomeDayByTime(dateTime);
       // models.add(bean.getData());
        hasMore = false;
        return models;

    }
}
