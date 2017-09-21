package com.lida.carcare.bean;

/**
 * Created by xkr on 2017/8/1.
 */

public class ServiceEditBean {
    String id;
    String project;
    String name;
    String count="1";
    String price = "0.0";

    //保存派单人员
    String workers;
    String ids;

    public ServiceEditBean() {
    }

    public  ServiceEditBean(String id,String project, String name, String count,String price) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWorkers() {
        return workers;
    }

    public void setWorkers(String workers) {
        this.workers = workers;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
