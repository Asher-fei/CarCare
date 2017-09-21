package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GetUserListBean;
import com.lida.carcare.bean.RoleBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 根据项目选择技师
 * Created by Administrator on 2017/4/5.
 */

public class ActivityDispatchToChooseWorkerData extends BaseListDataSource {

    private String projectName;

    public ActivityDispatchToChooseWorkerData(Context context) {
        super(context);
    }

    public ActivityDispatchToChooseWorkerData(Context context, String projectName) {
        super(context);
        this.projectName = projectName;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        GetUserListBean bean = AppUtil.getCarApiClient(ac).getUserList(projectName, ac.shopId);
        ArrayList<NetResult> models=new ArrayList<>();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
