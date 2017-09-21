package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.WaitWareHouseBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 待入库
 * Created by Administrator on 2017/6/26.
 */

public class ActivityWaitingWareHourseData extends BaseListDataSource {

    public ActivityWaitingWareHourseData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        WaitWareHouseBean bean = AppUtil.getCarApiClient(ac).selectCarPurchaseDetailList();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }

}
