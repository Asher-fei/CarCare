package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 消费卡
 * Created by Administrator on 2017/7/2.
 */

public class ConsumeCardBean extends NetResult{

    /**
     * status : 1
     * data : [{"id":"69cda4b75d6211e784e7f44d30a3e396","consumeCardNo":"111111","consumeCardName":"可是","mobile":"13800138000"},{"id":"996d063e5e2811e7be1cf44d30a3e396","consumeCardNo":"855","consumeCardName":"可是","mobile":"13800138000"},{"id":"a381064b5d6311e784e7f44d30a3e396","consumeCardNo":"123456","consumeCardName":"可是哟","mobile":"13800138000"}]
     */

    private List<DataBean> data;

    public static ConsumeCardBean parse(String json) throws AppException
    {
        ConsumeCardBean res = new ConsumeCardBean();
        try
        {
            res = gson.fromJson(json, ConsumeCardBean.class);
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

    public static class DataBean {
        /**
         * id : 69cda4b75d6211e784e7f44d30a3e396
         * consumeCardNo : 111111
         * consumeCardName : 可是
         * mobile : 13800138000
         */

        private String id;
        private String consumeCardNo;
        private String consumeCardName;
        private String mobile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getConsumeCardNo() {
            return consumeCardNo;
        }

        public void setConsumeCardNo(String consumeCardNo) {
            this.consumeCardNo = consumeCardNo;
        }

        public String getConsumeCardName() {
            return consumeCardName;
        }

        public void setConsumeCardName(String consumeCardName) {
            this.consumeCardName = consumeCardName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
