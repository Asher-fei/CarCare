package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

/**
 * 会员管理
 * Created by Administrator on 2017/7/1.
 */

public class MemberManagementBean extends NetResult {

    /**
     * status : 1
     * data : {"amount":1099,"timeCardNumber":2,"willExpire":0,"expired":0,"cardInsufficient":1,"unlimitedNumber":1,"membership":4,"overTimeLessThan":1,"numberOfPreloadedCards":2,"limitedNumberOfCards":1}
     */

    private DataBean data;

    public static MemberManagementBean parse(String json) throws AppException {
        MemberManagementBean res = new MemberManagementBean();
        try{
            res = gson.fromJson(json, MemberManagementBean.class);
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
         * amount : 1099
         * timeCardNumber : 2
         * willExpire : 0
         * expired : 0
         * cardInsufficient : 1
         * unlimitedNumber : 1
         * membership : 4
         * overTimeLessThan : 1
         * numberOfPreloadedCards : 2
         * limitedNumberOfCards : 1
         */

        private double amount;
        private int timeCardNumber;
        private int willExpire;
        private int expired;
        private int cardInsufficient;
        private int unlimitedNumber;
        private int membership;
        private int overTimeLessThan;
        private int numberOfPreloadedCards;
        private int limitedNumberOfCards;

        public double getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getTimeCardNumber() {
            return timeCardNumber;
        }

        public void setTimeCardNumber(int timeCardNumber) {
            this.timeCardNumber = timeCardNumber;
        }

        public int getWillExpire() {
            return willExpire;
        }

        public void setWillExpire(int willExpire) {
            this.willExpire = willExpire;
        }

        public int getExpired() {
            return expired;
        }

        public void setExpired(int expired) {
            this.expired = expired;
        }

        public int getCardInsufficient() {
            return cardInsufficient;
        }

        public void setCardInsufficient(int cardInsufficient) {
            this.cardInsufficient = cardInsufficient;
        }

        public int getUnlimitedNumber() {
            return unlimitedNumber;
        }

        public void setUnlimitedNumber(int unlimitedNumber) {
            this.unlimitedNumber = unlimitedNumber;
        }

        public int getMembership() {
            return membership;
        }

        public void setMembership(int membership) {
            this.membership = membership;
        }

        public int getOverTimeLessThan() {
            return overTimeLessThan;
        }

        public void setOverTimeLessThan(int overTimeLessThan) {
            this.overTimeLessThan = overTimeLessThan;
        }

        public int getNumberOfPreloadedCards() {
            return numberOfPreloadedCards;
        }

        public void setNumberOfPreloadedCards(int numberOfPreloadedCards) {
            this.numberOfPreloadedCards = numberOfPreloadedCards;
        }

        public int getLimitedNumberOfCards() {
            return limitedNumberOfCards;
        }

        public void setLimitedNumberOfCards(int limitedNumberOfCards) {
            this.limitedNumberOfCards = limitedNumberOfCards;
        }
    }
}
