package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * Created by Administrator on 2017/6/14.
 */

public class BannerDetailBean extends NetResult
{
    /**
     * status : 1
     * data : {"bannerDetail":"<p><img src=\"http://192.168.0.120:8080/CarCare/plug-in/ueditor/jsp//upload1/20170609/60071497002123347.jpg\"/><\/p>"}
     */

    private Data data;

    public static BannerDetailBean parse(String json) throws AppException
    {
        BannerDetailBean res = new BannerDetailBean();
        try
        {
            res = gson.fromJson(json, BannerDetailBean.class);
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


    public static class Data
    {
        /**
         * bannerDetail : <p><img src="http://192.168.0.120:8080/CarCare/plug-in/ueditor/jsp//upload1/20170609/60071497002123347.jpg"/></p>
         */

        private String bannerDetail;

        public String getBannerDetail()
        {
            return bannerDetail;
        }

        public void setBannerDetail(String bannerDetail)
        {
            this.bannerDetail = bannerDetail;
        }
    }
}
