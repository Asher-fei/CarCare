package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 次卡详情
 * Created by Administrator on 2017/7/1.
 */

public class OpenTimeCardDetailBean extends NetResult {

    /**
     * status : 1
     * data : {"id":"ece1f9a25e5811e7be1cf44d30a3e396","cardName":"666","cardPrice":"6969","cardRestrictCarno":"1","onceCardProjectList":[{"projectName":"维修项目1","projectCount":"10"},{"projectName":"维修项目2","projectCount":"6"},{"projectName":"保养项目2","projectCount":"3"}]}
     */

    private DataBean data;

    public static OpenTimeCardDetailBean parse(String json) throws AppException {
        OpenTimeCardDetailBean res = new OpenTimeCardDetailBean();
        try{
            res = gson.fromJson(json, OpenTimeCardDetailBean.class);
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
         * id : ece1f9a25e5811e7be1cf44d30a3e396
         * cardName : 666
         * cardPrice : 6969
         * cardRestrictCarno : 1
         * onceCardProjectList : [{"projectName":"维修项目1","projectCount":"10"},{"projectName":"维修项目2","projectCount":"6"},{"projectName":"保养项目2","projectCount":"3"}]
         */

        private String id;
        private String cardName;
        private String cardPrice;
        private String cardRestrictCarno;
        private List<OnceCardProjectListBean> onceCardProjectList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardPrice() {
            return cardPrice;
        }

        public void setCardPrice(String cardPrice) {
            this.cardPrice = cardPrice;
        }

        public String getCardRestrictCarno() {
            return cardRestrictCarno;
        }

        public void setCardRestrictCarno(String cardRestrictCarno) {
            this.cardRestrictCarno = cardRestrictCarno;
        }

        public List<OnceCardProjectListBean> getOnceCardProjectList() {
            return onceCardProjectList;
        }

        public void setOnceCardProjectList(List<OnceCardProjectListBean> onceCardProjectList) {
            this.onceCardProjectList = onceCardProjectList;
        }

        public static class OnceCardProjectListBean {
            /**
             * projectName : 维修项目1
             * projectCount : 10
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
