package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 提醒设置查询
 * Created by Administrator on 2017/7/12.
 */

public class CarRemindSetBean extends NetResult {


    /**
     * status : 1
     * data : {"maintenanceState":1,"maintenanceDate":2,"maintenanceRemindDate":1,"insuranceState":1,"insuranceDate":1,"insuranceRemindDate":8,"annualState":1,"annualDate":1,"annualRemindDate":12,"inspectionState":1,"inspectionDate":1,"inspectionRemindDate":12}
     */

    private DataBean data;

    public static CarRemindSetBean parse(String json) throws AppException
    {
        CarRemindSetBean res = new CarRemindSetBean();
        try
        {
            res = gson.fromJson(json, CarRemindSetBean.class);
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

    public static class DataBean extends NetResult{
        /**
         * maintenanceState : 1
         * maintenanceDate : 2
         * maintenanceRemindDate : 1
         * insuranceState : 1
         * insuranceDate : 1
         * insuranceRemindDate : 8
         * annualState : 1
         * annualDate : 1
         * annualRemindDate : 12
         * inspectionState : 1
         * inspectionDate : 1
         * inspectionRemindDate : 12
         */

        private int maintenanceState;
        private int maintenanceDate;
        private int maintenanceRemindDate;
        private int insuranceState;
        private int insuranceDate;
        private int insuranceRemindDate;
        private int annualState;
        private int annualDate;
        private int annualRemindDate;
        private int inspectionState;
        private int inspectionDate;
        private int inspectionRemindDate;

        public int getMaintenanceState() {
            return maintenanceState;
        }

        public void setMaintenanceState(int maintenanceState) {
            this.maintenanceState = maintenanceState;
        }

        public int getMaintenanceDate() {
            return maintenanceDate;
        }

        public void setMaintenanceDate(int maintenanceDate) {
            this.maintenanceDate = maintenanceDate;
        }

        public int getMaintenanceRemindDate() {
            return maintenanceRemindDate;
        }

        public void setMaintenanceRemindDate(int maintenanceRemindDate) {
            this.maintenanceRemindDate = maintenanceRemindDate;
        }

        public int getInsuranceState() {
            return insuranceState;
        }

        public void setInsuranceState(int insuranceState) {
            this.insuranceState = insuranceState;
        }

        public int getInsuranceDate() {
            return insuranceDate;
        }

        public void setInsuranceDate(int insuranceDate) {
            this.insuranceDate = insuranceDate;
        }

        public int getInsuranceRemindDate() {
            return insuranceRemindDate;
        }

        public void setInsuranceRemindDate(int insuranceRemindDate) {
            this.insuranceRemindDate = insuranceRemindDate;
        }

        public int getAnnualState() {
            return annualState;
        }

        public void setAnnualState(int annualState) {
            this.annualState = annualState;
        }

        public int getAnnualDate() {
            return annualDate;
        }

        public void setAnnualDate(int annualDate) {
            this.annualDate = annualDate;
        }

        public int getAnnualRemindDate() {
            return annualRemindDate;
        }

        public void setAnnualRemindDate(int annualRemindDate) {
            this.annualRemindDate = annualRemindDate;
        }

        public int getInspectionState() {
            return inspectionState;
        }

        public void setInspectionState(int inspectionState) {
            this.inspectionState = inspectionState;
        }

        public int getInspectionDate() {
            return inspectionDate;
        }

        public void setInspectionDate(int inspectionDate) {
            this.inspectionDate = inspectionDate;
        }

        public int getInspectionRemindDate() {
            return inspectionRemindDate;
        }

        public void setInspectionRemindDate(int inspectionRemindDate) {
            this.inspectionRemindDate = inspectionRemindDate;
        }
    }
}
