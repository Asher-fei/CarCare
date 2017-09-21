package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * d当前总收入支出
 * Created by Administrator on 2017/7/1.
 */

public class IncomeExpenditureDean extends NetResult {

    /**
     * status : 1
     * data : {"totalRevenue":200,"totalSpending":20000,"netIncome":-19800}
     */

    private DataBean data;

    public static IncomeExpenditureDean parse(String json) throws AppException {
        IncomeExpenditureDean res = new IncomeExpenditureDean();
        try{
            res = gson.fromJson(json, IncomeExpenditureDean.class);
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
         * totalRevenue : 200
         * totalSpending : 20000
         * netIncome : -19800
         */

        private double totalRevenue;
        private double totalSpending;
        private double netIncome;

        public double getTotalRevenue() {
            return totalRevenue;
        }

        public void setTotalRevenue(double totalRevenue) {
            this.totalRevenue = totalRevenue;
        }

        public double getTotalSpending() {
            return totalSpending;
        }

        public void setTotalSpending(double totalSpending) {
            this.totalSpending = totalSpending;
        }

        public double getNetIncome() {
            return netIncome;
        }

        public void setNetIncome(double netIncome) {
            this.netIncome = netIncome;
        }
    }
}
