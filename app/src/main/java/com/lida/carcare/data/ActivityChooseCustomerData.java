package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CustomerMainBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 选择联系人
 * Created by Administrator on 2017/4/5.
 */

public class ActivityChooseCustomerData extends BaseListDataSource {

    private String str = "";//模糊查询条件

    public void setStr(String str) {
        this.str = str;
    }

    public ActivityChooseCustomerData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        CustomerMainBean bean = AppUtil.getCarApiClient(ac).getCustomerList(str, ac.shopId);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
