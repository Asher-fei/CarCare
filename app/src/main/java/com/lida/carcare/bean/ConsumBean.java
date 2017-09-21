package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 车辆消费记录
 * Created by Administrator on 2017/6/14.
 */

public class ConsumBean extends NetResult
{
    /**
     * data : {"documents":[{"id":"138d390a4cb911e783fdf44d30a3e396","consumptionAmount":772,"deliveryTime":"2017-06-09 05:12","projectName":"qqq,qqq1,123,"}],"recordTntoTheShop":1,"historicalConsumptionAmount":772,"theNumberOfTimesStringhisTear":1,"theAmountOfConsumptionThisYear":772}
     */

    private DataBean data;

    public static ConsumBean parse(String json) throws AppException
    {
        ConsumBean res = new ConsumBean();
        try
        {
            res = gson.fromJson(json, ConsumBean.class);
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
         * documents : [{"id":"138d390a4cb911e783fdf44d30a3e396","consumptionAmount":772,"deliveryTime":"2017-06-09 05:12","projectName":"qqq,qqq1,123,"}]
         * recordTntoTheShop : 1
         * historicalConsumptionAmount : 772
         * theNumberOfTimesStringhisTear : 1
         * theAmountOfConsumptionThisYear : 772
         */

        private String recordTntoTheShop;
        private String historicalConsumptionAmount;
        private String theNumberOfTimesInThisTear;
        private String theAmountOfConsumptionThisYear;
        private List<DocumentsBean> documents;

        public String getRecordTntoTheShop() {
            return recordTntoTheShop;
        }

        public void setRecordTntoTheShop(String recordTntoTheShop) {
            this.recordTntoTheShop = recordTntoTheShop;
        }

        public String getHistoricalConsumptionAmount() {
            return historicalConsumptionAmount;
        }

        public void setHistoricalConsumptionAmount(String historicalConsumptionAmount) {
            this.historicalConsumptionAmount = historicalConsumptionAmount;
        }

        public String getTheNumberOfTimesInThisTear() {
            return theNumberOfTimesInThisTear;
        }

        public void setTheNumberOfTimesInThisTear(String theNumberOfTimesInThisTear) {
            this.theNumberOfTimesInThisTear = theNumberOfTimesInThisTear;
        }

        public String getTheAmountOfConsumptionThisYear() {
            return theAmountOfConsumptionThisYear;
        }

        public void setTheAmountOfConsumptionThisYear(String theAmountOfConsumptionThisYear) {
            this.theAmountOfConsumptionThisYear = theAmountOfConsumptionThisYear;
        }

        public List<DocumentsBean> getDocuments() {
            return documents;
        }

        public void setDocuments(List<DocumentsBean> documents) {
            this.documents = documents;
        }

        public static class DocumentsBean {
            /**
             * id : 138d390a4cb911e783fdf44d30a3e396
             * consumptionAmount : 772
             * deliveryTime : 2017-06-09 05:12
             * projectName : qqq,qqq1,123,
             */

            private String id;
            private String consumptionAmount;
            private String deliveryTime;
            private String projectName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getConsumptionAmount() {
                return consumptionAmount;
            }

            public void setConsumptionAmount(String consumptionAmount) {
                this.consumptionAmount = consumptionAmount;
            }

            public String getDeliveryTime() {
                return deliveryTime;
            }

            public void setDeliveryTime(String deliveryTime) {
                this.deliveryTime = deliveryTime;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }
        }
    }
}
