package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 选择客户列表
 * Created by WeiQingFeng on 2017/5/17.
 */

public class CustomerMainBean extends NetResult {

    private List<DataBean> data;

    public static CustomerMainBean parse(String json) throws AppException {
        CustomerMainBean res = new CustomerMainBean();
        try{
            res = gson.fromJson(json, CustomerMainBean.class);
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
         * userId : 402880fa5c2509b4015c2529bb610018
         * customerName : A客户
         * sex : 0 男 1 女
         * customerLevelName : A级客户
         */

        private String id;
        private String customerName;
        private String mobilePhoneNo;
        private String customerLevelName;
        private String sex;
        private String nickname;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getMobilePhoneNo() {
            return mobilePhoneNo;
        }

        public void setMobilePhoneNo(String mobilePhoneNo) {
            this.mobilePhoneNo = mobilePhoneNo;
        }

        public String getCustomerLevelName() {
            return customerLevelName;
        }

        public void setCustomerLevelName(String customerLevelName) {
            this.customerLevelName = customerLevelName;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
