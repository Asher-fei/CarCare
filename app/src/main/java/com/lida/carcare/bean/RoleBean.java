package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 角色权限
 * Created by WeiQingFeng on 2017/5/17.
 */

public class RoleBean extends NetResult {

    private List<DataBean> data;

    /**
     * data : {"sb":"56.0,45.0,25.0,"}
     */

    public static RoleBean parse(String json) throws AppException {
        RoleBean res = new RoleBean();
        try{
            res = gson.fromJson(json, RoleBean.class);
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

        private String realname;
        private String roleId;
        private boolean isSelect = false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }
    }
}
