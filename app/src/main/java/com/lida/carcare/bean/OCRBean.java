package com.lida.carcare.bean;

import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class OCRBean{

    /**
     * message : {"status":0,"value":"识别完成"}
     * cardsinfo : [{"type":"19","items":[{"nID":null,"index":null,"desc":"车牌号","content":"苏K**309"},{"nID":null,"index":null,"desc":"车牌颜色","content":"蓝"},{"nID":null,"index":null,"desc":"车牌颜色","content":"1"},{"nID":null,"index":null,"desc":"车牌类型","content":"1"},{"nID":null,"index":null,"desc":"整牌可信度","content":"83"},{"nID":null,"index":null,"desc":"亮度评价","content":"116"},{"nID":null,"index":null,"desc":"车牌运动方向","content":"0"},{"nID":null,"index":null,"desc":"车牌位置(left_top_right_bottom)","content":"29_3_47_31"}]}]
     */

    private MessageBean message;
    private List<CardsinfoBean> cardsinfo;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public List<CardsinfoBean> getCardsinfo() {
        return cardsinfo;
    }

    public void setCardsinfo(List<CardsinfoBean> cardsinfo) {
        this.cardsinfo = cardsinfo;
    }

    public static class MessageBean {
        /**
         * status : 0
         * value : 识别完成
         */

        private int status;
        private String value;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class CardsinfoBean {
        /**
         * type : 19
         * items : [{"nID":null,"index":null,"desc":"车牌号","content":"苏K**309"},{"nID":null,"index":null,"desc":"车牌颜色","content":"蓝"},{"nID":null,"index":null,"desc":"车牌颜色","content":"1"},{"nID":null,"index":null,"desc":"车牌类型","content":"1"},{"nID":null,"index":null,"desc":"整牌可信度","content":"83"},{"nID":null,"index":null,"desc":"亮度评价","content":"116"},{"nID":null,"index":null,"desc":"车牌运动方向","content":"0"},{"nID":null,"index":null,"desc":"车牌位置(left_top_right_bottom)","content":"29_3_47_31"}]
         */

        private String type;
        private List<ItemsBean> items;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * nID : null
             * index : null
             * desc : 车牌号
             * content : 苏K**309
             */

            private Object nID;
            private Object index;
            private String desc;
            private String content;

            public Object getNID() {
                return nID;
            }

            public void setNID(Object nID) {
                this.nID = nID;
            }

            public Object getIndex() {
                return index;
            }

            public void setIndex(Object index) {
                this.index = index;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
