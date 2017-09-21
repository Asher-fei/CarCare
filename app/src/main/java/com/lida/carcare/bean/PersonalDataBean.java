package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 个人中心
 * Created by WeiQingFeng on 2017/5/17.
 */

public class PersonalDataBean extends NetResult {

    /**
     * data : {"userId":"8a8ab0b246dc81120146dc8181950052","username":"admin","inviteCode":null,"sex":null,"birthday":null,"qq":null,"headPortrait":null,"mobilephone":""}
     */

    private DataBean data;

    public static PersonalDataBean parse(String json) throws AppException {
        PersonalDataBean res = new PersonalDataBean();
        try{
            res = gson.fromJson(json, PersonalDataBean.class);
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


    public static class DataBean {
        /**
         * userId : 8a8ab0b246dc81120146dc8181950052
         * username : admin
         * inviteCode : null
         * sex : null
         * birthday : null
         * qq : null
         * headPortrait : null
         * mobilephone :
         */

        private String id;
        private String username;
        private String inviteCode;
        private String sex;
        private String birthday;
        private String qq;
        private String headPortrait;
        private String mobilephone;

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

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }
    }
}
