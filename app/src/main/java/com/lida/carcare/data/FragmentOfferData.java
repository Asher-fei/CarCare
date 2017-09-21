package com.lida.carcare.data;

import android.content.Context;
import android.content.Intent;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SelectInfoOfferBean;
import com.lida.carcare.fragment.FragmentOffer;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 报价
 * Created by Administrator on 2017/6/20.
 */

public class FragmentOfferData extends BaseListDataSource {

    private String id;

    public FragmentOfferData(Context context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        SelectInfoOfferBean bean = AppUtil.getCarApiClient(ac).selectInfoOffer(id);
        double d = 0.0;
        for (int i = 0; i < bean.getData().getJfomService().size(); i++) {
            String temp = bean.getData().getJfomService().get(i).getServicePrice();
            String count = bean.getData().getJfomService().get(i).getFrequency();
            if(!"".equals(temp)&&temp!=null){
                if(!"".equals(count)&&count!=null){
                    d += Double.valueOf(temp)* Integer.parseInt(count);
                }else {
                    d += Double.valueOf(temp);
                }
            }
        }
        FragmentOffer.tvMoney.setText("总价：￥"+d);
        models.addAll(bean.getData().getJfomService());
        hasMore = false;
        return models;
    }
}
