package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ActivitySelectOutboundGoodsData extends BaseListDataSource {

    private String name = "";
    private String productType = "";
    private String repertoryId = "";

    public void setParams(String name, String productType, String repertoryId){
        this.name = name;
        this.productType = productType;
        this.repertoryId = repertoryId;
    }


    public ActivitySelectOutboundGoodsData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        SelectOutboundGoodslistBean bean = AppUtil.getCarApiClient(ac).selectOutboundGoodslist(name, productType, repertoryId);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
