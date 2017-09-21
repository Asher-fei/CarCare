package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 次卡类型
 * Created by Administrator on 2017/6/30.
 */

public class CardRestrictCarnoBean extends NetResult {


    public static CardRestrictCarnoBean parse(String json) throws AppException {
        CardRestrictCarnoBean res = new CardRestrictCarnoBean();
        try{
            res = gson.fromJson(json, CardRestrictCarnoBean.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    /**
     * status : 1
     * data : [{"id":"1","cardName":"名称","cardRestrictCarno":"0","cardPrice":"300"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends NetResult{
        /**
         * id : 1
         * cardName : 名称
         * cardRestrictCarno : 0
         * cardPrice : 300
         */

        private String id;
        private String cardName;
        private String cardRestrictCarno;
        private String cardPrice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public String getCardRestrictCarno() {
            return cardRestrictCarno;
        }

        public void setCardRestrictCarno(String cardRestrictCarno) {
            this.cardRestrictCarno = cardRestrictCarno;
        }

        public String getCardPrice() {
            return cardPrice;
        }

        public void setCardPrice(String cardPrice) {
            this.cardPrice = cardPrice;
        }
    }
}
