package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 员工管理
 * Created by WeiQingFeng on 2017/5/17.
 */

public class MemberBean extends NetResult {


    private List<DataBean> data;

    public static MemberBean parse(String json) throws AppException {
        MemberBean res = new MemberBean();
        try{
            res = gson.fromJson(json, MemberBean.class);
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
         * id : 0c7c6faa76de495d97f7217bdbd8db1b
         * username : 阿涛
         * shopCode : null
         * tsDeparts1 : [{"dePname":"前台接待"},{"dePname":"美容"},{"dePname":"换装"}]
         * headPortrait : upload/car/5d2b1f749a6755b0e2374b578f810422.jpg
         * iCode : null
         */

        private String id;
        private String username;
        private String shopCode;
        private String headPortrait;
        private String iCode;
        private List<TsDeparts1Bean> tsDeparts1;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getShopCode() {
            return shopCode;
        }

        public void setShopCode(String shopCode) {
            this.shopCode = shopCode;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getICode() {
            return iCode;
        }

        public void setICode(String iCode) {
            this.iCode = iCode;
        }

        public List<TsDeparts1Bean> getTsDeparts1() {
            return tsDeparts1;
        }

        public void setTsDeparts1(List<TsDeparts1Bean> tsDeparts1) {
            this.tsDeparts1 = tsDeparts1;
        }

        public static class TsDeparts1Bean {
            /**
             * dePname : 前台接待
             */

            private String dePname;

            public String getDePname() {
                return dePname;
            }

            public void setDePname(String dePname) {
                this.dePname = dePname;
            }
        }
    }
}
