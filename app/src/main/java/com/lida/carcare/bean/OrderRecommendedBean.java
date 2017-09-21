package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 订货商品推荐
 * Created by xkr on 2017/7/24.
 */

public class OrderRecommendedBean extends NetResult {


    /**
     * status : 1
     * data : [{"id":"fhgfhgfh","goodId":"017394045bec11e7838700163e0877ba","shopId":"c5afd3c9530c11e7907600163e0877ba"},{"id":"sdfdgfdg","goodId":"017394045bec11e7838700163e0877","shopId":"c5afd3c9530c11e7907600163e0877ba"}]
     */

    private List<DataBean> data;

    public static OrderRecommendedBean parse(String json) throws AppException {
        OrderRecommendedBean res = new OrderRecommendedBean();
        try{
            res = gson.fromJson(json, OrderRecommendedBean.class);
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

    public static class DataBean {
        /**
         * id : fhgfhgfh
         * goodId : 017394045bec11e7838700163e0877ba
         * shopId : c5afd3c9530c11e7907600163e0877ba
         */

        private String id;
        private String goodId;
        private String shopId;
        private String productImg;
        private String productName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodId() {
            return goodId;
        }

        public void setGoodId(String goodId) {
            this.goodId = goodId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}
