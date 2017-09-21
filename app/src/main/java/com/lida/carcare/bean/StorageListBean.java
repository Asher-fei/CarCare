package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;
import java.util.List;

/**
 * 仓库列表
 * Created by Administrator on 2017/6/14.
 */

public class StorageListBean extends NetResult
{

    private List<DataBean> data;

    public static StorageListBean parse(String json) throws AppException
    {
        StorageListBean res = new StorageListBean();
        try
        {
            res = gson.fromJson(json, StorageListBean.class);
        } catch (JsonSyntaxException e)
        {
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


    public static class DataBean extends NetResult implements Serializable{
        /**
         * id : sdfgdgfdghdfh
         * repertoryName : 2号
         * repertoryRemark : 2
         */

        private String id;
        private String repertoryName;
        private String repertoryRemark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRepertoryName() {
            return repertoryName;
        }

        public void setRepertoryName(String repertoryName) {
            this.repertoryName = repertoryName;
        }

        public String getRepertoryRemark() {
            return repertoryRemark;
        }

        public void setRepertoryRemark(String repertoryRemark) {
            this.repertoryRemark = repertoryRemark;
        }
    }
}
