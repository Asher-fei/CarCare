package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OutIntHistoryBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by zdf on 2017/6/26.
 * 出入库记录
 */

public class ActivityOutIntHistoryData extends BaseListDataSource {
    private String statu = "";
    private String str = "";
    public ActivityOutIntHistoryData(Context context) {
        super(context);
    }
    public void setParams(String Stutas, String Name){
        this.statu = Stutas;
        this.str = Name;
    }
    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        OutIntHistoryBean bean = AppUtil.getCarApiClient(ac).getOupInpHistory(str,statu);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
