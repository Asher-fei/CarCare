package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 服务分类下的商品
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServiceGoodBean extends NetResult {

    /**
     * data : {"jfomService":[{"userId":"b6d82d384bf211e7b48df44d30a3e396","name":"123","serviceType":"A01A01","servicePrice":345,"remark":"Sdf","serviceImg":"upload/car/4cacce756b24f5c5a60cebf4376ee6bf.jpg","drawType":"0","drawPrice":"12"},{"userId":"c9b522f14bf011e7b48df44d30a3e396","name":"qqq","serviceType":"A01A01","servicePrice":33,"remark":"Sdfsdf","serviceImg":"upload/car/deec1326e0fa09d978e079abff93435c.jpg","drawType":"0","drawPrice":"69"},{"userId":"f5e459834bf011e7b48df44d30a3e396","name":"qqq1","serviceType":"A01A01","servicePrice":331,"remark":"Sdfsdf","serviceImg":"upload/car/4ff7a83fbf91fb1f8cbd38237c934409.jpg","drawType":"0","drawPrice":"691"}],"count":3}
     */

    private DataBean data;

    public static ServiceGoodBean parse(String json) throws AppException {
        ServiceGoodBean res = new ServiceGoodBean();
        try{
            res = gson.fromJson(json, ServiceGoodBean.class);
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
         * jfomService : [{"userId":"b6d82d384bf211e7b48df44d30a3e396","name":"123","serviceType":"A01A01","servicePrice":345,"remark":"Sdf","serviceImg":"upload/car/4cacce756b24f5c5a60cebf4376ee6bf.jpg","drawType":"0","drawPrice":"12"},{"userId":"c9b522f14bf011e7b48df44d30a3e396","name":"qqq","serviceType":"A01A01","servicePrice":33,"remark":"Sdfsdf","serviceImg":"upload/car/deec1326e0fa09d978e079abff93435c.jpg","drawType":"0","drawPrice":"69"},{"userId":"f5e459834bf011e7b48df44d30a3e396","name":"qqq1","serviceType":"A01A01","servicePrice":331,"remark":"Sdfsdf","serviceImg":"upload/car/4ff7a83fbf91fb1f8cbd38237c934409.jpg","drawType":"0","drawPrice":"691"}]
         * count : 3
         */

        private String count;
        private List<JfomServiceBean> jfomService;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<JfomServiceBean> getJfomService() {
            return jfomService;
        }

        public void setJfomService(List<JfomServiceBean> jfomService) {
            this.jfomService = jfomService;
        }

        public static class JfomServiceBean extends NetResult{
            /**
             * userId : b6d82d384bf211e7b48df44d30a3e396
             * name : 123
             * serviceType : A01A01
             * servicePrice : 345
             * remark : Sdf
             * serviceImg : upload/car/4cacce756b24f5c5a60cebf4376ee6bf.jpg
             * drawType : 0
             * drawPrice : 12
             */

            private String id;
            private String name;
            private String serviceType;
            private String servicePrice;
            private String remark;
            private String serviceImg;
            private String drawType;
            private String drawPrice;
            private String count;

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

            public String getServiceType() {
                return serviceType;
            }

            public void setServiceType(String serviceType) {
                this.serviceType = serviceType;
            }

            public String getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(String servicePrice) {
                this.servicePrice = servicePrice;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getServiceImg() {
                return serviceImg;
            }

            public void setServiceImg(String serviceImg) {
                this.serviceImg = serviceImg;
            }

            public String getDrawType() {
                return drawType;
            }

            public void setDrawType(String drawType) {
                this.drawType = drawType;
            }

            public String getDrawPrice() {
                return drawPrice;
            }

            public void setDrawPrice(String drawPrice) {
                this.drawPrice = drawPrice;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }
        }
    }
}
