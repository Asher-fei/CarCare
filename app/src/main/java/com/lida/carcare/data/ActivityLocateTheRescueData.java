package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.LocateTheRescueBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by xkr on 2017/8/11.
 */

public class ActivityLocateTheRescueData extends BaseListDataSource {

    public ActivityLocateTheRescueData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
       LocateTheRescueBean bean = AppUtil.getCarApiClient(ac).selectPositioningTheRescue();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
