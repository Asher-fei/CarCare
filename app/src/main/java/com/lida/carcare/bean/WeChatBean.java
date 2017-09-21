package com.lida.carcare.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/6 0006.
 */

public class WeChatBean {

    /**
     * list : [{"userId":"wechat_20170116034547","title":"整容脸也可以是演技派，瞅瞅李小璐就知道了！","source":"电影天堂","firstImg":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12136083.jpg/640","mark":"","url":"http://v.juhe.cn/weixin/redirect?wid=wechat_20170116034547"}]
     * totalPage : 7478
     * ps : 1
     * pno : 1
     */

    private int totalPage;
    private int ps;
    private int pno;
    private List<ListBean> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * userId : wechat_20170116034547
         * title : 整容脸也可以是演技派，瞅瞅李小璐就知道了！
         * source : 电影天堂
         * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-12136083.jpg/640
         * mark :
         * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20170116034547
         */

        private String id;
        private String title;
        private String source;
        private String firstImg;
        private String mark;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFirstImg() {
            return firstImg;
        }

        public void setFirstImg(String firstImg) {
            this.firstImg = firstImg;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
