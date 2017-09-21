package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19.
 */

public class PerformanceBean extends NetResult
{
    public static PerformanceBean parse(String json) throws AppException
    {
        PerformanceBean res = new PerformanceBean();
        try
        {
            res = gson.fromJson(json, PerformanceBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    /**
     * status : 1
     * data : {"msg":"操作成功","document":[{"username":"阿峰","headPortrait":null,"amount":"8.33"},{"username":"阿星","headPortrait":"upload/car/8099d482594070d732e478f8b48b9a19.jpg","amount":"92.5"},{"username":"阿涛","headPortrait":"upload/carafbf31a6d5fc1986f0a5b8bdeb54f1fd.jpg","amount":"71.67"}]}
     */

    private Data data;

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
         * msg : 操作成功
         * document : [{"username":"阿峰","headPortrait":null,"amount":"8.33"},{"username":"阿星","headPortrait":"upload/car/8099d482594070d732e478f8b48b9a19.jpg","amount":"92.5"},{"username":"阿涛","headPortrait":"upload/carafbf31a6d5fc1986f0a5b8bdeb54f1fd.jpg","amount":"71.67"}]
         */

        private String amount;

        private List<DocumentBean> document;


        public List<DocumentBean> getDocument()
        {
            return document;
        }

        public void setDocument(List<DocumentBean> document)
        {
            this.document = document;
        }


        public String getAmount()
        {
            return amount;
        }

        public void setAmount(String amount)
        {
            this.amount = amount;
        }

        public static class DocumentBean extends NetResult
        {
            /**
             * username : 阿峰
             * headPortrait : null
             * amount : 8.33
             */

            private String carNo;
            private String entryTime;
            private String username;
            private String headPortrait;
            private String amount;
            private String project;

            public String getProject() {
                return project;
            }

            public void setProject(String project) {
                this.project = project;
            }

            public String getCarNo()
            {
                return carNo;
            }

            public void setCarNo(String carNo)
            {
                this.carNo = carNo;
            }

            public String getEntryTime()
            {
                return entryTime;
            }

            public void setEntryTime(String entryTime)
            {
                this.entryTime = entryTime;
            }

            public String getUsername()
            {
                return username;
            }

            public void setUsername(String username)
            {
                this.username = username;
            }

            public String getHeadPortrait()
            {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait)
            {
                this.headPortrait = headPortrait;
            }

            public String getAmount()
            {
                return amount;
            }

            public void setAmount(String amount)
            {
                this.amount = amount;
            }
        }
    }
}
