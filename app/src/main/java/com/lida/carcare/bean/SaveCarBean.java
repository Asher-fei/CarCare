package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * Created by WeiQingFeng on 2017/5/17.
 */

public class SaveCarBean extends NetResult {

    public static SaveCarBean parse(String json) throws AppException {
        SaveCarBean res = new SaveCarBean();
        try{
            res = gson.fromJson(json, SaveCarBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean extends NetResult{

        private String userName;
        private String userId;
        private String token;

    }
}
