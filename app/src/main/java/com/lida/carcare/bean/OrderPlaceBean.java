package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 订单列表
 * Created by xkr on 2017/7/25.
 */

public class OrderPlaceBean extends NetResult {


    /**
     * status : 1
     * data : [{"id":"dfds","orderCode":"2017113121","state":"0","createDate":null,"supplierName":null,"trackingNumber":null,"logisticsCompany":null,"totalPrice":150,"shopId":"1c2799fb4f1911e79108f44d30a3e396"},{"id":"dfdsf","orderCode":"sdfdsf","state":"1","createDate":null,"supplierName":null,"trackingNumber":null,"logisticsCompany":null,"totalPrice":80,"shopId":"1c2799fb4f1911e79108f44d30a3e396"}]
     */

    private List<DataBean> data;

    public static OrderPlaceBean parse(String json) throws AppException {
        OrderPlaceBean res = new OrderPlaceBean();
        try{
            res = gson.fromJson(json, OrderPlaceBean.class);
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
         * id : dfds
         * orderCode : 2017113121
         * state : 0
         * createDate : null
         * supplierName : null
         * trackingNumber : null
         * logisticsCompany : null
         * totalPrice : 150
         * shopId : 1c2799fb4f1911e79108f44d30a3e396
         */

        private String id;
        private String orderCode;
        private String state;
        private String createDate;
        private String supplierName;
        private String trackingNumber;
        private String logisticsCompany;
        private String totalPrice;
        private String shopId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public String getTrackingNumber() {
            return trackingNumber;
        }

        public void setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }

        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
    }
}
