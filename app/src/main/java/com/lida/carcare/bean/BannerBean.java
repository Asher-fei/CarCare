package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class BannerBean extends NetResult
{
    /**
     * status : 1
     * data : [{"userId":"402880f85c9f7971015ca01be279000e","bannerName":"轮播1","bannerImg":"upload/files/20170613141957rKgu9K9A.png"},{"userId":"402880f85c9f7971015ca01cb3ba0014","bannerName":"轮播2","bannerImg":"upload/files/201706131420510tAV22DS.jpg"},{"userId":"402880f85c9f7971015ca01dc5670018","bannerName":"轮播3","bannerImg":"upload/files/20170613142202kM3wQOrG.png"}]
     */

    private List<Data> data;

    public static BannerBean parse(String json) throws AppException
    {
        BannerBean res = new BannerBean();
        try
        {
            res = gson.fromJson(json, BannerBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<Data> getData()
    {
        return data;
    }

    public void setData(List<Data> data)
    {
        this.data = data;
    }

    public static class Data implements Serializable
    {
        /**
         * userId : 402880f85c9f7971015ca01be279000e
         * bannerName : 轮播1
         * bannerImg : upload/files/20170613141957rKgu9K9A.png
         */

        private String id;
        private String bannerName;
        private String bannerImg;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getBannerName()
        {
            return bannerName;
        }

        public void setBannerName(String bannerName)
        {
            this.bannerName = bannerName;
        }

        public String getBannerImg()
        {
            return bannerImg;
        }

        public void setBannerImg(String bannerImg)
        {
            this.bannerImg = bannerImg;
        }
    }
}
