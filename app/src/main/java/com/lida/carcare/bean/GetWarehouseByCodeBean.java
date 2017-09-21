package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 通过code获取库存
 * Created by Administrator on 2017/6/14.
 */

public class GetWarehouseByCodeBean extends NetResult {

    /**
     * data : {"count":1,"carWarehouse":[{"id":"2ff7a8b25c7811e79a4bb8975ae21ffe","productId":"0f9204d94cc211e783fdf44d30a3e396","stock":"81","repertoryId":"e3a7056c5b1611e79a4bb8975ae21ffe","productName":"1","productImg":"dfdfgdg.jsp","productInternationalCode":"2222222","repertoryName":"25"}]}
     */

    private DataBean data;

    public static GetWarehouseByCodeBean parse(String json) throws AppException {
        GetWarehouseByCodeBean res = new GetWarehouseByCodeBean();
        try {
            res = gson.fromJson(json, GetWarehouseByCodeBean.class);
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
         * count : 1
         * carWarehouse : [{"id":"2ff7a8b25c7811e79a4bb8975ae21ffe","productId":"0f9204d94cc211e783fdf44d30a3e396","stock":"81","repertoryId":"e3a7056c5b1611e79a4bb8975ae21ffe","productName":"1","productImg":"dfdfgdg.jsp","productInternationalCode":"2222222","repertoryName":"25"}]
         */

        private String count;
        private List<CarWarehouseBean> carWarehouse;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<CarWarehouseBean> getCarWarehouse() {
            return carWarehouse;
        }

        public void setCarWarehouse(List<CarWarehouseBean> carWarehouse) {
            this.carWarehouse = carWarehouse;
        }

        public static class CarWarehouseBean extends NetResult{
            /**
             * id : 2ff7a8b25c7811e79a4bb8975ae21ffe
             * productId : 0f9204d94cc211e783fdf44d30a3e396
             * stock : 81
             * repertoryId : e3a7056c5b1611e79a4bb8975ae21ffe
             * productName : 1
             * productImg : dfdfgdg.jsp
             * productInternationalCode : 2222222
             * repertoryName : 25
             */

            private String id;
            private String productId;
            private String stock;
            private String repertoryId;
            private String productName;
            private String productImg;
            private String productInternationalCode;
            private String repertoryName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getRepertoryId() {
                return repertoryId;
            }

            public void setRepertoryId(String repertoryId) {
                this.repertoryId = repertoryId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getProductImg() {
                return productImg;
            }

            public void setProductImg(String productImg) {
                this.productImg = productImg;
            }

            public String getProductInternationalCode() {
                return productInternationalCode;
            }

            public void setProductInternationalCode(String productInternationalCode) {
                this.productInternationalCode = productInternationalCode;
            }

            public String getRepertoryName() {
                return repertoryName;
            }

            public void setRepertoryName(String repertoryName) {
                this.repertoryName = repertoryName;
            }
        }
    }
}
