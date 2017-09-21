package com.lida.carcare.updater;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;
import java.io.Serializable;
public class Version  extends NetResult implements Serializable{


    /**
     * status : 1
     * data : {"updateRemark":1,"size":2,"download_url":1,"updateTime":1,"versionName":1,"versionCode":8}
     */

    private DataBean data;

    public static Version parse(String json) throws AppException {
        Version res = new Version();
        try{
            res = gson.fromJson(json, Version.class);
        }catch (JsonSyntaxException e){
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

    public static class DataBean extends NetResult implements Serializable{
        /**
         * updateRemark : 1
         * size : 2
         * download_url : 1
         * updateTime : 1
         * versionName : 1
         * versionCode : 8
         */

        private String updateRemark;
        private String size;
        private String download_url;
        private String updateTime;
        private String versionName;
        private Integer versionCode;

        public String getUpdateRemark() {
            return updateRemark;
        }

        public void setUpdateRemark(String updateRemark) {
            this.updateRemark = updateRemark;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public Integer getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(Integer versionCode) {
            this.versionCode = versionCode;
        }
    }
}
