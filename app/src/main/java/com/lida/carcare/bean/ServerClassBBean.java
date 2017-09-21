package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 技师二级分类
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServerClassBBean extends NetResult {

    private List<DataBean> data;

    public static ServerClassBBean parse(String json) throws AppException {
        ServerClassBBean res = new ServerClassBBean();
        try{
            res = gson.fromJson(json, ServerClassBBean.class);
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
         * userId : 402880fa5c1f82d8015c1f8474aa0005
         * rolename : 666
         */

        private String id;
        private String rolename;

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
    }
}
