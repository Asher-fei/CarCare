package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 商品详情
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GoodDetailBean extends NetResult {

    /**
     * data : {"userId":"01aecada428211e7bcc1b8975ae21ffe","name":"hah","brand":"aodi","productType":"A03","sizeParem":"f","sizeName":"f","type":1,"priceStandardSell":1000,"remark":"dssgsgs","productImg":"fdrgdfhgf"}
     */

    private DataBean data;

    public static GoodDetailBean parse(String json) throws AppException {
        GoodDetailBean res = new GoodDetailBean();
        try{
            res = gson.fromJson(json, GoodDetailBean.class);
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
         * userId : 01aecada428211e7bcc1b8975ae21ffe
         * name : hah
         * brand : aodi
         * productType : A03
         * sizeParem : f
         * sizeName : f
         * type : 1
         * priceStandardSell : 1000
         * remark : dssgsgs
         * productImg : fdrgdfhgf
         */

        private String id;
        private String name;
        private String brand;
        private String sizeParem;
        private String sizeName;
        private String type;
        private String pricePlatform;
        private String remark;
        private String productImg;
        private String internationalCode;
        private String drawType;
        private String drawPrice;
        private String typeName;
        private String productType;
        private String safetyInventory;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
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

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPricePlatform() {
            return pricePlatform;
        }

        public void setPricePlatform(String pricePlatform) {
            this.pricePlatform = pricePlatform;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getInternationalCode() {
            return internationalCode;
        }

        public void setInternationalCode(String internationalCode) {
            this.internationalCode = internationalCode;
        }

        public String getDrawType() {
            return drawType;
        }

        public void setDrawType(String drawType) {
            this.drawType = drawType;
        }

        public String getDrawPrice() {
            return drawPrice;
        }

        public void setDrawPrice(String drawPrice) {
            this.drawPrice = drawPrice;
        }

        public String getSafetyInventory() {
            return safetyInventory;
        }

        public void setSafetyInventory(String safetyInventory) {
            this.safetyInventory = safetyInventory;
        }
    }
}
