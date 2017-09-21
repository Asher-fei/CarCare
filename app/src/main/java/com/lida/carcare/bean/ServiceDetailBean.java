package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 商品详情
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServiceDetailBean extends NetResult {

    /**
     * data : {"userId":"01aecada428211e7bcc1b8975ae21ffe","name":"hah","brand":"aodi","productType":"A03","sizeParem":"f","sizeName":"f","type":1,"priceStandardSell":1000,"remark":"dssgsgs","productImg":"fdrgdfhgf"}
     */

    private DataBean data;

    public static ServiceDetailBean parse(String json) throws AppException {
        ServiceDetailBean res = new ServiceDetailBean();
        try{
            res = gson.fromJson(json, ServiceDetailBean.class);
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

        private String id;
        private String name;
        private String serviceImg;
        private String serviceType;
        private String servicePrice;
        private String remark;
        private String drawType;
        private String drawPrice;
        private String typeName;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public String getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(String servicePrice) {
            this.servicePrice = servicePrice;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }


    }
}
