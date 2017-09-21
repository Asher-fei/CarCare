package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OrderPlaceBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 我的订单
 * Created by Administrator on 2017/7/24.
 */

public class ActivityOrderListData extends BaseListDataSource {

    private String status="";

    public void setStatus(String status){
        this.status = status;
    }

    public ActivityOrderListData(Context context) {
        super(context);
    }


    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        OrderPlaceBean bean = AppUtil.getCarApiClient(ac).SelectOrderPlace(status);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
