package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 在店车辆
 * Created by WeiQingFeng on 2017/5/17.
 */

public class CarInShopBean extends NetResult {

    private List<DataBean> data;

    public static CarInShopBean parse(String json) throws AppException {
        CarInShopBean res = new CarInShopBean();
        try{
            res = gson.fromJson(json, CarInShopBean.class);
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

    public static class DataBean extends NetResult implements Serializable{
        /**
         * userId : 013b78a5406911e796d4f44d30a3e396
         * carNo : 999
         * goodsProject : 测试美容,
         * maintainProject : 四轮定位,
         * repairProject :
         * refitProject : D服务,
         * userName : admin
         * status : 0
         * customerName : A客户
         * phone : null
         */

        private String entryTime;
        private String id;
        private String carNo;
        private String goodsProject;
        private String maintainProject;
        private String repairProject;
        private String refitProject;
        private String userName;
        private String workStatus;
        private String customerName;
        private String phone;
        private String consumptionAmount;
        private String name;
        private String customerId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getConsumptionAmount() {
            return consumptionAmount;
        }

        public void setConsumptionAmount(String consumptionAmount) {
            this.consumptionAmount = consumptionAmount;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }
    }
}
