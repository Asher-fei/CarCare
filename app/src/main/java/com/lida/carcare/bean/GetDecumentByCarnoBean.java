package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 员工绩效的具体项目
 * Created by Administrator on 2017/6/14.
 */

public class GetDecumentByCarnoBean extends NetResult
{

    /**
     * data : {"msg":"操作成功","document":[{"carNo":"辽190909","entryTime":"2017-06-13 18:23:32","amount":"25.00","project":"美孚金装一号"},{"carNo":"辽190909","entryTime":"2017-06-13 18:23:32","amount":"25.0","project":"接待"}]}
     */

    private DataBean data;

    public static GetDecumentByCarnoBean parse(String json) throws AppException
    {
        GetDecumentByCarnoBean res = new GetDecumentByCarnoBean();
        try
        {
            res = gson.fromJson(json, GetDecumentByCarnoBean.class);
        } catch (JsonSyntaxException e)
        {
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

    public static class DataBean{
        /**
         * msg : 操作成功
         * document : [{"carNo":"辽190909","entryTime":"2017-06-13 18:23:32","amount":"25.00","project":"美孚金装一号"},{"carNo":"辽190909","entryTime":"2017-06-13 18:23:32","amount":"25.0","project":"接待"}]
         */

        private String msg;
        private List<DocumentBean> document;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<DocumentBean> getDocument() {
            return document;
        }

        public void setDocument(List<DocumentBean> document) {
            this.document = document;
        }

        public static class DocumentBean extends NetResult{
            /**
             * carNo : 辽190909
             * entryTime : 2017-06-13 18:23:32
             * amount : 25.00
             * project : 美孚金装一号
             */

            private String carNo;
            private String entryTime;
            private String amount;
            private String project;

            public String getCarNo() {
                return carNo;
            }

            public void setCarNo(String carNo) {
                this.carNo = carNo;
            }

            public String getEntryTime() {
                return entryTime;
            }

            public void setEntryTime(String entryTime) {
                this.entryTime = entryTime;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getProject() {
                return project;
            }

            public void setProject(String project) {
                this.project = project;
            }
        }
    }
}
