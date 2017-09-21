package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ChooseOrderGoodBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 选择订货商品列表
 * Created by Administrator on 2017/7/24.
 */

public class ActivityChooseOrderGoodData extends BaseListDataSource {

    String id="";
    String productName = "";
    String goodId="";
    public ActivityChooseOrderGoodData(Context context,String id) {
        super(context);
        this.id = id;
    }

    public void setParams(String id,String goodId,String productName){
        this.productName = productName;
        this.id = id;
        this.goodId = goodId;
    }


    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        ChooseOrderGoodBean bean = AppUtil.getCarApiClient(ac).SelectProductByClassification(id,goodId,productName);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
