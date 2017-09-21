package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 报价项目
 * Created by Administrator on 2017/6/14.
 */

public class SelectInfoOfferBean extends NetResult
{
    /**
     * data : {"id":"3ced8737526111e7a835f44d30a3e396","jfomService":[{"name":"保养项目1","servicePrice":55},{"name":"保养项目2","servicePrice":687}]}
     */

    private DataBean data;

    public static SelectInfoOfferBean parse(String json) throws AppException
    {
        SelectInfoOfferBean res = new SelectInfoOfferBean();
        try
        {
            res = gson.fromJson(json, SelectInfoOfferBean.class);
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
         * id : 3ced8737526111e7a835f44d30a3e396
         * jfomService : [{"name":"保养项目1","servicePrice":55},{"name":"保养项目2","servicePrice":687}]
         */

        private String id;
        private List<JfomServiceBean> jfomService;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<JfomServiceBean> getJfomService() {
            return jfomService;
        }

        public void setJfomService(List<JfomServiceBean> jfomService) {
            this.jfomService = jfomService;
        }

        public static class JfomServiceBean extends NetResult{
            /**
             * name : 保养项目1
             * servicePrice : 55
             */

            private String name;
            private String servicePrice;
            private String frequency;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getServicePrice() {
                return servicePrice;
            }

            public void setServicePrice(String servicePrice) {
                this.servicePrice = servicePrice;
            }

            public String getFrequency() {
                return frequency;
            }

            public void setFrequency(String frequency) {
                this.frequency = frequency;
            }
        }
    }
}
