package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 服务一级分类
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServiceBean extends NetResult {

    private List<DataBean> data;

    public static ServiceBean parse(String json) throws AppException {
        ServiceBean res = new ServiceBean();
        try{
            res = gson.fromJson(json, ServiceBean.class);
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
         * userId : 402880fa5c1916bb015c19196db6000e
         * code : A05A01
         * name : 洗车
         */

        private String id;
        private String code;
        private String name;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

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

        @Override
        public String toString() {
            return "Data{" +
                    "userId='" + id + '\'' +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ServiceBean{" +
                "data=" + data +
                '}';
    }
}
