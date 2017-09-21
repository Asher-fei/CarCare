package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.WillExpireBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 已过期列表
 * Created by Administrator on 2017/7/3.
 */

public class ActivityExpireData extends BaseListDataSource {

    private String cardNo = "";

    public ActivityExpireData(Context context) {
        super(context);
    }

    public void setParams(String cardNo){
        this.cardNo = cardNo;

    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        WillExpireBean bean = AppUtil.getCarApiClient(ac).selectExpired(cardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
