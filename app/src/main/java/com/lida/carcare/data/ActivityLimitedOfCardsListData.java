package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.LimitedOfCardsListBean;
import com.lida.carcare.bean.TimeCardListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 有限卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityLimitedOfCardsListData extends BaseListDataSource {
    private String cardNo="";

    public ActivityLimitedOfCardsListData(Context context) {
        super(context);
    }

    public void setParams(String cardNo){
        this.cardNo = cardNo;

    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        LimitedOfCardsListBean bean = AppUtil.getCarApiClient(ac).selectLimitedOfCardslist(cardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
