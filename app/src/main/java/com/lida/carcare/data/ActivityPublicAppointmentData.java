package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.PublicAppointmentBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 公众号预约列表
 * Created by xkr on 2017/8/7.
 */

public class ActivityPublicAppointmentData extends BaseListDataSource {


    public ActivityPublicAppointmentData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
       PublicAppointmentBean bean = AppUtil.getCarApiClient(ac).selectWeiXinAppList();
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
