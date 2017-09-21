package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 盘点详情
 * Created by Administrator on 2017/7/6.
 */

public class InventoryVerificationDetailBean extends NetResult {


    /**
     * status : 1
     * data : [{"id":"a60f73576a9511e78788b8975ae21ffe","shoutId":"1500257651711","shoutPeason":"1号店员工大修","shoutStock":"120","shoutDate":"2017-07-17 10:14:11","shoutOk":"58","shoutNo":null,"carWarehouse":[{"stock":"60","productName":"发动机四件套","productInternationalCode":"123456"},{"stock":"60","productName":"通用语","productInternationalCode":"45863586"}],"shoutProductRecord":[{"id":null,"goodsId":"38f6553b5eb811e7838700163e0877ba","stock":"60","recordShoutOk":"25","recordShoutNo":null},{"id":null,"goodsId":"9ce23b9b559811e78e92f44d30a3e396","stock":"60","recordShoutOk":"33","recordShoutNo":null}],"carRepertory":[{"repertoryName":"存储仓","sumAcountNo":"0","sumAcountOk":"35"},{"repertoryName":"技术","sumAcountNo":"0","sumAcountOk":"27"}],"acount":"2"}]
     */

    private List<DataBean> data;

    public static InventoryVerificationDetailBean parse(String json) throws AppException {
        InventoryVerificationDetailBean res = new InventoryVerificationDetailBean();
        try{
            res = gson.fromJson(json, InventoryVerificationDetailBean.class);
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
         * id : a60f73576a9511e78788b8975ae21ffe
         * shoutId : 1500257651711
         * shoutPeason : 1号店员工大修
         * shoutStock : 120
         * shoutDate : 2017-07-17 10:14:11
         * shoutOk : 58
         * shoutNo : null
         * carWarehouse : [{"stock":"60","productName":"发动机四件套","productInternationalCode":"123456"},{"stock":"60","productName":"通用语","productInternationalCode":"45863586"}]
         * shoutProductRecord : [{"id":null,"goodsId":"38f6553b5eb811e7838700163e0877ba","stock":"60","recordShoutOk":"25","recordShoutNo":null},{"id":null,"goodsId":"9ce23b9b559811e78e92f44d30a3e396","stock":"60","recordShoutOk":"33","recordShoutNo":null}]
         * carRepertory : [{"repertoryName":"存储仓","sumAcountNo":"0","sumAcountOk":"35"},{"repertoryName":"技术","sumAcountNo":"0","sumAcountOk":"27"}]
         * acount : 2
         */

        private String id;
        private String shoutId;
        private String shoutPeason;
        private String shoutStock;
        private String shoutDate;
        private String shoutOk;
        private String shoutNo;
        private String acount;
        private List<CarWarehouseBean> carWarehouse;
        private List<ShoutProductRecordBean> shoutProductRecord;
        private List<CarRepertoryBean> carRepertory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShoutId() {
            return shoutId;
        }

        public void setShoutId(String shoutId) {
            this.shoutId = shoutId;
        }

        public String getShoutPeason() {
            return shoutPeason;
        }

        public void setShoutPeason(String shoutPeason) {
            this.shoutPeason = shoutPeason;
        }

        public String getShoutStock() {
            return shoutStock;
        }

        public void setShoutStock(String shoutStock) {
            this.shoutStock = shoutStock;
        }

        public String getShoutDate() {
            return shoutDate;
        }

        public void setShoutDate(String shoutDate) {
            this.shoutDate = shoutDate;
        }

        public String getShoutOk() {
            return shoutOk;
        }

        public void setShoutOk(String shoutOk) {
            this.shoutOk = shoutOk;
        }

        public String getShoutNo() {
            return shoutNo;
        }

        public void setShoutNo(String shoutNo) {
            this.shoutNo = shoutNo;
        }

        public String getAcount() {
            return acount;
        }

        public void setAcount(String acount) {
            this.acount = acount;
        }

        public List<CarWarehouseBean> getCarWarehouse() {
            return carWarehouse;
        }

        public void setCarWarehouse(List<CarWarehouseBean> carWarehouse) {
            this.carWarehouse = carWarehouse;
        }

        public List<ShoutProductRecordBean> getShoutProductRecord() {
            return shoutProductRecord;
        }

        public void setShoutProductRecord(List<ShoutProductRecordBean> shoutProductRecord) {
            this.shoutProductRecord = shoutProductRecord;
        }

        public List<CarRepertoryBean> getCarRepertory() {
            return carRepertory;
        }

        public void setCarRepertory(List<CarRepertoryBean> carRepertory) {
            this.carRepertory = carRepertory;
        }

        public static class CarWarehouseBean extends NetResult{
            /**
             * stock : 60
             * productName : 发动机四件套
             * productInternationalCode : 123456
             */

            private String stock;
            private String productName;
            private String productInternationalCode;

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductInternationalCode() {
                return productInternationalCode;
            }

            public void setProductInternationalCode(String productInternationalCode) {
                this.productInternationalCode = productInternationalCode;
            }
        }

        public static class ShoutProductRecordBean extends NetResult{
            /**
             * id : null
             * goodsId : 38f6553b5eb811e7838700163e0877ba
             * stock : 60
             * recordShoutOk : 25
             * recordShoutNo : null
             */

            private String id;
            private String goodsId;
            private String stock;
            private String recordShoutOk;
            private String recordShoutNo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getRecordShoutOk() {
                return recordShoutOk;
            }

            public void setRecordShoutOk(String recordShoutOk) {
                this.recordShoutOk = recordShoutOk;
            }

            public String getRecordShoutNo() {
                return recordShoutNo;
            }

            public void setRecordShoutNo(String recordShoutNo) {
                this.recordShoutNo = recordShoutNo;
            }
        }

        public static class CarRepertoryBean extends NetResult{
            /**
             * repertoryName : 存储仓
             * sumAcountNo : 0
             * sumAcountOk : 35
             */

            private String repertoryName;
            private String sumAcountNo;
            private String sumAcountOk;

            public String getRepertoryName() {
                return repertoryName;
            }

            public void setRepertoryName(String repertoryName) {
                this.repertoryName = repertoryName;
            }

            public String getSumAcountNo() {
                return sumAcountNo;
            }

            public void setSumAcountNo(String sumAcountNo) {
                this.sumAcountNo = sumAcountNo;
            }

            public String getSumAcountOk() {
                return sumAcountOk;
            }

            public void setSumAcountOk(String sumAcountOk) {
                this.sumAcountOk = sumAcountOk;
            }
        }
    }
}
