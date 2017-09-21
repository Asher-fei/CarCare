package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GetDecumentByCarnoBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 绩效管理详情
 * Created by Administrator on 2017/4/5.
 */

public class ActivityEnterprisesDetailData extends BaseListDataSource {

    private String carNo = "";

    public ActivityEnterprisesDetailData(Context context, String carNo) {
        super(context);
        this.carNo = carNo;
    }

    public ActivityEnterprisesDetailData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        Calendar cal = Calendar.getInstance();
        String month = (cal.get(Calendar.MONTH) + 1)+"";
        String year = cal.get(Calendar.YEAR)+"";
        ArrayList<NetResult> models=new ArrayList<>();
        GetDecumentByCarnoBean bean = AppUtil.getCarApiClient(ac).getDecumentByCarno(year, month, carNo);
        models.addAll(bean.getData().getDocument());
        hasMore=false;
        return models;
    }
}
