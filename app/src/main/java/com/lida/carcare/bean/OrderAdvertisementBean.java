package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 订货广告图片
 * Created by xkr on 2017/7/25.
 */

public class OrderAdvertisementBean extends NetResult {


    /**
     * status : 1
     * data : {"id":"1","content":"内容","title":"标题","imagePath":"upload/files/20170615151234Cvgs0yfe.jpg"}
     */

    private DataBean data;

    public static OrderAdvertisementBean parse(String json) throws AppException {
        OrderAdvertisementBean res = new OrderAdvertisementBean();
        try{
            res = gson.fromJson(json, OrderAdvertisementBean.class);
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
         * id : 1
         * content : 内容
         * title : 标题
         * imagePath : upload/files/20170615151234Cvgs0yfe.jpg
         */

        private String id;
        private String content;
        private String title;
        private String imagePath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
