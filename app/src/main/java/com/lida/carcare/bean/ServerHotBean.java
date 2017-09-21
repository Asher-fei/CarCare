package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 *  新添车辆--热门服务
 * Created by xkr on 2017/8/3.
 */

public class ServerHotBean extends NetResult{


    /**
     * status : 1
     * msg : success
     * data : [{"id":"cgfdgfdghdf","name":"倒车可视","price":"2380","shopId":"a25e261c76c711e7a0b6f44d30a3e396","code":"A01"},{"id":"fdhgfhgjhg","name":"工时2","price":"100","shopId":"a25e261c76c711e7a0b6f44d30a3e396","code":"A01"},{"id":"dsfdsgfdhg","name":"道达尔方向机油ATF","price":"250","shopId":"a25e261c76c711e7a0b6f44d30a3e396","code":"A02"},{"id":"dgdfhg","name":"5-6速自动变速箱油MV","price":"1600","shopId":"a25e261c76c711e7a0b6f44d30a3e396","code":"A02"}]
     */

    private List<DataBean> data;

    public static ServerHotBean parse(String json) throws AppException {
        ServerHotBean res = new ServerHotBean();
        try{
            res = gson.fromJson(json, ServerHotBean.class);
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
         * id : cgfdgfdghdf
         * name : 倒车可视
         * price : 2380
         * shopId : a25e261c76c711e7a0b6f44d30a3e396
         * code : A01
         */

        private String id;
        private String name;
        private String price;
        private String shopId;
        private String code;
        private String serviceImg;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getServiceImg() {
            return serviceImg;
        }

        public void setServiceImg(String serviceImg) {
            this.serviceImg = serviceImg;
        }
    }
}
