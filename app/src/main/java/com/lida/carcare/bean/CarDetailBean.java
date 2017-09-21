package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 车辆详情
 * Created by WeiQingFeng on 2017/5/17.
 */

public class CarDetailBean extends NetResult {

    /**
     * data : {"userId":"013b78a5406911e796d4f44d30a3e396","carNo":"999","goodsProject":"测试美容,","maintainProject":"四轮定位,","repairProject":"","refitProject":"D服务,","userName":"admin","status":"0","remark":null,"customerName":null,"phone":null,"car":{"carFrameNo":"110","createDate":"2017-05-15 00:00:00","compulsoryDate":"2017-06-15 00:00:00","endDate":"2017-05-25 00:00:00","carPrice":"100w","engineNo":"1234568dd"},"customer":{"customerName":"A客户","mobilePhoneNo":"15915773578"},"customerLevel":{"customerLevelName":"A级客户"},"promoter":{"promoterName":"111"},"consumeCard":[{"consumeCardNo":"123456","consumeCardName":"test","residualAmount":1500}]}
     */

    private DataBean data;

    public static CarDetailBean parse(String json) throws AppException {
        CarDetailBean res = new CarDetailBean();
        try{
            res = gson.fromJson(json, CarDetailBean.class);
        }catch (JsonSyntaxException e){
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
         * userId : 013b78a5406911e796d4f44d30a3e396
         * carNo : 999
         * goodsProject : 测试美容,
         * maintainProject : 四轮定位,
         * repairProject :
         * refitProject : D服务,
         * userName : admin
         * status : 0
         * remark : null
         * customerName : null
         * phone : null
         * car : {"carFrameNo":"110","createDate":"2017-05-15 00:00:00","compulsoryDate":"2017-06-15 00:00:00","endDate":"2017-05-25 00:00:00","carPrice":"100w","engineNo":"1234568dd"}
         * customer : {"customerName":"A客户","mobilePhoneNo":"15915773578"}
         * customerLevel : {"customerLevelName":"A级客户"}
         * promoter : {"promoterName":"111"}
         * consumeCard : [{"consumeCardNo":"123456","consumeCardName":"test","residualAmount":1500}]
         */

        private String id;
        private String carNo;
        private String goodsProject;
        private String maintainProject;
        private String repairProject;
        private String refitProject;
        private String userName;
        private String workStatus;
        private String remark;
        private String customerName;
        private String phone;
        private CarBean car;
        private CustomerBean customer;
        private CustomerLevelBean customerLevel;
        private PromoterBean promoter;
        private List<ConsumeCardBean> consumeCard;
        private Brand brand;
        private String refitNumber;
        private String maintainNumber;
        private String goodNumber;
        private String repairNumber;
        private String goodPrice;
        private String refitPrice;
        private String maintainPrice;
        private String repairPrice;


        public Brand getBrand() {
            return brand;
        }

        public void setBrand(Brand brand) {
            this.brand = brand;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
            this.car = car;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public CustomerLevelBean getCustomerLevel() {
            return customerLevel;
        }

        public void setCustomerLevel(CustomerLevelBean customerLevel) {
            this.customerLevel = customerLevel;
        }

        public PromoterBean getPromoter() {
            return promoter;
        }

        public void setPromoter(PromoterBean promoter) {
            this.promoter = promoter;
        }

        public List<ConsumeCardBean> getConsumeCard() {
            return consumeCard;
        }

        public void setConsumeCard(List<ConsumeCardBean> consumeCard) {
            this.consumeCard = consumeCard;
        }

        public String getRefitNumber() {
            return refitNumber;
        }

        public void setRefitNumber(String refitNumber) {
            this.refitNumber = refitNumber;
        }

        public String getMaintainNumber() {
            return maintainNumber;
        }

        public void setMaintainNumber(String maintainNumber) {
            this.maintainNumber = maintainNumber;
        }

        public String getGoodNumber() {
            return goodNumber;
        }

        public void setGoodNumber(String goodNumber) {
            this.goodNumber = goodNumber;
        }

        public String getRepairNumber() {
            return repairNumber;
        }

        public void setRepairNumber(String repairNumber) {
            this.repairNumber = repairNumber;
        }


        public String getGoodPrice() {
            return goodPrice;
        }

        public void setGoodPrice(String goodPrice) {
            this.goodPrice = goodPrice;
        }

        public String getRefitPrice() {
            return refitPrice;
        }

        public void setRefitPrice(String refitPrice) {
            this.refitPrice = refitPrice;
        }

        public String getMaintainPrice() {
            return maintainPrice;
        }

        public void setMaintainPrice(String maintainPrice) {
            this.maintainPrice = maintainPrice;
        }

        public String getRepairPrice() {
            return repairPrice;
        }

        public void setRepairPrice(String repairPrice) {
            this.repairPrice = repairPrice;
        }

        public static class Brand{
            private String brandName;

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }
        }

        public static class CarBean {
            /**
             * carFrameNo : 110
             * createDate : 2017-05-15 00:00:00
             * compulsoryDate : 2017-06-15 00:00:00
             * endDate : 2017-05-25 00:00:00
             * carPrice : 100w
             * engineNo : 1234568dd
             */

            private String carFrameNo;
            private String createDate;
            private String compulsoryDate;
            private String endDate;
            private String carPrice;
            private String engineNo;

            public String getCarFrameNo() {
                return carFrameNo;
            }

            public void setCarFrameNo(String carFrameNo) {
                this.carFrameNo = carFrameNo;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCompulsoryDate() {
                return compulsoryDate;
            }

            public void setCompulsoryDate(String compulsoryDate) {
                this.compulsoryDate = compulsoryDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getCarPrice() {
                return carPrice;
            }

            public void setCarPrice(String carPrice) {
                this.carPrice = carPrice;
            }

            public String getEngineNo() {
                return engineNo;
            }

            public void setEngineNo(String engineNo) {
                this.engineNo = engineNo;
            }
        }

        public static class CustomerBean {
            /**
             * customerName : A客户
             * mobilePhoneNo : 15915773578
             */

            private String customerName;
            private String mobilePhoneNo;

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
        }

        public static class CustomerLevelBean {
            /**
             * customerLevelName : A级客户
             */

            private String customerLevelName;

            public String getCustomerLevelName() {
                return customerLevelName;
            }

            public void setCustomerLevelName(String customerLevelName) {
                this.customerLevelName = customerLevelName;
            }
        }

        public static class PromoterBean {
            /**
             * promoterName : 111
             */

            private String promoterName;

            public String getPromoterName() {
                return promoterName;
            }

            public void setPromoterName(String promoterName) {
                this.promoterName = promoterName;
            }
        }

        public static class ConsumeCardBean {
            /**
             * consumeCardNo : 123456
             * consumeCardName : test
             * residualAmount : 1500
             */

            private String consumeCardNo;
            private String consumeCardName;
            private int residualAmount;

            public String getConsumeCardNo() {
                return consumeCardNo;
            }

            public void setConsumeCardNo(String consumeCardNo) {
                this.consumeCardNo = consumeCardNo;
            }

            public String getConsumeCardName() {
                return consumeCardName;
            }

            public void setConsumeCardName(String consumeCardName) {
                this.consumeCardName = consumeCardName;
            }

            public int getResidualAmount() {
                return residualAmount;
            }

            public void setResidualAmount(int residualAmount) {
                this.residualAmount = residualAmount;
            }
        }
    }
}
