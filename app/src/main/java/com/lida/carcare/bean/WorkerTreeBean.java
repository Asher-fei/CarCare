package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 员工树形列表
 * Created by WeiQingFeng on 2017/5/17.
 */

public class WorkerTreeBean extends NetResult {

    private List<DataBean> data;

    public static WorkerTreeBean parse(String json) throws AppException {
        WorkerTreeBean res = new WorkerTreeBean();
        try{
            res = gson.fromJson(json, WorkerTreeBean.class);
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
         * userId : 402880e447e99cf10147e9a03b320003
         * departname : 保养
         * tsBaseUser : [{"userId":"460326d177984c86b0067103e3a99541","username":"zxc"},{"userId":"8a8ab0b246dc81120146dc8181950052","username":"admin"},{"userId":"488462e1073d4062826228b24fd2ba78","username":"xiaowang"},{"userId":"167c64840eaf43328a59bd7b139f2cb8","username":"aaa"}]
         */

        private String id;
        private String departname;
        private List<TsBaseUserBean> tsBaseUser;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDepartname() {
            return departname;
        }

        public void setDepartname(String departname) {
            this.departname = departname;
        }

        public List<TsBaseUserBean> getTsBaseUser() {
            return tsBaseUser;
        }

        public void setTsBaseUser(List<TsBaseUserBean> tsBaseUser) {
            this.tsBaseUser = tsBaseUser;
        }

        public static class TsBaseUserBean {
            /**
             * userId : 460326d177984c86b0067103e3a99541
             * username : zxc
             */

            private String id;
            private String iCode;
            private String username;
            private boolean select;

            public String getiCode()
            {
                return iCode;
            }

            public void setiCode(String iCode)
            {
                this.iCode = iCode;
            }

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
