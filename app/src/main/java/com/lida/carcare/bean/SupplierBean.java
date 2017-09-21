package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */

public class SupplierBean extends NetResult
{
    /**
     * status : 1
     * data : [{"id":"402880fa5c2e1239015c2e1404160006","supplierCompany":"零件公司","supplierName":"小红","telephoneNo":"22066900"},{"id":"f8fcb310558111e7976bb8975ae21ffe","supplierCompany":"奔驰2","supplierName":"小花2","telephoneNo":null}]
     */

    private List<DataBean> data;

    public static SupplierBean parse(String json) throws AppException
    {
        SupplierBean res = new SupplierBean();
        try
        {
            res = gson.fromJson(json, SupplierBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }


    public static class DataBean extends NetResult
    {
        /**
         * id : 402880fa5c2e1239015c2e1404160006
         * supplierCompany : 零件公司
         * supplierName : 小红
         * telephoneNo : 22066900
         */

        private String id;
        private String supplierCompany;
        private String supplierName;
        private String telephoneNo;

        /**
         * id : f8fcb310558111e7976bb8975ae21ffe
         * supplierCompany : 奔驰2
         * supplierName : 小花2
         * telephoneNo : null
         * mobilephoneNo : 13698763256
         * faxNo : 223366622
         * address : 某路某街道2
         * bankName : null
         * remark : 哈哈2
         */

        private String mobilephoneNo;
        private String faxNo;
        private String address;
        private String bankName;
        private String remark;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getSupplierCompany()
        {
            return supplierCompany;
        }

        public void setSupplierCompany(String supplierCompany)
        {
            this.supplierCompany = supplierCompany;
        }

        public String getSupplierName()
        {
            return supplierName;
        }

        public void setSupplierName(String supplierName)
        {
            this.supplierName = supplierName;
        }

        public String getTelephoneNo()
        {
            return telephoneNo;
        }

        public void setTelephoneNo(String telephoneNo)
        {
            this.telephoneNo = telephoneNo;
        }

        public String getMobilephoneNo()
        {
            return mobilephoneNo;
        }

        public void setMobilephoneNo(String mobilephoneNo)
        {
            this.mobilephoneNo = mobilephoneNo;
        }

        public String getFaxNo()
        {
            return faxNo;
        }

        public void setFaxNo(String faxNo)
        {
            this.faxNo = faxNo;
        }

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getBankName()
        {
            return bankName;
        }

        public void setBankName(String bankName)
        {
            this.bankName = bankName;
        }

        public String getRemark()
        {
            return remark;
        }

        public void setRemark(String remark)
        {
            this.remark = remark;
        }
    }
}
