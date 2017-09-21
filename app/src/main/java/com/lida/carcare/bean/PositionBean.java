package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 职位列表
 * Created by WeiQingFeng on 2017/5/17.
 */

public class PositionBean extends NetResult {

    private List<DataBean> data;

    /**
     * data : {"sb":"56.0,45.0,25.0,"}
     */

    public static PositionBean parse(String json) throws AppException {
        PositionBean res = new PositionBean();
        try{
            res = gson.fromJson(json, PositionBean.class);
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
         * realname : demo
         * roleId : 402880e74d75c4dd014d75d3c5f40001
         */

        private String departname;
        private String id;
        private boolean isSelect = false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
