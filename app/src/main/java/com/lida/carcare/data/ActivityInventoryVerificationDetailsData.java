package com.lida.carcare.data;

import android.content.Context;

import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 盘点详情
 * Created by zdf on 2017/6/28.
 */

public class ActivityInventoryVerificationDetailsData extends BaseListDataSource {
    public ActivityInventoryVerificationDetailsData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            models.add(new NetResult());
        }
        hasMore = false;
        return models;
    }
}
