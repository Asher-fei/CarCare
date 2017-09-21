package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 在店车辆
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GetPriceBean extends NetResult {

    /**
     * data : {"sb":"56.0,45.0,25.0,"}
     */

    private DataBean data;

    public static GetPriceBean parse(String json) throws AppException {
        GetPriceBean res = new GetPriceBean();
        try{
            res = gson.fromJson(json, GetPriceBean.class);
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


    public static class DataBean {
        /**
         * sb : 56.0,45.0,25.0,
         */

        private String sb;

        public String getSb() {
            return sb;
        }

        public void setSb(String sb) {
            this.sb = sb;
        }
    }
}
