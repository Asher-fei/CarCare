package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 其他出入库
 * Created by zdf on 2017/6/28.
 */

public class OutIntHistoryBean extends NetResult{

    private List<DataBean> data;

    public static OutIntHistoryBean parse(String json) throws AppException {
        OutIntHistoryBean res = new OutIntHistoryBean();
        try{
            res = gson.fromJson(json, OutIntHistoryBean.class);
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
         * id : 525096645d7c11e784e7f44d30a3e396
         * supplierId : null
         * logisticsCompany : null
         * logisticsCode : null
         * purchaseDate : 2017-06-30 18:10:19
         * code : 1498817419653
         * purchasePrice : null
         * scompany : null
         * workStatus : 3
         */

        private String id;
        private String supplierId;
        private String logisticsCompany;
        private String logisticsCode;
        private String purchaseDate;
        private String code;
        private String purchasePrice;
        private String scompany;
        private String workStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public String getLogisticsCode() {
            return logisticsCode;
        }

        public void setLogisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(String purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public String getScompany() {
            return scompany;
        }

        public void setScompany(String scompany) {
            this.scompany = scompany;
        }

        public String getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(String workStatus) {
            this.workStatus = workStatus;
        }
    }
}
