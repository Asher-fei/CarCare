package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 微信预支付数据
 * Created by Administrator on 2017/8/7.
 */

public class WeiXinPayBean extends NetResult {


    /**
     * status : 1
     * data : {"1":"预支付成功","package":"sign=WXPay","appid":"wx633190b9e554d9e9","sign":"95163E7EC2932437D4CB07A480AA8150","partnerid":"1486505492","prepayid":"wx20170807171359143dc6f5440499472844","noncestr":"0r9uf8ir24ulks5g437ojqvc7nclyu0p","timestamp":"1502097221"}
     */

    private DataBean data;

    public static WeiXinPayBean parse(String json) throws AppException
    {
        WeiXinPayBean res = new WeiXinPayBean();
        try
        {
            res = gson.fromJson(json, WeiXinPayBean.class);
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
         * 1 : 预支付成功
         * package : sign=WXPay
         * appid : wx633190b9e554d9e9
         * sign : 95163E7EC2932437D4CB07A480AA8150
         * partnerid : 1486505492
         * prepayid : wx20170807171359143dc6f5440499472844
         * noncestr : 0r9uf8ir24ulks5g437ojqvc7nclyu0p
         * timestamp : 1502097221
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
