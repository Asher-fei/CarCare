package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 查询订货品类
 * Created by xkr on 2017/7/24.
 */

public class OrderClassificationBean extends NetResult {


    /**
     * status : 1
     * data : [{"id":"dfdgdfg","name":"2","shopId":"c5afd3c9530c11e7907600163e0877ba"},{"id":"dfdsgfdg","name":"1","shopId":"c5afd3c9530c11e7907600163e0877ba"}]
     */

    private List<DataBean> data;

    public static OrderClassificationBean parse(String json) throws AppException {
        OrderClassificationBean res = new OrderClassificationBean();
        try{
            res = gson.fromJson(json, OrderClassificationBean.class);
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
         * id : dfdgdfg
         * name : 2
         * shopId : c5afd3c9530c11e7907600163e0877ba
         */

        private String id;
        private String name;
        private String shopId;
        private String classPath;

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

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getClassPath() {
            return classPath;
        }

        public void setClassPath(String classPath) {
            this.classPath = classPath;
        }
    }
}
