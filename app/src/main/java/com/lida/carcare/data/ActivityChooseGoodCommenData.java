package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GetWarehouseByCodeBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ActivityChooseGoodCommenData extends BaseListDataSource {

    private String code;

    public ActivityChooseGoodCommenData(Context context, String code) {
        super(context);
        this.code = code;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        GetWarehouseByCodeBean bean = AppUtil.getCarApiClient(ac).getWarehouseBy(code);
        models.addAll(bean.getData().getCarWarehouse());
        hasMore = false;
        return models;
    }
}
