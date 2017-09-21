package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 *
 * 即将过期 、已过期列表
 * Created by Administrator on 2017/7/3.
 */

public class WillExpireBean extends NetResult {

    /**
     * status : 1
     * data : [{"mobile":"","cardNo":"123466","userName":"A客户","cardType":"次卡"}]
     */

    private List<DataBean> data;

    public static WillExpireBean parse(String json) throws AppException
    {
        WillExpireBean res = new WillExpireBean();
        try
        {
            res = gson.fromJson(json, WillExpireBean.class);
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
         * mobile :
         * cardNo : 123466
         * userName : A客户
         * cardType : 次卡
         */

        private String mobile;
        private String cardNo;
        private String userName;
        private String cardType;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }
    }
}
