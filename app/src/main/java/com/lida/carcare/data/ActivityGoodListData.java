package com.lida.carcare.data;

import android.app.Activity;
import android.content.Context;

import com.lida.carcare.activity.ActivityGoodList;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodListBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 商品列表
 * Created by Administrator on 2017/4/5.
 */

public class ActivityGoodListData extends BaseListDataSource {

    private String code;

    public ActivityGoodListData(Context context, String code) {
        super(context);
        this.code = code;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        final GoodListBean bean = AppUtil.getCarApiClient(ac).getProductByCode(code,ac.shopId);
        models.addAll(bean.getData().getJfomGoods());
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ActivityGoodList.tvCount.setText("商品数量："+bean.getData().getCount());
            }
        });
        hasMore=false;
        return models;
    }
}
