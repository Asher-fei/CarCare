package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by zdf on 2017/6/28.
 * 库存盘点Bean
 */

public class InventoryVerificationBean extends NetResult{


    /**
     * status : 1
     * data : [{"id":"247be8c161fc11e79a4bb8975ae21ffe","shoutId":"1499312081653","shoutPeason":"王总","shoutStock":"10","shoutDate":"2017-07-06 11:34:41","shoutOk":"8","shoutNo":null,"acount":"2"},{"id":"b40c9c8661fd11e79a4bb8975ae21ffe","shoutId":"1499312752012","shoutPeason":"王总","shoutStock":"4","shoutDate":"2017-07-06 11:45:52","shoutOk":null,"shoutNo":"-6","acount":"2"}]
     */

    private List<DataBean> data;

    public static InventoryVerificationBean parse(String json) throws AppException {
        InventoryVerificationBean res = new InventoryVerificationBean();
        try{
            res = gson.fromJson(json, InventoryVerificationBean.class);
        }catch (JsonSyntaxException e){
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
         * id : 247be8c161fc11e79a4bb8975ae21ffe
         * shoutId : 1499312081653
         * shoutPeason : 王总
         * shoutStock : 10
         * shoutDate : 2017-07-06 11:34:41
         * shoutOk : 8
         * shoutNo : null
         * acount : 2
         */

        private String id;
        private String shoutId;
        private String shoutPeason;
        private String shoutStock;
        private String shoutDate;
        private String shoutOk;
        private String shoutNo;
        private String acount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShoutId() {
            return shoutId;
        }

        public void setShoutId(String shoutId) {
            this.shoutId = shoutId;
        }

        public String getShoutPeason() {
            return shoutPeason;
        }

        public void setShoutPeason(String shoutPeason) {
            this.shoutPeason = shoutPeason;
        }

        public String getShoutStock() {
            return shoutStock;
        }

        public void setShoutStock(String shoutStock) {
            this.shoutStock = shoutStock;
        }

        public String getShoutDate() {
            return shoutDate;
        }

        public void setShoutDate(String shoutDate) {
            this.shoutDate = shoutDate;
        }

        public String getShoutOk() {
            return shoutOk;
        }

        public void setShoutOk(String shoutOk) {
            this.shoutOk = shoutOk;
        }

        public String getShoutNo() {
            return shoutNo;
        }

        public void setShoutNo(String shoutNo) {
            this.shoutNo = shoutNo;
        }

        public String getAcount() {
            return acount;
        }

        public void setAcount(String acount) {
            this.acount = acount;
        }
    }
}
