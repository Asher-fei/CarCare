package com.lida.carcare.bean;

import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * 输入公里数显示服务项目
 * Created by WeiQingFeng on 2017/5/17.
 */

public class GetCarMainTainBean extends NetResult {

    /**
     * data : {"projects":[{"createName":"换机油"},{"createName":"换机油格"},{"createName":"检查全车油水"},{"createName":"检查悬挂"},{"createName":"检查方向"},{"createName":"车内净化"}]}
     */

    private DataBean data;

    public static GetCarMainTainBean parse(String json) throws AppException {
        GetCarMainTainBean res = new GetCarMainTainBean();
        try{
            res = gson.fromJson(json, GetCarMainTainBean.class);
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
        private List<ProjectsBean> projects;

        public List<ProjectsBean> getProjects() {
            return projects;
        }

        public void setProjects(List<ProjectsBean> projects) {
            this.projects = projects;
        }

        public static class ProjectsBean {
            /**
             * createName : 换机油
             */

            private String createName;

            public String getCreateName() {
                return createName;
            }

            public void setCreateName(String createName) {
                this.createName = createName;
            }
        }
    }
}
