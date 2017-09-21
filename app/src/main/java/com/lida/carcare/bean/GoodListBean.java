package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 获取商品二级分类下的商品列表
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GoodListBean extends NetResult {


    /**
     * data : {"jfomGoods":[{"userId":"02c074da4bf711e7b48df44d30a3e396","name":"34","sizeParem":"456","sizeName":"123","productImg":"upload/car/4ff7a83fbf91fb1f8cbd38237c934409.jpg","type":"0","drawPrice":"11","brand":"sdf","drawType":"0","pricePlatform":123,"StringernationalCode":"12","remark":null},{"userId":"4bb325e74bfd11e7b48df44d30a3e396","name":"777","sizeParem":"","sizeName":"","productImg":"upload/car/deec1326e0fa09d978e079abff93435c.jpg","type":"0","drawPrice":"2","brand":"sdf","drawType":"0","pricePlatform":1,"StringernationalCode":"666","remark":null},{"userId":"8d5c61e04bff11e7b48df44d30a3e396","name":"edc","sizeParem":"111","sizeName":"123","productImg":"upload/car/deec1326e0fa09d978e079abff93435c.jpg","type":"0","drawPrice":"222","brand":"code","drawType":"0","pricePlatform":111,"StringernationalCode":"222","remark":"Sdfsd"},{"userId":"e4fe73eb4bfc11e7b48df44d30a3e396","name":"test","sizeParem":"456,567","sizeName":"123,234","productImg":"upload/car/5928dcdf9a66815673eb9202aae6269c.jpg","type":"1","drawPrice":"456","brand":"asd","drawType":"1","pricePlatform":123,"StringernationalCode":"6969","remark":null}],"count":4}
     */

    private DataBean data;

    public static GoodListBean parse(String json) throws AppException {
        GoodListBean res = new GoodListBean();
        try{
            res = gson.fromJson(json, GoodListBean.class);
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
         * jfomGoods : [{"userId":"02c074da4bf711e7b48df44d30a3e396","name":"34","sizeParem":"456","sizeName":"123","productImg":"upload/car/4ff7a83fbf91fb1f8cbd38237c934409.jpg","type":"0","drawPrice":"11","brand":"sdf","drawType":"0","pricePlatform":123,"StringernationalCode":"12","remark":null},{"userId":"4bb325e74bfd11e7b48df44d30a3e396","name":"777","sizeParem":"","sizeName":"","productImg":"upload/car/deec1326e0fa09d978e079abff93435c.jpg","type":"0","drawPrice":"2","brand":"sdf","drawType":"0","pricePlatform":1,"StringernationalCode":"666","remark":null},{"userId":"8d5c61e04bff11e7b48df44d30a3e396","name":"edc","sizeParem":"111","sizeName":"123","productImg":"upload/car/deec1326e0fa09d978e079abff93435c.jpg","type":"0","drawPrice":"222","brand":"code","drawType":"0","pricePlatform":111,"StringernationalCode":"222","remark":"Sdfsd"},{"userId":"e4fe73eb4bfc11e7b48df44d30a3e396","name":"test","sizeParem":"456,567","sizeName":"123,234","productImg":"upload/car/5928dcdf9a66815673eb9202aae6269c.jpg","type":"1","drawPrice":"456","brand":"asd","drawType":"1","pricePlatform":123,"StringernationalCode":"6969","remark":null}]
         * count : 4
         */

        private String count;
        private List<JfomGoodsBean> jfomGoods;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<JfomGoodsBean> getJfomGoods() {
            return jfomGoods;
        }

        public void setJfomGoods(List<JfomGoodsBean> jfomGoods) {
            this.jfomGoods = jfomGoods;
        }

        public static class JfomGoodsBean extends NetResult{
            /**
             * userId : 02c074da4bf711e7b48df44d30a3e396
             * name : 34
             * sizeParem : 456
             * sizeName : 123
             * productImg : upload/car/4ff7a83fbf91fb1f8cbd38237c934409.jpg
             * type : 0
             * drawPrice : 11
             * brand : sdf
             * drawType : 0
             * pricePlatform : 123
             * StringernationalCode : 12
             * remark : null
             */

            private String id;
            private String name;
            private String sizeParem;
            private String sizeName;
            private String productImg;
            private String type;
            private String drawPrice;
            private String brand;
            private String drawType;
            private String pricePlatform;
            private String StringernationalCode;
            private String remark;
            private String internationalCode ;
            private String safetyInventory;
            private String stock;

            public String getSafetyInventory() {
                return safetyInventory;
            }

            public void setSafetyInventory(String safetyInventory) {
                this.safetyInventory = safetyInventory;
            }

            public String getInternationalCode()
            {
                return internationalCode;
            }

            public void setInternationalCode(String internationalCode)
            {
                this.internationalCode = internationalCode;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSizeParem() {
                return sizeParem;
            }

            public void setSizeParem(String sizeParem) {
                this.sizeParem = sizeParem;
            }

            public String getSizeName() {
                return sizeName;
            }

            public void setSizeName(String sizeName) {
                this.sizeName = sizeName;
            }

            public String getProductImg() {
                return productImg;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDrawPrice() {
                return drawPrice;
            }

            public void setDrawPrice(String drawPrice) {
                this.drawPrice = drawPrice;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getDrawType() {
                return drawType;
            }

            public void setDrawType(String drawType) {
                this.drawType = drawType;
            }

            public String getPricePlatform() {
                return pricePlatform;
            }

            public void setPricePlatform(String pricePlatform) {
                this.pricePlatform = pricePlatform;
            }

            public String getStringernationalCode() {
                return StringernationalCode;
            }

            public void setStringernationalCode(String StringernationalCode) {
                this.StringernationalCode = StringernationalCode;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }
        }
    }
}
