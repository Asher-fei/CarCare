package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/20.
 */

public class SupplierDetailsBean extends NetResult
{
    /**
     * status : 1
     * data : {"id":"c8677d3f559e11e78e92f44d30a3e396","supplierCompany":"广州供应商","supplierName":"张三","mobilephoneNo":"13846497032","faxNo":"020-599544","address":null,"bankName":null,"remark":null}
     */

    private DataBean data;

    public static SupplierDetailsBean parse(String json) throws AppException
    {
        SupplierDetailsBean res = new SupplierDetailsBean();
        try
        {
            res = gson.fromJson(json, SupplierDetailsBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }


    public static class DataBean implements Serializable
    {
        /**
         * id : c8677d3f559e11e78e92f44d30a3e396
         * supplierCompany : 广州供应商
         * supplierName : 张三
         * mobilephoneNo : 13846497032
         * faxNo : 020-599544
         * address : null
         * bankName : null
         * remark : null
         */

        private String id;
        private String supplierCompany;
        private String supplierName;
        private String mobilephoneNo;
        private String faxNo;
        private String address;
        private String bankName;
        private String remark;
        private String accountNo;

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

        public String getAccountNo()
        {
            return accountNo;
        }

        public void setAccountNo(String accountNo)
        {
            this.accountNo = accountNo;
        }
    }
}
