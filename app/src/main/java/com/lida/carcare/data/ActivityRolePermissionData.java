package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.RoleBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 角色权限
 * Created by Administrator on 2017/4/5.
 */

public class ActivityRolePermissionData extends BaseListDataSource {

    public ActivityRolePermissionData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        RoleBean bean = AppUtil.getCarApiClient(ac).selectEmpRoleListData();
        ArrayList<NetResult> models=new ArrayList<>();
        models.addAll(bean.getData());
        hasMore=false;
        return models;
    }
}
