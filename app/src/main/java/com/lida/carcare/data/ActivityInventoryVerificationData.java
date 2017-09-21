package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CustomerMainBean;
import com.lida.carcare.bean.InventoryVerificationBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 库存盘点
 * Created by zdf on 2017/6/28.
 */

public class ActivityInventoryVerificationData extends BaseListDataSource{


    private String shoutId="";

    public ActivityInventoryVerificationData(Context context) {
        super(context);
    }

    public void setParams(String shoutId){
        this.shoutId = shoutId;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        InventoryVerificationBean bean = AppUtil.getCarApiClient(ac).selectShout(shoutId);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
