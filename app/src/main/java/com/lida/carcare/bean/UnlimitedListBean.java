package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 无限卡列表
 * Created by Administrator on 2017/7/4.
 */

public class UnlimitedListBean extends NetResult{


    /**
     * status : 1
     * data : [{"id":"a8a83ae15edc11e7838700163e0877ba","onceCardNo":"jklsdfsdf","onceCardName":"lisa","mobile":"15899959195","carNo":"","onceCardType":{"cardName":"至尊卡"},"onceCardProjectLists":[{"projectName":"单手打蜡","projectCount":"无限次"},{"projectName":"清洗发动机","projectCount":"无限次"}]}]
     */

    private List<DataBean> data;

    public static UnlimitedListBean parse(String json) throws AppException {
        UnlimitedListBean res = new UnlimitedListBean();
        try{
            res = gson.fromJson(json, UnlimitedListBean.class);
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
         * id : a8a83ae15edc11e7838700163e0877ba
         * onceCardNo : jklsdfsdf
         * onceCardName : lisa
         * mobile : 15899959195
         * carNo :
         * onceCardType : {"cardName":"至尊卡"}
         * onceCardProjectLists : [{"projectName":"单手打蜡","projectCount":"无限次"},{"projectName":"清洗发动机","projectCount":"无限次"}]
         */

        private String id;
        private String onceCardNo;
        private String onceCardName;
        private String mobile;
        private String carNo;
        private OnceCardTypeBean onceCardType;
        private List<OnceCardProjectListsBean> onceCardProjectLists;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOnceCardNo() {
            return onceCardNo;
        }

        public void setOnceCardNo(String onceCardNo) {
            this.onceCardNo = onceCardNo;
        }

        public String getOnceCardName() {
            return onceCardName;
        }

        public void setOnceCardName(String onceCardName) {
            this.onceCardName = onceCardName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public OnceCardTypeBean getOnceCardType() {
            return onceCardType;
        }

        public void setOnceCardType(OnceCardTypeBean onceCardType) {
            this.onceCardType = onceCardType;
        }

        public List<OnceCardProjectListsBean> getOnceCardProjectLists() {
            return onceCardProjectLists;
        }

        public void setOnceCardProjectLists(List<OnceCardProjectListsBean> onceCardProjectLists) {
            this.onceCardProjectLists = onceCardProjectLists;
        }

        public static class OnceCardTypeBean extends NetResult{
            /**
             * cardName : 至尊卡
             */

            private String cardName;

            public String getCardName() {
                return cardName;
            }

            public void setCardName(String cardName) {
                this.cardName = cardName;
            }
        }

        public static class OnceCardProjectListsBean extends NetResult{
            /**
             * projectName : 单手打蜡
             * projectCount : 无限次
             */

            private String projectName;
            private String projectCount;

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public String getProjectCount() {
                return projectCount;
            }

            public void setProjectCount(String projectCount) {
                this.projectCount = projectCount;
            }
        }
    }
}
