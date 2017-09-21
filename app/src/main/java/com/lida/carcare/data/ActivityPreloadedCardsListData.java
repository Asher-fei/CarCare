package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OverTimeLessThanBean;
import com.lida.carcare.bean.PreloadedCardsListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 充值卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityPreloadedCardsListData extends BaseListDataSource {

    private String consumeCardNo="";

    public ActivityPreloadedCardsListData(Context context) {
        super(context);
    }

    public void setParams(String consumeCardNo) {
        this.consumeCardNo = consumeCardNo;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        PreloadedCardsListBean bean = AppUtil.getCarApiClient(ac).selectOfPreloadedCardslist(consumeCardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
