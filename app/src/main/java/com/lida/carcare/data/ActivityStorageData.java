package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.StorageListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/20.
 */

public class ActivityStorageData extends BaseListDataSource {

    public ActivityStorageData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        StorageListBean bean = AppUtil.getCarApiClient(ac).getRepertoryList();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
