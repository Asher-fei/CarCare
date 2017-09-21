package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 查询所有商品
 * Created by Administrator on 2017/6/21.
 */

public class ActivityQueryAllGoodsData extends BaseListDataSource {

    public ActivityQueryAllGoodsData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        QueryAllGoodsBean bean = AppUtil.getCarApiClient(ac).selectProductByShopId();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
