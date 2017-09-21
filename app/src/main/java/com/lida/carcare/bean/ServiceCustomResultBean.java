package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 服务自定义返回
 * Created by xkr on 2017/9/16.
 */

public class ServiceCustomResultBean extends NetResult {


    /**
     * status : 1
     * data : {"id":"4c857d639aa211e7895ff44d30a3e396","name":"测试1","serviceType":"b5962bc175aa11e78473f44d30a3e396","servicePrice":100,"remark":null,"serviceImg":null,"serviceState":null,"drawType":"1","drawPrice":"20","shopId":"1c2799fb4f1911e79108f44d30a3e396","typeName":null,"frequency":"1","code":"A01"}
     */

    private DataBean data;

    public static ServiceCustomResultBean parse(String json) throws AppException {
        ServiceCustomResultBean res = new ServiceCustomResultBean();
        try{
            res = gson.fromJson(json, ServiceCustomResultBean.class);
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
         * id : 4c857d639aa211e7895ff44d30a3e396
         * name : 测试1
         * serviceType : b5962bc175aa11e78473f44d30a3e396
         * servicePrice : 100
         * remark : null
         * serviceImg : null
         * serviceState : null
         * drawType : 1
         * drawPrice : 20
         * shopId : 1c2799fb4f1911e79108f44d30a3e396
         * typeName : null
         * frequency : 1
         * code : A01
         */

        private String id;
        private String name;
        private String serviceType;
        private String servicePrice;
        private Object remark;
        private Object serviceImg;
        private Object serviceState;
        private String drawType;
        private String drawPrice;
        private String shopId;
        private Object typeName;
        private String frequency;
        private String code;

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

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(Object serviceImg) {
            this.serviceImg = serviceImg;
        }

        public Object getServiceState() {
            return serviceState;
        }

        public void setServiceState(Object serviceState) {
            this.serviceState = serviceState;
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

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public Object getTypeName() {
            return typeName;
        }

        public void setTypeName(Object typeName) {
            this.typeName = typeName;
        }

        public String getFrequency() {
            return frequency;
        }

        public void setFrequency(String frequency) {
            this.frequency = frequency;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
