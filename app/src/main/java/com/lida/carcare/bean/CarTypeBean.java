package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by WeiQingFeng on 2017/5/17.
 */

public class CarTypeBean extends NetResult {

    private List<DataBean> data;

    public static CarTypeBean parse(String json) throws AppException {
        CarTypeBean res = new CarTypeBean();
        try{
            res = gson.fromJson(json, CarTypeBean.class);
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
         * id : 402880065cd3101c015cd3dcef3e0016
         * brandName : 宝马
         * carTrees : [{"id":"402880065cd3101c015cd3dd41ec0018","brandName":"宝马1","carTrees":[]}]
         */

        private String id;
        private String brandName;
        private List<CarTreesBean> carTrees;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public List<CarTreesBean> getCarTrees() {
            return carTrees;
        }

        public void setCarTrees(List<CarTreesBean> carTrees) {
            this.carTrees = carTrees;
        }

        public static class CarTreesBean extends NetResult{
            /**
             * id : 402880065cd3101c015cd3dd41ec0018
             * brandName : 宝马1
             * carTrees : []
             */

            private String id;
            private String brandName;
            private List<?> carTrees;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public List<?> getCarTrees() {
                return carTrees;
            }

            public void setCarTrees(List<?> carTrees) {
                this.carTrees = carTrees;
            }
        }
    }
}
