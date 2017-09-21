package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 根据车牌号查客户
 * Created by xkr on 2017/8/18.
 */

public class CarAndDocumentBean extends NetResult {

    /**
     * status : 1
     * data : [{"id":"689dbf9282fd11e7b5b9f44d30a3e396","carNo":"晋111111","customer":{"id":"402880f35d780bd7015d780efe8a0007","customerName":"徐徐","sex":"0","mobilePhoneNo":"18377610623","nickname":null}},{"id":"f1d2fdd278f111e7bec4f44d30a3e396","carNo":"晋111111","customer":{"id":"402880f35d780bd7015d780efe8a000d","customerName":"BB","sex":"0","mobilePhoneNo":"18377610666","nickname":null}}]
     */

    private List<DataBean> data;

    public static CarAndDocumentBean parse(String json) throws AppException
    {
        CarAndDocumentBean res = new CarAndDocumentBean();
        try
        {
            res = gson.fromJson(json, CarAndDocumentBean.class);
        } catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends NetResult{
        /**
         * id : 689dbf9282fd11e7b5b9f44d30a3e396
         * carNo : 晋111111
         * customer : {"id":"402880f35d780bd7015d780efe8a0007","customerName":"徐徐","sex":"0","mobilePhoneNo":"18377610623","nickname":null}
         */

        private String id;
        private String carNo;
        private CustomerBean customer;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public static class CustomerBean {
            /**
             * id : 402880f35d780bd7015d780efe8a0007
             * customerName : 徐徐
             * sex : 0
             * mobilePhoneNo : 18377610623
             * nickname : null
             */

            private String id;
            private String customerName;
            private String sex;
            private String mobilePhoneNo;
            private String nickname;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getMobilePhoneNo() {
                return mobilePhoneNo;
            }

            public void setMobilePhoneNo(String mobilePhoneNo) {
                this.mobilePhoneNo = mobilePhoneNo;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
