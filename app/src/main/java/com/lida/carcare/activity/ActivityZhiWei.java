package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.bean.PositionBean;
import com.lida.carcare.data.ActivityRolePermissionData;
import com.lida.carcare.data.ActivityZhiWeiData;
import com.lida.carcare.tpl.ActivityRolePermissionTpl;
import com.lida.carcare.tpl.ActivityZhiWeiTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 职位
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityZhiWei extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnOk)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("职位");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rolepermission;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityZhiWeiData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityZhiWeiTpl.class;
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        List<PositionBean.DataBean> data = (List<PositionBean.DataBean>) dataSource.getResultList();
        StringBuilder ids = new StringBuilder();
        StringBuilder names = new StringBuilder();
        String sIds = "";
        String sNames = "";
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).isSelect()){
                ids.append(data.get(i).getId()+",");
                names.append(data.get(i).getDepartname()+",");
            }
        }
        if(ids.length()>1){
            sIds = ids.substring(0, ids.length() - 1);
            sNames = names.substring(0, names.length() - 1);
        }
        Intent intent = new Intent();
        intent.putExtra("ids",sIds);
        intent.putExtra("names",sNames);
        LogUtils.e(sIds);
        LogUtils.e(sNames);
        UIHelper.t(_activity, "设置成功！");
        setResult(RESULT_OK,intent);
        finish();
    }
}
