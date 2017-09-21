package com.lida.carcare.widget.IndexList;

import com.lida.carcare.bean.CarTypeBean;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityBean extends BaseIndexPinyinBean {

    private String city;//城市名字
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的
    private String id;
    private String code;
    private String price;//价格
    private boolean select;//是否选中
    private List<CarTypeBean.DataBean.CarTreesBean> carTrees;

    public List<CarTypeBean.DataBean.CarTreesBean> getCarTrees() {
        return carTrees;
    }

    public void setCarTrees(List<CarTypeBean.DataBean.CarTreesBean> carTrees) {
        this.carTrees = carTrees;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CityBean() {
    }

    public CityBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public CityBean setCity(String city) {
        this.city = city;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public CityBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }


    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }
}
