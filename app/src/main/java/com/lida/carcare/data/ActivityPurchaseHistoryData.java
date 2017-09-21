package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.PurchaseHistoryBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 采购记录
 * Created by Administrator on 2017/6/21.
 */

public class ActivityPurchaseHistoryData extends BaseListDataSource {

    private String purchaseStutas = "3";
    private String purchaseName = "";

    public ActivityPurchaseHistoryData(Context context) {
        super(context);
    }

    public void setParams(String purchaseStutas, String purchaseName){
        this.purchaseStutas = purchaseStutas;
        this.purchaseName = purchaseName;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        PurchaseHistoryBean bean = AppUtil.getCarApiClient(ac).selectCarPurchase(purchaseStutas,purchaseName);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
