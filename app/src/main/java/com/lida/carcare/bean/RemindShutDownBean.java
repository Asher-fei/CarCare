package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 已关闭提醒列表
 * Created by Administrator on 2017/7/5.
 */

public class RemindShutDownBean extends NetResult {


    /**
     * status : 1
     * data : [{"id":"1e92952b56ee11e79ebbf44d30a3e396","maturityDate":"2017-06-22","reminderDetails":"我么的","daysRemaining":"已关闭","carNo":"999","customerName":null,"mobilePhoneNo":null,"brandCode":"A101","brandName":"奥迪","shopId":"1","carId":"013b78a5406911e796d4f44d30a3e396"}]
     */

    private List<DataBean> data;

    public static RemindShutDownBean parse(String json) throws AppException
    {
        RemindShutDownBean res = new RemindShutDownBean();
        try
        {
            res = gson.fromJson(json, RemindShutDownBean.class);
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
         * id : 1e92952b56ee11e79ebbf44d30a3e396
         * maturityDate : 2017-06-22
         * reminderDetails : 我么的
         * daysRemaining : 已关闭
         * carNo : 999
         * customerName : null
         * mobilePhoneNo : null
         * brandCode : A101
         * brandName : 奥迪
         * shopId : 1
         * carId : 013b78a5406911e796d4f44d30a3e396
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
        private String carId;

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

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }
    }
}
