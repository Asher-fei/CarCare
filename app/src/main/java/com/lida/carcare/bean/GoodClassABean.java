package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 商品一级分类
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GoodClassABean extends NetResult {


    private List<DataBean> data;

    public static GoodClassABean parse(String json) throws AppException {
        GoodClassABean res = new GoodClassABean();
        try{
            res = gson.fromJson(json, GoodClassABean.class);
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

    public static class DataBean implements Serializable{
        /**
         * userId : 402880ec5c37f955015c3825b01f000d
         * name : C商品
         */

        private String id;
        private String name;
        private String code;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        @Override
        public String toString() {
            return "Data{" +
                    "userId='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodBean{" +
                "data=" + data +
                '}';
    }
}
