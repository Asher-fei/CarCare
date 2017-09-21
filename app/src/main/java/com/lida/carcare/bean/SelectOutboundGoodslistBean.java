package com.lida.carcare.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 领料出库商品列表
 * Created by Administrator on 2017/6/14.
 */

public class SelectOutboundGoodslistBean extends NetResult {

    private List<DataBean> data;

    public static SelectOutboundGoodslistBean parse(String json) throws AppException
    {
        SelectOutboundGoodslistBean res = new SelectOutboundGoodslistBean();
        try
        {
            res = gson.fromJson(json, SelectOutboundGoodslistBean.class);
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

    public static class DataBean extends NetResult implements Parcelable{
        /**
         * id : 3aa31968572111e79ebbf44d30a3e396
         * stock : 3
         * price : 0
         * repertoryId : 017fab60559a11e78e92f44d30a3e396
         * name : 通用语
         * productImg : upload/car/5bbc6b99d9446fa3d03c8c1526b4ae3a.jpg
         * repertoryName : 技术
         */

        private String id;
        private String productId;
        private String stock;
        private String repertoryId;
        private String productName;
        private String productImg;
        private String productInternationalCode;
        private String repertoryName;
        private String selectCount = "1";
        private boolean select = false;

        //添加退货价--采购退货用
        private String ReturnPrice = "0.0";

        protected DataBean(Parcel in) {
            id = in.readString();
            productId = in.readString();
            stock = in.readString();
            repertoryId = in.readString();
            productName = in.readString();
            productImg = in.readString();
            productInternationalCode = in.readString();
            repertoryName = in.readString();
            selectCount = in.readString();
            select = in.readByte() != 0;

        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getSelectCount() {
            return selectCount;
        }

        public void setSelectCount(String selectCount) {
            this.selectCount = selectCount;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

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

        public String getReturnPrice() {
            return ReturnPrice;
        }

        public void setReturnPrice(String returnPrice) {
            ReturnPrice = returnPrice;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", productId='" + productId + '\'' +
                    ", stock='" + stock + '\'' +
                    ", repertoryId='" + repertoryId + '\'' +
                    ", productName='" + productName + '\'' +
                    ", productImg='" + productImg + '\'' +
                    ", productInternationalCode='" + productInternationalCode + '\'' +
                    ", repertoryName='" + repertoryName + '\'' +
                    ", selectCount='" + selectCount + '\'' +
                    ", select=" + select +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(productId);
            dest.writeString(stock);
            dest.writeString(repertoryId);
            dest.writeString(productName);
            dest.writeString(productImg);
            dest.writeString(productInternationalCode);
            dest.writeString(repertoryName);
            dest.writeString(selectCount);
            dest.writeByte((byte) (select ? 1 : 0));
        }
    }

    @Override
    public String toString() {
        return "SelectOutboundGoodslistBean{" +
                "data=" + data +
                '}';
    }
}
