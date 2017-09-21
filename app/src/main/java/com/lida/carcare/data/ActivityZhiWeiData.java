package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.PositionBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 职位
 * Created by Administrator on 2017/4/5.
 */

public class ActivityZhiWeiData extends BaseListDataSource {

    public ActivityZhiWeiData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        PositionBean bean = AppUtil.getCarApiClient(ac).findTsDepartAndCount();
        ArrayList<NetResult> models=new ArrayList<>();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
