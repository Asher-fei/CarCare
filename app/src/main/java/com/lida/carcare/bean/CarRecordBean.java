package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 检测记录
 * Created by Administrator on 2017/6/14.
 */

public class CarRecordBean extends NetResult
{

    /**
     * data : {"maturityDate":"2017-06-29","reminderDetails":"提醒","daysRemaining":"9","conclusion":"皮带待定，下次保养时再约","carMaintainRecord":[{"mileage":"10000","maintainDate":"2017-05-15 00:00:00"}],"carInspects":[{"entryName":"皮带","checkRemarks":"无开裂"},{"entryName":"轮胎","checkRemarks":"还好"}]}
     */

    private DataBean data;

    public static CarRecordBean parse(String json) throws AppException
    {
        CarRecordBean res = new CarRecordBean();
        try
        {
            res = gson.fromJson(json, CarRecordBean.class);
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
         * maturityDate : 2017-06-29
         * reminderDetails : 提醒
         * daysRemaining : 9
         * conclusion : 皮带待定，下次保养时再约
         * carMaintainRecord : [{"mileage":"10000","maintainDate":"2017-05-15 00:00:00"}]
         * carInspects : [{"entryName":"皮带","checkRemarks":"无开裂"},{"entryName":"轮胎","checkRemarks":"还好"}]
         */

        private String maturityDate;
        private String reminderDetails;
        private String daysRemaining;
        private String conclusion;
        private List<CarMaintainRecordBean> carMaintainRecord;
        private List<CarInspectsBean> carInspects;

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

        public String getConclusion() {
            return conclusion;
        }

        public void setConclusion(String conclusion) {
            this.conclusion = conclusion;
        }

        public List<CarMaintainRecordBean> getCarMaintainRecord() {
            return carMaintainRecord;
        }

        public void setCarMaintainRecord(List<CarMaintainRecordBean> carMaintainRecord) {
            this.carMaintainRecord = carMaintainRecord;
        }

        public List<CarInspectsBean> getCarInspects() {
            return carInspects;
        }

        public void setCarInspects(List<CarInspectsBean> carInspects) {
            this.carInspects = carInspects;
        }

        public static class CarMaintainRecordBean {
            /**
             * mileage : 10000
             * maintainDate : 2017-05-15 00:00:00
             */

            private String mileage;
            private String maintainDate;

            public String getMileage() {
                return mileage;
            }

            public void setMileage(String mileage) {
                this.mileage = mileage;
            }

            public String getMaintainDate() {
                return maintainDate;
            }

            public void setMaintainDate(String maintainDate) {
                this.maintainDate = maintainDate;
            }
        }

        public static class CarInspectsBean {
            /**
             * entryName : 皮带
             * checkRemarks : 无开裂
             */

            private String entryName;
            private String checkRemarks;

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
        }
    }
}
