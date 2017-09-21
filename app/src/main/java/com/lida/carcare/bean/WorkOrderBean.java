package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 施工单
 * Created by WeiQingFeng on 2017/5/17.
 */

public class WorkOrderBean extends NetResult {


    private List<DataBean> data;

    public static WorkOrderBean parse(String json) throws AppException {
        WorkOrderBean res = new WorkOrderBean();
        try{
            res = gson.fromJson(json, WorkOrderBean.class);
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
         * userId : 0dc91ad2534011e78e92f44d30a3e396
         * carNo : 冀W23456
         * entryTime : 2017-06-17 17:33:43
         * consumptionAmount : 610
         * goodsProject : 洗车,
         * maintainProject : 美孚金装一号,
         * repairProject :
         * refitProject :
         * workStatus : 0
         * customerName : null
         * name : null
         * mobile : null
         * carDispatches : []
         */

        private String id;
        private String carNo;
        private String entryTime;
        private String consumptionAmount;
        private String goodsProject;
        private String maintainProject;
        private String repairProject;
        private String refitProject;
        private String workStatus;
        private String customerName;
        private String name;
        private String mobile;
        private List<CarDispatches> carDispatches;

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

        public String getGoodsProject() {
            return goodsProject;
        }

        public void setGoodsProject(String goodsProject) {
            this.goodsProject = goodsProject;
        }

        public String getMaintainProject() {
            return maintainProject;
        }

        public void setMaintainProject(String maintainProject) {
            this.maintainProject = maintainProject;
        }

        public String getRepairProject() {
            return repairProject;
        }

        public void setRepairProject(String repairProject) {
            this.repairProject = repairProject;
        }

        public String getRefitProject() {
            return refitProject;
        }

        public void setRefitProject(String refitProject) {
            this.refitProject = refitProject;
        }

        public String getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(String workStatus) {
            this.workStatus = workStatus;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public List<CarDispatches> getCarDispatches() {
            return carDispatches;
        }

        public void setCarDispatches(List<CarDispatches> carDispatches) {
            this.carDispatches = carDispatches;
        }
    }

    public static class CarDispatches {
        private String implementName;

        public String getImplementName() {
            return implementName;
        }

        public void setImplementName(String implementName) {
            this.implementName = implementName;
        }
    }
}
