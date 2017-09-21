package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 采购记录
 * Created by Administrator on 2017/6/14.
 */

public class PurchaseHistoryBean extends NetResult {

    private List<DataBean> data;

    public static PurchaseHistoryBean parse(String json) throws AppException
    {
        PurchaseHistoryBean res = new PurchaseHistoryBean();
        try
        {
            res = gson.fromJson(json, PurchaseHistoryBean.class);
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
         * id : 2487e166564911e79a4bb8975ae21ffe
         * supplierId : hohohohoho
         * logisticsCompany : 哈哈哈哈哈
         * logisticsCode : AA
         * purchaseStutas : 0
         * purchaseDate : 2017-06-21 14:16:20
         */

        private String id;
        private String supplierId;
        private String logisticsCompany;
        private String logisticsCode;
        private String purchaseDate;
        private String scompany;
        private String purchasePrice;
        private String code;

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

        public String getScompany() {
            return scompany;
        }

        public void setScompany(String scompany) {
            this.scompany = scompany;
        }

        public String getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(String purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
