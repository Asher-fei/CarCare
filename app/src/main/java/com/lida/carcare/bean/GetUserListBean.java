package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 派工的时候，根据服务选择相对应部门的员工
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GetUserListBean extends NetResult {

    private List<DataBean> data;

    public static GetUserListBean parse(String json) throws AppException {
        GetUserListBean res = new GetUserListBean();
        try{
            res = gson.fromJson(json, GetUserListBean.class);
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
         * userId : 460326d177984c86b0067103e3a99541
         * username : zxc
         */

        private String id;
        private String username;
        private boolean isSelect = false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
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
