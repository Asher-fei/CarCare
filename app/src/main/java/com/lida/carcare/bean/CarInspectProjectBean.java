package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 检车项目列表
 * Created by Administrator on 2017/6/14.
 */

public class CarInspectProjectBean extends NetResult
{
    private List<DataBean> data;

    public static CarInspectProjectBean parse(String json) throws AppException
    {
        CarInspectProjectBean res = new CarInspectProjectBean();
        try
        {
            res = gson.fromJson(json, CarInspectProjectBean.class);
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

    public static class DataBean {
        /**
         * name : 漆面
         * remarks : 有点晃动
         * state : 1
         */

        private String name;
        private String remarks;
        private String state;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
