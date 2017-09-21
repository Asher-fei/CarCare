package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CardRestrictCarnoBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30.
 */

public class CardRestrictCarnoData extends BaseListDataSource {

    public CardRestrictCarnoData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        CardRestrictCarnoBean bean = AppUtil.getCarApiClient(ac).getOnceCardType();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
