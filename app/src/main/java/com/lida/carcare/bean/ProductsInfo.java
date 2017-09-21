package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * id查询商品
 * Created by xkr on 2017/7/25.
 */

public class ProductsInfo extends NetResult {


    /**
     * status : 1
     * data : {"id":"017394045bec11e7838700163e0877","code":"45623","name":"test2","shopId":"1c2799fb4f1911e79108f44d30a3e396","classId":"dfdgdfg","goodsImg":"upload/car/eb33972551c9e9487ac5cd9061472a38.jpg","price":35}
     */

    private DataBean data;

    public static ProductsInfo parse(String json) throws AppException {
        ProductsInfo res = new ProductsInfo();
        try{
            res = gson.fromJson(json, ProductsInfo.class);
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

    public static class DataBean {
        /**
         * id : 017394045bec11e7838700163e0877
         * code : 45623
         * name : test2
         * shopId : 1c2799fb4f1911e79108f44d30a3e396
         * classId : dfdgdfg
         * goodsImg : upload/car/eb33972551c9e9487ac5cd9061472a38.jpg
         * price : 35
         */

        private String id;
        private String code;
        private String name;
        private String shopId;
        private String classId;
        private String goodsImg;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getGoodsImg() {
            return goodsImg;
        }

        public void setGoodsImg(String goodsImg) {
            this.goodsImg = goodsImg;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
