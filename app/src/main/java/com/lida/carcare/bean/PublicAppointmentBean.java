package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 公众号预约
 * Created by xkr on 2017/8/8.
 */

public class PublicAppointmentBean extends NetResult {


    /**
     * status : 1
     * data : [{"id":1,"name":"陈光","carNo":"蒙A74110","orderTime":"2017-08-08 11:08:22","phone":"15247479676","arriveTime":"2017-08-07 11:10:12"},{"id":2,"name":"凯撒","carNo":"粤u23543","orderTime":"2017-08-07 13:23:35","phone":"13544461345","arriveTime":"2017-08-07 13:37:49"}]
     */

    private List<DataBean> data;

    public static PublicAppointmentBean parse(String json) throws AppException {
        PublicAppointmentBean res = new PublicAppointmentBean();
        try{
            res = gson.fromJson(json, PublicAppointmentBean.class);
        }catch (JsonSyntaxException e){
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

    public static class DataBean extends NetResult{
        /**
         * id : 1
         * name : 陈光
         * carNo : 蒙A74110
         * orderTime : 2017-08-08 11:08:22
         * phone : 15247479676
         * arriveTime : 2017-08-07 11:10:12
         */

        private String id;
        private String name;
        private String carNo;
        private String orderTime;
        private String phone;
        private String arriveTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }
    }
}
