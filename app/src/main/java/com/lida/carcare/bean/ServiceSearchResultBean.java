package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 服务搜索结果页
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServiceSearchResultBean extends NetResult {

    private List<DataBean> data;

    public static ServiceSearchResultBean parse(String json) throws AppException {
        ServiceSearchResultBean res = new ServiceSearchResultBean();
        try {
            res = gson.fromJson(json, ServiceSearchResultBean.class);
        } catch (JsonSyntaxException e) {
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


    public static class DataBean extends NetResult {
        /**
         * userId : 8f462cd045db11e7bcc1b8975ae21ffe
         * name : ff
         * priceStandardSell : 1000
         * serviceImg : dsfds
         */

        private String id;
        private String name;
        private String serviceType;
        private String servicePrice;
        private String remark;
        private String drawType;
        private String serviceImg;
        private String typeName;
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

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}