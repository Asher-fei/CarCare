package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by zdf on 2017/6/29.
 */

public class OutIntHistoryDetailsBean extends NetResult {
    /**
     * data : {"id":"201706281498619749996","remark":"测试1","createdate":"2017-06-28 11:15:49","state":"2","carEntryRecordsGood":[{"goodNum":"1","name":"ehbdbb"},{"goodNum":"1","name":"通用语"}],"price":200}
     */

    private DataBean data;

    public static OutIntHistoryDetailsBean parse(String json) throws AppException {
        OutIntHistoryDetailsBean res = new OutIntHistoryDetailsBean();
        try {
            res = gson.fromJson(json, OutIntHistoryDetailsBean.class);
        } catch (JsonSyntaxException e) {
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


    public static class DataBean extends NetResult {
        /**
         * id : 201706281498619749996
         * remark : 测试1
         * createdate : 2017-06-28 11:15:49
         * state : 2
         * carEntryRecordsGood : [{"goodNum":"1","name":"ehbdbb"},{"goodNum":"1","name":"通用语"}]
         * price : 200
         */

        private String id;
        private String remark;
        private String createdate;
        private String state;
        private String price;
        private List<CarEntryRecordsGoodBean> carEntryRecordsGood;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<CarEntryRecordsGoodBean> getCarEntryRecordsGood() {
            return carEntryRecordsGood;
        }

        public void setCarEntryRecordsGood(List<CarEntryRecordsGoodBean> carEntryRecordsGood) {
            this.carEntryRecordsGood = carEntryRecordsGood;
        }

        public static class CarEntryRecordsGoodBean extends NetResult {
            /**
             * goodNum : 1
             * name : ehbdbb
             */

            private String goodNum;
            private String name;

            public String getGoodNum() {
                return goodNum;
            }

            public void setGoodNum(String goodNum) {
                this.goodNum = goodNum;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
