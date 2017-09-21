package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 查询定位救援列表
 * Created by xkr on 2017/8/24.
 */

public class LocateTheRescueBean extends NetResult{

    /**
     * status : 1
     * data : [{"id":"2c233c8375e2405590a7fa166cbf3bb8","createtime":"2017-08-24 09:17:36","lx":"23.123888","ly":"113.398109","s":"113.398109","l":"天河区车陂东景花园(车陂龙口大街东)"},{"id":"2734a551ee1245b6b24b48eb5e469d3f","createtime":"2017-08-23 20:49:01","lx":"23.124203","ly":"113.395569","s":"113.395569","l":"天河区中山大道中(汇迅楼)"}]
     */

    private List<DataBean> data;

    public static LocateTheRescueBean parse(String json) throws AppException
    {
        LocateTheRescueBean res = new LocateTheRescueBean();
        try
        {
            res = gson.fromJson(json, LocateTheRescueBean.class);
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

    public static class DataBean extends NetResult{
        /**
         * id : 2c233c8375e2405590a7fa166cbf3bb8
         * createtime : 2017-08-24 09:17:36
         * lx : 23.123888
         * ly : 113.398109
         * s : 113.398109
         * l : 天河区车陂东景花园(车陂龙口大街东)
         */

        private String id;
        private String createtime;
        private String lx;
        private String ly;
        private String s;
        private String l;
        private String phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getLx() {
            return lx;
        }

        public void setLx(String lx) {
            this.lx = lx;
        }

        public String getLy() {
            return ly;
        }

        public void setLy(String ly) {
            this.ly = ly;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getL() {
            return l;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
