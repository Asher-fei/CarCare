package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/14.
 */

public class LineDetailBean extends NetResult
{
    /**
     * status : 1
     * data : {"lineDetail":"<p>是的发送到范德萨范德萨<br/><\/p>"}
     */

    private Data data;

    public static LineDetailBean parse(String json) throws AppException
    {
        LineDetailBean res = new LineDetailBean();
        try
        {
            res = gson.fromJson(json, LineDetailBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }


    public Data getData()
    {
        return data;
    }

    public void setData(Data data)
    {
        this.data = data;
    }


    public static class Data implements Serializable
    {
        /**
         * lineDetail : <p>是的发送到范德萨范德萨<br/></p>
         */

        private String lineDetail;

        public String getLineDetail()
        {
            return lineDetail;
        }

        public void setLineDetail(String lineDetail)
        {
            this.lineDetail = lineDetail;
        }
    }
}
