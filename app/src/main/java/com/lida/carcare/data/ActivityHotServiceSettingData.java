package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServerHotBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 热门服务
 * Created by xkr on 2017/9/11.
 */

public class ActivityHotServiceSettingData extends BaseListDataSource {

    String name;

    public ActivityHotServiceSettingData(Context context) {
        super(context);
    }

    public void setParams(String name){
        this.name = name;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
       ServerHotBean bean = AppUtil.getCarApiClient(ac).selectJfomCategory(name);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
