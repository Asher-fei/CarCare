package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 商品搜索结果页
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GoodSearchResultBean extends NetResult {

    private List<DataBean> data;

    public static GoodSearchResultBean parse(String json) throws AppException {
        GoodSearchResultBean res = new GoodSearchResultBean();
        try{
            res = gson.fromJson(json, GoodSearchResultBean.class);
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
         * id : 6f8cdc0e5e1011e7be1cf44d30a3e396
         * name : 七一
         * sizeParem : 哦
         * sizeName : 嗯
         * productImg : upload/car/3e3963bf859668e8634a03351024f639.jpg
         * type : 1
         * drawPrice : 10
         * brand : 莫大
         * drawType : 0
         * productType : A01A01
         * priceStandardBid : null
         * pricePlatform : 36
         * safetyInventory : 30
         * internationalCode : 582
         * remark : 因为
         * typeName : 本田机油格
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
        private String productType;
        private String priceStandardBid;
        private String pricePlatform;
        private String safetyInventory;
        private String internationalCode;
        private String remark;
        private String typeName;

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

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getPriceStandardBid() {
            return priceStandardBid;
        }

        public void setPriceStandardBid(String priceStandardBid) {
            this.priceStandardBid = priceStandardBid;
        }

        public String getPricePlatform() {
            return pricePlatform;
        }

        public void setPricePlatform(String pricePlatform) {
            this.pricePlatform = pricePlatform;
        }

        public String getSafetyInventory() {
            return safetyInventory;
        }

        public void setSafetyInventory(String safetyInventory) {
            this.safetyInventory = safetyInventory;
        }

        public String getInternationalCode() {
            return internationalCode;
        }

        public void setInternationalCode(String internationalCode) {
            this.internationalCode = internationalCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

    }
}
