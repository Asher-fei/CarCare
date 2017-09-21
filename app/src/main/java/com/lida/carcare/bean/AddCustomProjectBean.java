package com.lida.carcare.bean;

import java.io.Serializable;

/**
 * 添加新车-自定义项目
 * Created by xkr on 2017/9/15.
 */

public class AddCustomProjectBean implements Serializable{

    private String name;
    private String price;
    private String count;
    private String drawType;
    private String drawPrice;

    public AddCustomProjectBean() {
    }

    public AddCustomProjectBean(String name, String price, String count, String drawType, String drawPrice) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.drawType = drawType;
        this.drawPrice = drawPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDrawType() {
        return drawType;
    }

    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    public String getDrawPrice() {
        return drawPrice;
    }

    public void setDrawPrice(String drawPrice) {
        this.drawPrice = drawPrice;
    }
}
