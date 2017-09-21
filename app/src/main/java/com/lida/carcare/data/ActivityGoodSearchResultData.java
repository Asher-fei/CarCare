package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodListBean;
import com.lida.carcare.bean.GoodSearchResultBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 商品搜索结果
 * Created by Administrator on 2017/4/5.
 */

public class ActivityGoodSearchResultData extends BaseListDataSource {

    private String name;

    public ActivityGoodSearchResultData(Context context, String name) {
        super(context);
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        GoodSearchResultBean bean = AppUtil.getCarApiClient(ac).findProductByName(name, ac.shopId);
        ArrayList<NetResult> models=new ArrayList<>();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
