package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 待入库
 * Created by Administrator on 2017/6/14.
 */

public class WaitWareHouseBean extends NetResult {

    private List<DataBean> data;

    public static WaitWareHouseBean parse(String json) throws AppException
    {
        WaitWareHouseBean res = new WaitWareHouseBean();
        try
        {
            res = gson.fromJson(json, WaitWareHouseBean.class);
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


    public static class DataBean extends NetResult implements Serializable{
        /**
         * id : 119910295cb911e791dcf44d30a3e396
         * supplierId : c09409f255b011e78e92f44d30a3e396
         * logisticsCompany : 哦哦
         * logisticsCode : 666
         * price : null
         * purchaseDate : 2017-06-29 18:52:39
         * jfomGoods : null
         * carPurchaseRecords : null
         * supplierCompany : 深圳供应商
         */

        private String id;
        private String supplierId;
        private String logisticsCompany;
        private String logisticsCode;
        private String price;
        private String purchaseDate;
        private String jfomGoods;
        private String carPurchaseRecords;
        private String supplierCompany;
        private boolean check;
        private String billCode;

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public String getJfomGoods() {
            return jfomGoods;
        }

        public void setJfomGoods(String jfomGoods) {
            this.jfomGoods = jfomGoods;
        }

        public String getCarPurchaseRecords() {
            return carPurchaseRecords;
        }

        public void setCarPurchaseRecords(String carPurchaseRecords) {
            this.carPurchaseRecords = carPurchaseRecords;
        }

        public String getSupplierCompany() {
            return supplierCompany;
        }

        public void setSupplierCompany(String supplierCompany) {
            this.supplierCompany = supplierCompany;
        }
    }
}
