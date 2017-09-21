package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 采购详情
 * Created by Administrator on 2017/6/14.
 */

public class PurchaseDetailBean extends NetResult {

    /**
     * data : {"id":"223035935cca11e791dcf44d30a3e396","supplierId":"c09409f255b011e78e92f44d30a3e396","logisticsCompany":"经济","logisticsCode":"222","purchaseDate":"2017-06-29 20:54:48","billCode":"1498740888481","jfomGoods":[{"id":"0f2644c15cba11e791dcf44d30a3e396","name":"测试","productImg":"upload/car/036493c5ec95d1d8be67069aee320053.jpg","priceStandardBid":30,"internationalCode":"55"},{"id":"d0f454755cb611e791dcf44d30a3e396","name":"玻璃水","productImg":"upload/car/5cd152d48f108c4ed4b202a140dd6f85.jpg","priceStandardBid":20,"internationalCode":"123456"}],"carPurchaseRecords":[{"carPurchaseId":"223035935cca11e791dcf44d30a3e396","goodsId":"0f2644c15cba11e791dcf44d30a3e396","stock":"2"},{"carPurchaseId":"223035935cca11e791dcf44d30a3e396","goodsId":"d0f454755cb611e791dcf44d30a3e396","stock":"2"}]}
     */

    private DataBean data;

    public static PurchaseDetailBean parse(String json) throws AppException {
        PurchaseDetailBean res = new PurchaseDetailBean();
        try {
            res = gson.fromJson(json, PurchaseDetailBean.class);
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

    public static class DataBean {
        /**
         * id : 223035935cca11e791dcf44d30a3e396
         * supplierId : c09409f255b011e78e92f44d30a3e396
         * logisticsCompany : 经济
         * logisticsCode : 222
         * purchaseDate : 2017-06-29 20:54:48
         * billCode : 1498740888481
         * jfomGoods : [{"id":"0f2644c15cba11e791dcf44d30a3e396","name":"测试","productImg":"upload/car/036493c5ec95d1d8be67069aee320053.jpg","priceStandardBid":30,"internationalCode":"55"},{"id":"d0f454755cb611e791dcf44d30a3e396","name":"玻璃水","productImg":"upload/car/5cd152d48f108c4ed4b202a140dd6f85.jpg","priceStandardBid":20,"internationalCode":"123456"}]
         * carPurchaseRecords : [{"carPurchaseId":"223035935cca11e791dcf44d30a3e396","goodsId":"0f2644c15cba11e791dcf44d30a3e396","stock":"2"},{"carPurchaseId":"223035935cca11e791dcf44d30a3e396","goodsId":"d0f454755cb611e791dcf44d30a3e396","stock":"2"}]
         */

        private String id;
        private String supplierId;
        private String logisticsCompany;
        private String logisticsCode;
        private String supplierCompany;
        private String purchaseDate;
        private String billCode;
        private String remark;
        private List<JfomGoodsBean> jfomGoods;
        private String price;
        private List<CarPurchaseRecordsBean> carPurchaseRecords;

        public String getSupplierCompany() {
            return supplierCompany;
        }

        public void setSupplierCompany(String supplierCompany) {
            this.supplierCompany = supplierCompany;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public static class JfomGoodsBean {
            /**
             * id : 0f2644c15cba11e791dcf44d30a3e396
             * name : 测试
             * productImg : upload/car/036493c5ec95d1d8be67069aee320053.jpg
             * priceStandardBid : 30
             * internationalCode : 55
             */

            private String id;
            private String name;
            private String productImg;
            private String priceStandardBid;
            private String internationalCode;

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

            private String carPurchaseId;
            private String goodsId;
            private String stock;
            private String returnPrice;

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

            public String getReturnPrice() {
                return returnPrice;
            }

            public void setReturnPrice(String returnPrice) {
                this.returnPrice = returnPrice;
            }
        }
    }
}
