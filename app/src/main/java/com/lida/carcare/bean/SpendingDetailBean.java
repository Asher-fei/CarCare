package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/7/1.
 */

public class SpendingDetailBean extends NetResult {

    /**
     * status : 1
     * data : {"totalSpending":20000,"carIncomeSpending":[{"name":"工资与奖金","price":20000,"remake":"给五个发工资"}]}
     */

    private DataBean data;


    public static SpendingDetailBean parse(String json) throws AppException {
        SpendingDetailBean res = new SpendingDetailBean();
        try{
            res = gson.fromJson(json, SpendingDetailBean.class);
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

    public static class DataBean {
        /**
         * totalSpending : 20000
         * carIncomeSpending : [{"name":"工资与奖金","price":20000,"remake":"给五个发工资"}]
         */

        private double totalSpending;
        private List<CarIncomeSpendingBean> carIncomeSpending;

        public double getTotalSpending() {
            return totalSpending;
        }

        public void setTotalSpending(int totalSpending) {
            this.totalSpending = totalSpending;
        }

        public List<CarIncomeSpendingBean> getCarIncomeSpending() {
            return carIncomeSpending;
        }

        public void setCarIncomeSpending(List<CarIncomeSpendingBean> carIncomeSpending) {
            this.carIncomeSpending = carIncomeSpending;
        }

        public static class CarIncomeSpendingBean {
            /**
             * name : 工资与奖金
             * price : 20000
             * remake : 给五个发工资
             */

            private String name;
            private double price;
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

            public String getRemake() {
                return remake;
            }

            public void setRemake(String remake) {
                this.remake = remake;
            }
        }
    }
}
