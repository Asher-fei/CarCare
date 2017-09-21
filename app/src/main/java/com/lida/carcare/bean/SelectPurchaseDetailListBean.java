package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class SelectPurchaseDetailListBean extends NetResult {

    private List<DataBean> data;

    public static SelectPurchaseDetailListBean parse(String json) throws AppException {
        SelectPurchaseDetailListBean res = new SelectPurchaseDetailListBean();
        try {
            res = gson.fromJson(json, SelectPurchaseDetailListBean.class);
        } catch (JsonSyntaxException e) {
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

    public static class DataBean {
        /**
         * id : 223035935cca11e791dcf44d30a3e396
         * supplierId : c09409f255b011e78e92f44d30a3e396
         * logisticsCompany : 经济
         * logisticsCode : 222
         * remark : 
         * price : null
         * purchaseDate : 2017-06-29 20:54:48
         * billCode : 1498740888481
         * supplierCompany : null
         * jfomGoods : [{"id":"0f2644c15cba11e791dcf44d30a3e396","name":"测试","productImg":"upload/car/036493c5ec95d1d8be67069aee320053.jpg","priceStandardBid":60,"internationalCode":"55"},{"id":"d0f454755cb611e791dcf44d30a3e396","name":"玻璃水","productImg":"upload/car/5cd152d48f108c4ed4b202a140dd6f85.jpg","priceStandardBid":20,"internationalCode":"123456"}]
         * carPurchaseRecords : [{"carPurchaseId":"223035935cca11e791dcf44d30a3e396","goodsId":"0f2644c15cba11e791dcf44d30a3e396","stock":"2"},{"carPurchaseId":"223035935cca11e791dcf44d30a3e396","goodsId":"d0f454755cb611e791dcf44d30a3e396","stock":"2"}]
         */

        private String id;
        private String supplierId;
        private String logisticsCompany;
        private String logisticsCode;
        private String remark;
        private String price;
        private String purchaseDate;
        private String billCode;
        private String supplierCompany;
        private List<JfomGoodsBean> jfomGoods;
        private List<CarPurchaseRecordsBean> carPurchaseRecords;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSupplierId() {
            return supplierId;
        }

        public void setSupplierId(String supplierId) {
            this.supplierId = supplierId;
        }

        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public String getLogisticsCode() {
            return logisticsCode;
        }

        public void setLogisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public String getBillCode() {
            return billCode;
        }

        public void setBillCode(String billCode) {
            this.billCode = billCode;
        }

        public String getSupplierCompany() {
            return supplierCompany;
        }

        public void setSupplierCompany(String supplierCompany) {
            this.supplierCompany = supplierCompany;
        }

        public List<JfomGoodsBean> getJfomGoods() {
            return jfomGoods;
        }

        public void setJfomGoods(List<JfomGoodsBean> jfomGoods) {
            this.jfomGoods = jfomGoods;
        }

        public List<CarPurchaseRecordsBean> getCarPurchaseRecords() {
            return carPurchaseRecords;
        }

        public void setCarPurchaseRecords(List<CarPurchaseRecordsBean> carPurchaseRecords) {
            this.carPurchaseRecords = carPurchaseRecords;
        }

        public static class JfomGoodsBean {
            /**
             * id : 0f2644c15cba11e791dcf44d30a3e396
             * name : 测试
             * productImg : upload/car/036493c5ec95d1d8be67069aee320053.jpg
             * priceStandardBid : 60
             * internationalCode : 55
             */

            private String id;
            private String name;
            private String productImg;
            private String priceStandardBid;
            private String internationalCode;
            private String storageId;

            public String getStorageId() {
                return storageId;
            }

            public void setStorageId(String storageId) {
                this.storageId = storageId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProductImg() {
                return productImg;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
            }

            public String getPriceStandardBid() {
                return priceStandardBid;
            }

            public void setPriceStandardBid(String priceStandardBid) {
                this.priceStandardBid = priceStandardBid;
            }

            public String getInternationalCode() {
                return internationalCode;
            }

            public void setInternationalCode(String internationalCode) {
                this.internationalCode = internationalCode;
            }
        }

        public static class CarPurchaseRecordsBean {
            /**
             * carPurchaseId : 223035935cca11e791dcf44d30a3e396
             * goodsId : 0f2644c15cba11e791dcf44d30a3e396
             * stock : 2
             */

            private String id;
            private String carPurchaseId;
            private String goodsId;
            private String stock;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCarPurchaseId() {
                return carPurchaseId;
            }

            public void setCarPurchaseId(String carPurchaseId) {
                this.carPurchaseId = carPurchaseId;
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
        }
    }
}
