package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SelectShopListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 车店列表
 * Created by Administrator on 2017/6/7.
 */

public class ActivityShopListData extends BaseListDataSource {

    public ActivityShopListData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        SelectShopListBean bean = AppUtil.getCarApiClient(ac).selectShopList();
        ArrayList<NetResult> models = new ArrayList<>();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
