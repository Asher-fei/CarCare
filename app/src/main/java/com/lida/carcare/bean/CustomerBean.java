package com.lida.carcare.bean;

import com.midian.base.util.Func;

/**
 * 选择客户列表
 * Created by WeiQingFeng on 2017/4/6.
 */

public class CustomerBean {

    private String name="";
    private String phone="";
    private String relation="";
    private String sex="";

    public CustomerBean() {
        setSex("0");
    }

    public CustomerBean(String name, String phone, String relation, String sex) {
        this.name = name;
        this.phone = phone;
        this.relation = relation;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isComplete(){
        if("".equals(name)||"".equals(phone)||"".equals(relation)||"".equals(sex))
            return false;
        else
            return true;
    }

    public boolean isPhoneRight(){
        if(Func.isMobileNO(phone))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "CustomerBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", relation='" + relation + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
