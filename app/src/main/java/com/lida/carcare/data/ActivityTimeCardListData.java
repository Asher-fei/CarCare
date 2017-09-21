package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.TimeCardListBean;
import com.lida.carcare.bean.WillExpireBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 次卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityTimeCardListData extends BaseListDataSource {

    private String cardNo="";
    public ActivityTimeCardListData(Context context) {
        super(context);
    }

    public void setParams(String cardNo){
        this.cardNo = cardNo;

    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        TimeCardListBean bean = AppUtil.getCarApiClient(ac).selectTimeCardlist(cardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
