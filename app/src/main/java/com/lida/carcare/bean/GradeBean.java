package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 获取用户级别
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GradeBean extends NetResult {

    private List<DataBean> data;

    public static GradeBean parse(String json) throws AppException {
        GradeBean res = new GradeBean();
        try{
            res = gson.fromJson(json, GradeBean.class);
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
         * userId : 402880fa5c140cda015c1423ee9c0005
         * customerLevelName : A级客户
         */

        private String id;
        private String customerLevelName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomerLevelName() {
            return customerLevelName;
        }

        public void setCustomerLevelName(String customerLevelName) {
            this.customerLevelName = customerLevelName;
        }
    }
}
