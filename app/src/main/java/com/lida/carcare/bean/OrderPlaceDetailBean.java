package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 订单详情
 * Created by xkr on 2017/7/25.
 */

public class OrderPlaceDetailBean extends NetResult {


    /**
     * status : 1
     * data : {"orderCode":"201707241500880185754","state":"0","createDate":"2017-07-24 15:09:45","supplierName":null,"trackingNumber":null,"logisticsCompany":null,"totalPrice":80,"orderPlaceGoodsList":[{"price":10,"goodNum":2,"name":"测试1"},{"price":20,"goodNum":3,"name":"测试2"}]}
     */

    private DataBean data;

    public static OrderPlaceDetailBean parse(String json) throws AppException {
        OrderPlaceDetailBean res = new OrderPlaceDetailBean();
        try{
            res = gson.fromJson(json, OrderPlaceDetailBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends NetResult{
        /**
         * orderCode : 201707241500880185754
         * state : 0
         * createDate : 2017-07-24 15:09:45
         * supplierName : null
         * trackingNumber : null
         * logisticsCompany : null
         * totalPrice : 80
         * orderPlaceGoodsList : [{"price":10,"goodNum":2,"name":"测试1"},{"price":20,"goodNum":3,"name":"测试2"}]
         */

        private String orderCode;
        private String state;
        private String createDate;
        private String supplierName;
        private String trackingNumber;
        private String logisticsCompany;
        private String totalPrice;
        private List<OrderPlaceGoodsListBean> orderPlaceGoodsList;

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

        public List<OrderPlaceGoodsListBean> getOrderPlaceGoodsList() {
            return orderPlaceGoodsList;
        }

        public void setOrderPlaceGoodsList(List<OrderPlaceGoodsListBean> orderPlaceGoodsList) {
            this.orderPlaceGoodsList = orderPlaceGoodsList;
        }

        public static class OrderPlaceGoodsListBean {
            /**
             * price : 10
             * goodNum : 2
             * name : 测试1
             */

            private String price;
            private String goodNum;
            private String name;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getGoodNum() {
                return goodNum;
            }

            public void setGoodNum(String goodNum) {
                this.goodNum = goodNum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
