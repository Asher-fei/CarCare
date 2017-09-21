package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.LackOfBalanceBean;
import com.lida.carcare.bean.OutIntHistoryBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 *
 * 余额不足详情
 * Created by Administrator on 2017/7/3.
 */

public class ActivityLackOfBalanceData extends BaseListDataSource {

    private String consumeCardNo="";

    public ActivityLackOfBalanceData(Context context) {
        super(context);
    }

    public void setParams(String consumeCardNo){
        this.consumeCardNo = consumeCardNo;

    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        LackOfBalanceBean bean = AppUtil.getCarApiClient(ac).selectConsumeCardInsufficient(consumeCardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
