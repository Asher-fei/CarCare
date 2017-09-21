package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 领料人
 * Created by Administrator on 2017/6/14.
 */

public class CurrentStoreUserBean extends NetResult {

    private List<DataBean> data;

    public static CurrentStoreUserBean parse(String json) throws AppException {
        CurrentStoreUserBean res = new CurrentStoreUserBean();
        try {
            res = gson.fromJson(json, CurrentStoreUserBean.class);
        } catch (JsonSyntaxException e) {
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
         * userId : 0c7c6faa76de495d97f7217bdbd8db1b
         * userName : 阿涛
         */

        private String userId;
        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
