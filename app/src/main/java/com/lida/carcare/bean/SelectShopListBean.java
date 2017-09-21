package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 店铺列表查询
 * Created by WeiQingFeng on 2017/5/17.
 */

public class SelectShopListBean extends NetResult {

    private List<DataBean> data;

    public static SelectShopListBean parse(String json) throws AppException {
        SelectShopListBean res = new SelectShopListBean();
        try{
            res = gson.fromJson(json, SelectShopListBean.class);
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
         * userId : 0b0d09b080734024b0c24cb1fb12a16e
         * shopName : eweds
         */

        private String id;
        private String shopName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }
}
