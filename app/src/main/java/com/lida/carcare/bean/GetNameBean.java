package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 根据项目返回服务的员工
 * Created by Administrator on 2017/6/14.
 */

public class GetNameBean extends NetResult
{

    /**
     * data : {"str":"美孚金装一号-阿涛;洗车-阿涛,阿峰,"}
     */

    private DataBean data;

    public static GetNameBean parse(String json) throws AppException
    {
        GetNameBean res = new GetNameBean();
        try
        {
            res = gson.fromJson(json, GetNameBean.class);
        } catch (JsonSyntaxException e)
        {
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
         * str : 美孚金装一号-阿涛;洗车-阿涛,阿峰,
         */

        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
