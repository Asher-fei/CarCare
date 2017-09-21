package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 客户详情
 * Created by Administrator on 2017/7/17.
 */

public class CustomerDetailBean extends NetResult {


    /**
     * status : 1
     * data : {"id":"3c92bc74687011e7a483f44d30a3e396","customerName":"saaa","customerLevelNo":"402880fa5c140cda015c1423ee9c0005","sex":"0","mobilePhoneNo":"13536487634","address":null,"customerSource":"402880fa5c24c3ed015c24d549e50017","customerType":"0","remark":"xxgfx","customerLevel":{"id":"402880fa5c140cda015c1423ee9c0005","customerLevelName":"A级客户"},"promoter":{"id":"402880fa5c24c3ed015c24d549e50017","promoterName":"推广员2"},"customerContactList":[{"id":"af2e4efe5fc611e7838700163e0877ba","contactName":"1","contactSex":"2","contactMobile":"15915440000","contactRelation":"q"},{"id":"af35059c5fc611e7838700163e0877ba","contactName":"3","contactSex":"4","contactMobile":"15915330000","contactRelation":"w"}],"birthDates":"2017-07-14"}
     */

    private DataBean data;

    public static CustomerDetailBean parse(String json) throws AppException {
        CustomerDetailBean res = new CustomerDetailBean();
        try{
            res = gson.fromJson(json, CustomerDetailBean.class);
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

    public static class DataBean extends NetResult{
        /**
         * id : 3c92bc74687011e7a483f44d30a3e396
         * customerName : saaa
         * customerLevelNo : 402880fa5c140cda015c1423ee9c0005
         * sex : 0
         * mobilePhoneNo : 13536487634
         * address : null
         * customerSource : 402880fa5c24c3ed015c24d549e50017
         * customerType : 0
         * remark : xxgfx
         * customerLevel : {"id":"402880fa5c140cda015c1423ee9c0005","customerLevelName":"A级客户"}
         * promoter : {"id":"402880fa5c24c3ed015c24d549e50017","promoterName":"推广员2"}
         * customerContactList : [{"id":"af2e4efe5fc611e7838700163e0877ba","contactName":"1","contactSex":"2","contactMobile":"15915440000","contactRelation":"q"},{"id":"af35059c5fc611e7838700163e0877ba","contactName":"3","contactSex":"4","contactMobile":"15915330000","contactRelation":"w"}]
         * birthDates : 2017-07-14
         */

        private String id;
        private String customerName;
        private String customerLevelNo;
        private String sex;
        private String mobilePhoneNo;
        private String customerCompany;
        private String customerSource;
        private String customerType;
        private String remark;
        private CustomerLevelBean customerLevel;
        private PromoterBean promoter;
        private String birthDates;
        private String nickname;
        private List<CustomerContactListBean> customerContactList;

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

        public String getCustomerLevelNo() {
            return customerLevelNo;
        }

        public void setCustomerLevelNo(String customerLevelNo) {
            this.customerLevelNo = customerLevelNo;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobilePhoneNo() {
            return mobilePhoneNo;
        }

        public void setMobilePhoneNo(String mobilePhoneNo) {
            this.mobilePhoneNo = mobilePhoneNo;
        }

        public String getCustomerCompany() {
            return customerCompany;
        }

        public void setCustomerCompany(String customerCompany) {
            this.customerCompany = customerCompany;
        }

        public String getCustomerSource() {
            return customerSource;
        }

        public void setCustomerSource(String customerSource) {
            this.customerSource = customerSource;
        }

        public String getCustomerType() {
            return customerType;
        }

        public void setCustomerType(String customerType) {
            this.customerType = customerType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getBirthDates() {
            return birthDates;
        }

        public void setBirthDates(String birthDates) {
            this.birthDates = birthDates;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public List<CustomerContactListBean> getCustomerContactList() {
            return customerContactList;
        }

        public void setCustomerContactList(List<CustomerContactListBean> customerContactList) {
            this.customerContactList = customerContactList;
        }

        public static class CustomerLevelBean extends NetResult{
            /**
             * id : 402880fa5c140cda015c1423ee9c0005
             * customerLevelName : A级客户
             */

            private String id;
            private String customerLevelName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCustomerLevelName() {
                return customerLevelName;
            }

            public void setCustomerLevelName(String customerLevelName) {
                this.customerLevelName = customerLevelName;
            }
        }

        public static class PromoterBean extends NetResult{
            /**
             * id : 402880fa5c24c3ed015c24d549e50017
             * promoterName : 推广员2
             */

            private String id;
            private String promoterName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPromoterName() {
                return promoterName;
            }

            public void setPromoterName(String promoterName) {
                this.promoterName = promoterName;
            }
        }

        public static class CustomerContactListBean extends NetResult{
            /**
             * id : af2e4efe5fc611e7838700163e0877ba
             * contactName : 1
             * contactSex : 2
             * contactMobile : 15915440000
             * contactRelation : q
             */

            private String id;
            private String contactName;
            private String contactSex;
            private String contactMobile;
            private String contactRelation;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContactName() {
                return contactName;
            }

            public void setContactName(String contactName) {
                this.contactName = contactName;
            }

            public String getContactSex() {
                return contactSex;
            }

            public void setContactSex(String contactSex) {
                this.contactSex = contactSex;
            }

            public String getContactMobile() {
                return contactMobile;
            }

            public void setContactMobile(String contactMobile) {
                this.contactMobile = contactMobile;
            }

            public String getContactRelation() {
                return contactRelation;
            }

            public void setContactRelation(String contactRelation) {
                this.contactRelation = contactRelation;
            }
        }
    }
}
