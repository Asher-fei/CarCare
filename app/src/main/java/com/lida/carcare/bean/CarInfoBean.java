package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class CarInfoBean extends NetResult
{

    /**
     * data : {"id":"013b78a5406911e796d4f44d30a3e396","carNo":"999","conclusion":"皮带待定，下次保养时再约","carRemind":[{"maturityDate":"2017-06-29","reminderDetails":"提醒","daysRemaining":"9"}],"entryTime":"2017-05-14 00:00:00","consumptionAmount":100,"mileage":"10000","oiltable":"402880ec5c3917fc015c3917fce70000","deliveryTime":"2017-05-15 00:00:00","isWait":"1","remark":null,"count":1,"customerName":"A客户","mobilePhoneNo":"15915773578","brandCode":"A101","brandName":"奥迪","carInspect":[{"entryName":"皮带","checkRemarks":"无开裂","detectionOpinion":"0"},{"entryName":"轮胎","checkRemarks":"还好","detectionOpinion":"1"}],"customerId":"402880fa5c2509b4015c2529bb610018","userName":"admin"}
     */

    private DataBean data;

    public static CarInfoBean parse(String json) throws AppException
    {
        CarInfoBean res = new CarInfoBean();
        try
        {
            res = gson.fromJson(json, CarInfoBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 013b78a5406911e796d4f44d30a3e396
         * carNo : 999
         * conclusion : 皮带待定，下次保养时再约
         * carRemind : [{"maturityDate":"2017-06-29","reminderDetails":"提醒","daysRemaining":"9"}]
         * entryTime : 2017-05-14 00:00:00
         * consumptionAmount : 100
         * mileage : 10000
         * oiltable : 402880ec5c3917fc015c3917fce70000
         * deliveryTime : 2017-05-15 00:00:00
         * isWait : 1
         * remark : null
         * count : 1
         * customerName : A客户
         * mobilePhoneNo : 15915773578
         * brandCode : A101
         * brandName : 奥迪
         * carInspect : [{"entryName":"皮带","checkRemarks":"无开裂","detectionOpinion":"0"},{"entryName":"轮胎","checkRemarks":"还好","detectionOpinion":"1"}]
         * customerId : 402880fa5c2509b4015c2529bb610018
         * userName : admin
         */

        private String id;
        private String carNo;
        private String conclusion;
        private String entryTime;
        private String consumptionAmount;
        private String mileage;
        private String oiltable;
        private String deliveryTime;
        private String isWait;
        private String remark;
        private String count;
        private String customerName;
        private String mobilePhoneNo;
        private String brandCode;
        private String brandName;
        private String customerId;
        private String userName;
        private String carId;
        private List<CarRemindBean> carRemind;
        private List<CarInspectBean> carInspect;

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getConclusion() {
            return conclusion;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public String getConsumptionAmount() {
            return consumptionAmount;
        }

        public void setConsumptionAmount(String consumptionAmount) {
            this.consumptionAmount = consumptionAmount;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getOiltable() {
            return oiltable;
        }

        public void setOiltable(String oiltable) {
            this.oiltable = oiltable;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getIsWait() {
            return isWait;
        }

        public void setIsWait(String isWait) {
            this.isWait = isWait;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<CarRemindBean> getCarRemind() {
            return carRemind;
        }

        public void setCarRemind(List<CarRemindBean> carRemind) {
            this.carRemind = carRemind;
        }

        public List<CarInspectBean> getCarInspect() {
            return carInspect;
        }

        public void setCarInspect(List<CarInspectBean> carInspect) {
            this.carInspect = carInspect;
        }

        public static class CarRemindBean {
            /**
             * maturityDate : 2017-06-29
             * reminderDetails : 提醒
             * daysRemaining : 9
             */

            private String maturityDate;
            private String reminderDetails;
            private String daysRemaining;

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
        }

        public static class CarInspectBean {
            /**
             * entryName : 皮带
             * checkRemarks : 无开裂
             * detectionOpinion : 0
             */

            private String entryName;
            private String checkRemarks;
            private String detectionOpinion;

            public String getEntryName() {
                return entryName;
            }

            public void setEntryName(String entryName) {
                this.entryName = entryName;
            }

            public String getCheckRemarks() {
                return checkRemarks;
            }

            public void setCheckRemarks(String checkRemarks) {
                this.checkRemarks = checkRemarks;
            }

            public String getDetectionOpinion() {
                return detectionOpinion;
            }

            public void setDetectionOpinion(String detectionOpinion) {
                this.detectionOpinion = detectionOpinion;
            }
        }
    }
}
