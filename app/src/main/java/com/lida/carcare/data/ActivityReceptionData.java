package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarInShopBean;
import com.lida.carcare.bean.WorkOrderBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 接车出单
 * Created by Administrator on 2017/4/5.
 */

public class ActivityReceptionData extends BaseListDataSource {

    private String carNo;

    public ActivityReceptionData(Context context, String carNo) {
        super(context);
        this.carNo = carNo;
    }

    public ActivityReceptionData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        WorkOrderBean bean = AppUtil.getCarApiClient(ac).getdocumentList("",ac.shopId);
        ArrayList<NetResult> models=new ArrayList<>();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
