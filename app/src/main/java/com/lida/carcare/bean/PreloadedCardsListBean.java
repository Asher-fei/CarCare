package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 充值卡列表
 * Created by Administrator on 2017/7/4.
 */

public class PreloadedCardsListBean extends NetResult {

    /**
     * status : 1
     * data : [{"id":"8afabc415d4411e7aac6102175e7270e","consumeCardNo":"1234567890","consumeCardName":"456","mobile":"","residualAmount":1000}]
     */

    private List<DataBean> data;

    public static PreloadedCardsListBean parse(String json) throws AppException {
        PreloadedCardsListBean res = new PreloadedCardsListBean();
        try{
            res = gson.fromJson(json, PreloadedCardsListBean.class);
        }catch (JsonSyntaxException e){
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
         * id : 8afabc415d4411e7aac6102175e7270e
         * consumeCardNo : 1234567890
         * consumeCardName : 456
         * mobile :
         * residualAmount : 1000
         */

        private String id;
        private String consumeCardNo;
        private String consumeCardName;
        private String mobile;
        private double residualAmount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConsumeCardNo() {
            return consumeCardNo;
        }

        public void setConsumeCardNo(String consumeCardNo) {
            this.consumeCardNo = consumeCardNo;
        }

        public String getConsumeCardName() {
            return consumeCardName;
        }

        public void setConsumeCardName(String consumeCardName) {
            this.consumeCardName = consumeCardName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public double getResidualAmount() {
            return residualAmount;
        }

        public void setResidualAmount(double residualAmount) {
            this.residualAmount = residualAmount;
        }
    }
}
