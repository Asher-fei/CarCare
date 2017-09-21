package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 当前用户拥有的次卡列表
 * Created by Administrator on 2017/7/6.
 */

public class OnceCarByCustomerBean extends NetResult {


    /**
     * status : 1
     * data : [{"onceCardNo":"123456","cardName":"VIP"}]
     */

    private List<DataBean> data;

    public static OnceCarByCustomerBean parse(String json) throws AppException
    {
        OnceCarByCustomerBean res = new OnceCarByCustomerBean();
        try
        {
            res = gson.fromJson(json, OnceCarByCustomerBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends NetResult{
        /**
         * onceCardNo : 123456
         * cardName : VIP
         */

        private String onceCardNo;
        private String cardName;

        public String getOnceCardNo() {
            return onceCardNo;
        }

        public void setOnceCardNo(String onceCardNo) {
            this.onceCardNo = onceCardNo;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }
    }
}
