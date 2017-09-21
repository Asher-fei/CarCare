package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 智能提醒
 * Created by Administrator on 2017/6/14.
 */

public class RemindBean extends NetResult {

    private List<DataBean> data;

    public static RemindBean parse(String json) throws AppException
    {
        RemindBean res = new RemindBean();
        try
        {
            res = gson.fromJson(json, RemindBean.class);
        } catch (JsonSyntaxException e)
        {
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
         * id : 1
         * maturityDate : 2017-06-29
         * reminderDetails : 提醒
         * daysRemaining : 9
         * carNo : 999
         * customerName : 卢生
         * mobilePhoneNo :
         * brandCode : A101
         * brandName : 奥迪
         * shopId : 1
         */

        private String id;
        private String maturityDate;
        private String reminderDetails;
        private String daysRemaining;
        private String carNo;
        private String customerName;
        private String mobilePhoneNo;
        private String brandCode;
        private String brandName;
        private String shopId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMaturityDate() {
            return maturityDate;
        }

        public void setMaturityDate(String maturityDate) {
            this.maturityDate = maturityDate;
        }

        public String getReminderDetails() {
            return reminderDetails;
        }

        public void setReminderDetails(String reminderDetails) {
            this.reminderDetails = reminderDetails;
        }

        public String getDaysRemaining() {
            return daysRemaining;
        }

        public void setDaysRemaining(String daysRemaining) {
            this.daysRemaining = daysRemaining;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
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

        public String getBrandCode() {
            return brandCode;
        }

        public void setBrandCode(String brandCode) {
            this.brandCode = brandCode;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }
    }
}
