package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class LineBean extends NetResult
{

    public static LineBean parse(String json) throws AppException
    {
        LineBean res = new LineBean();
        try{
            res = gson.fromJson(json, LineBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    /**
     * status : 1
     * data : [{"userId":"402880f85ca038d7015ca03aeadc0001","lineName":"头条1"},{"userId":"402880f85ca038d7015ca03b2fec0003","lineName":"头条2"},{"userId":"402880f85ca038d7015ca03c316a0005","lineName":"头条3"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        /**
         * userId : 402880f85ca038d7015ca03aeadc0001
         * lineName : 头条1
         */

        private String id;
        private String lineName;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getLineName()
        {
            return lineName;
        }

        public void setLineName(String lineName)
        {
            this.lineName = lineName;
        }
    }
}
