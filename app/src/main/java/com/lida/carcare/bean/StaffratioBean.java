package com.lida.carcare.bean;

/**
 * 员工提成
 * Created by xkr on 2017/8/16.
 */

public class StaffratioBean {

    private String name;
    private String proportion;

    public StaffratioBean() {
    }

    public StaffratioBean(String name, String proportion) {
        this.name = name;
        this.proportion = proportion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
