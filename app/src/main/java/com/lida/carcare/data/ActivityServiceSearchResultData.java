package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodSearchResultBean;
import com.lida.carcare.bean.ServiceSearchResultBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 服务搜索结果
 * Created by Administrator on 2017/4/5.
 */

public class ActivityServiceSearchResultData extends BaseListDataSource {

    private String name;

    public ActivityServiceSearchResultData(Context context, String name) {
        super(context);
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ServiceSearchResultBean bean = AppUtil.getCarApiClient(ac).findServiceByName(name, ac.shopId);
        ArrayList<NetResult> models=new ArrayList<>();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
