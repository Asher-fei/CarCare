package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 当天收入详情
 * Created by Administrator on 2017/7/1.
 */

public class IncomeDetailsBean extends NetResult{

    /**
     * status : 1
     * data : {"cashReceipts":0,"weChatIncome":200,"alipayRevenue":0,"creditCardIncome":0,"carIncomeSpending":[{"name":"轮胎服务","price":200,"state":"微信","remake":"轮胎服务dsddsdadad"}]}
     */

    private DataBean data;

    public static IncomeDetailsBean parse(String json) throws AppException {
        IncomeDetailsBean res = new IncomeDetailsBean();
        try{
            res = gson.fromJson(json, IncomeDetailsBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends NetResult{
        /**
         * cashReceipts : 0
         * weChatIncome : 200
         * alipayRevenue : 0
         * creditCardIncome : 0
         * carIncomeSpending : [{"name":"轮胎服务","price":200,"state":"微信","remake":"轮胎服务dsddsdadad"}]
         */

        private double cashReceipts;
        private double weChatIncome;
        private double alipayRevenue;
        private double creditCardIncome;
        private List<CarIncomeSpendingBean> carIncomeSpending;

        public double getCashReceipts() {
            return cashReceipts;
        }

        public void setCashReceipts(double cashReceipts) {
            this.cashReceipts = cashReceipts;
        }

        public double getWeChatIncome() {
            return weChatIncome;
        }

        public void setWeChatIncome(double weChatIncome) {
            this.weChatIncome = weChatIncome;
        }

        public double getAlipayRevenue() {
            return alipayRevenue;
        }

        public void setAlipayRevenue(double alipayRevenue) {
            this.alipayRevenue = alipayRevenue;
        }

        public double getCreditCardIncome() {
            return creditCardIncome;
        }

        public void setCreditCardIncome(double creditCardIncome) {
            this.creditCardIncome = creditCardIncome;
        }

        public void setCreditCardIncome(int creditCardIncome) {
            this.creditCardIncome = creditCardIncome;
        }

        public List<CarIncomeSpendingBean> getCarIncomeSpending() {
            return carIncomeSpending;
        }

        public void setCarIncomeSpending(List<CarIncomeSpendingBean> carIncomeSpending) {
            this.carIncomeSpending = carIncomeSpending;
        }

        public static class CarIncomeSpendingBean extends NetResult{
            /**
             * name : 轮胎服务
             * price : 200
             * state : 微信
             * remake : 轮胎服务dsddsdadad
             */

            private String name;
            private double price;
            private String state;
            private String remake;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getRemake() {
                return remake;
            }

            public void setRemake(String remake) {
                this.remake = remake;
            }
        }
    }
}
