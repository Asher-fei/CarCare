package com.lida.carcare.data;

import android.content.Context;

import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 派单
 * Created by Administrator on 2017/4/5.
 */

public class ActivityDispatchData extends BaseListDataSource {

    public ActivityDispatchData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            models.add(new NetResult());
        }
        return models;
    }
}