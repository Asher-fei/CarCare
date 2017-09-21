package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 施工单派单分解项目
 * Created by WeiQingFeng on 2017/5/17.
 */

public class ServiceItemBean extends NetResult {

    private String itemName;
    private String workers;
    private String ids;

    public ServiceItemBean(String itemName, String workers, String ids) {
        this.itemName = itemName;
        this.workers = workers;
        this.ids = ids;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getWorkers() {
        return workers;
    }

    public void setWorkers(String workers) {
        this.workers = workers;
    }
}
