package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/15.
 */

public class CarManageListBean extends NetResult
{
    /**
     * status : 1
     * data : [{"userId":"0b716c1f4f5f11e79108f44d30a3e396","customerName":null,"mobile":""},{"userId":"1b362cad4f6211e79108f44d30a3e396","customerName":null,"mobile":""},{"userId":"4d0f86864f1d11e79108f44d30a3e396","customerName":null,"mobile":""},{"userId":"5a1fb329502211e7ba12f44d30a3e396","customerName":null,"mobile":""},{"userId":"9407dbbd500511e7ba12f44d30a3e396","customerName":null,"mobile":"13510000000"}]
     */

    private List<Data> data;

    public static CarManageListBean parse(String json) throws AppException
    {
        CarManageListBean res = new CarManageListBean();
        try
        {
            res = gson.fromJson(json, CarManageListBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<Data> getData()
    {
        return data;
    }

    public void setData(List<Data> data)
    {
        this.data = data;
    }


    public static class Data
    {
        /**
         * userId : 0b716c1f4f5f11e79108f44d30a3e396
         * customerName : null
         * carNo: 粤AH98J4
         * mobile :
         */

        private String id;
        private String customerName;
        private String mobile;
        private String carNo;

        public String getCarNo()
        {
            return carNo;
        }

        public void setCarNo(String carNo)
        {
            this.carNo = carNo;
        }

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getCustomerName()
        {
            return customerName;
        }

        public void setCustomerName(String customerName)
        {
            this.customerName = customerName;
        }

        public String getMobile()
        {
            return mobile;
        }

        public void setMobile(String mobile)
        {
            this.mobile = mobile;
        }
    }
}
