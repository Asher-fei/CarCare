package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 输入里程数时获取上次的保养记录的服务（商品）
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GoodBean extends NetResult {


    private List<DataBean> data;

    public static GoodBean parse(String json) throws AppException {
        GoodBean res = new GoodBean();
        try{
            res = gson.fromJson(json, GoodBean.class);
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

    public static class DataBean {
        /**
         * userId : 402880ec5c37f955015c3825b01f000d
         * name : C商品
         */

        private String id;
        private String name;

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
