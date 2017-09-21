package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 技师一级分类
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServerClassABean extends NetResult {

    private List<DataBean> data;

    public static ServerClassABean parse(String json) throws AppException {
        ServerClassABean res = new ServerClassABean();
        try{
            res = gson.fromJson(json, ServerClassABean.class);
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
         * userId : 402880e74d75c4dd014d75d3c5f40001
         * rolename : demo
         * count : 6
         */

        private String id;
        private String rolename;
        private int count;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
