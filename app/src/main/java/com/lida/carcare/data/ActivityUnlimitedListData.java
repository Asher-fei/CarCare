package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.UnlimitedListBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 无限卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityUnlimitedListData extends BaseListDataSource {

    private String cardNo="";

    public ActivityUnlimitedListData(Context context) {
        super(context);
    }

    public void setParams(String cardNo){
        this.cardNo = cardNo;

    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        UnlimitedListBean bean = AppUtil.getCarApiClient(ac).selectUnlimitedlist(cardNo);
        models.addAll(bean.getData());
        hasMore = false;
        return models;
    }
}
