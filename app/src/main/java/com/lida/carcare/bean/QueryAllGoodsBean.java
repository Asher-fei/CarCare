package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 查询店铺下所有商品
 * Created by Administrator on 2017/6/14.
 */

public class QueryAllGoodsBean extends NetResult {

    private List<DataBean> data;

    public static QueryAllGoodsBean parse(String json) throws AppException
    {
        QueryAllGoodsBean res = new QueryAllGoodsBean();
        try
        {
            res = gson.fromJson(json, QueryAllGoodsBean.class);
        } catch (JsonSyntaxException e)
        {
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

    public static class DataBean extends NetResult implements Serializable{
        /**
         * name : 345
         * productImg : upload/car/480704681e8477aff4b0df965b895f80.jpg
         * internationalCode : 345
         */

        private String id;
        private String name;
        private String productImg;
        private String internationalCode;
        private String count = "1";
        private String price = "0.0";

        //添加仓库字段  其它入库使用
        private String repertoryId = "";

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getInternationalCode() {
            return internationalCode;
        }

        public void setInternationalCode(String internationalCode) {
            this.internationalCode = internationalCode;
        }

        public String getRepertoryId() {
            return repertoryId;
        }

        public void setRepertoryId(String repertoryId) {
            this.repertoryId = repertoryId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", productImg='" + productImg + '\'' +
                    ", internationalCode='" + internationalCode + '\'' +
                    ", count='" + count + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "QueryAllGoodsBean{" +
                "data=" + data +
                '}';
    }
}
