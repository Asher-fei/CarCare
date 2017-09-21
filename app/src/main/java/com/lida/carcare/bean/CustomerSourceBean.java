package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 获取客户来源
 * Created by WeiQingFeng on 2017/5/17.
 */

public class CustomerSourceBean extends NetResult {

    private List<DataBean> data;

    public static CustomerSourceBean parse(String json) throws AppException {
        CustomerSourceBean res = new CustomerSourceBean();
        try{
            res = gson.fromJson(json, CustomerSourceBean.class);
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
         * userId : 402880fa5c140cda015c1423ee9c0005
         * customerLevelName : 推广员2
         */

        private String id;
        private String promoterName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPromoterName() {
            return promoterName;
        }

        public void setPromoterName(String promoterName) {
            this.promoterName = promoterName;
        }
    }
}
