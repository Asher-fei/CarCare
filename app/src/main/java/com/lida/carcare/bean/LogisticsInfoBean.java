package com.lida.carcare.bean;

/**
 * 物流数据实体类
 * Created by xkr on 2017/8/16.
 */

public class LogisticsInfoBean {

    private String AcceptStation;
    private String AcceptTime;

    public LogisticsInfoBean() {
    }

    public LogisticsInfoBean(String acceptStation, String acceptTime) {
        AcceptStation = acceptStation;
        AcceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return AcceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        AcceptStation = acceptStation;
    }

    public String getAcceptTime() {
        return AcceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        AcceptTime = acceptTime;
    }
}
