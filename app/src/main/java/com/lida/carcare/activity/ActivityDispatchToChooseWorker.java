package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.lida.carcare.R;
import com.lida.carcare.bean.GetUserListBean;
import com.lida.carcare.data.ActivityDispatchToChooseWorkerData;
import com.lida.carcare.tpl.ActivityDispatchToChooseWorkerTpl;
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
 * 根据项目选择员工
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityDispatchToChooseWorker extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnOk)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("选择技师");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rolepermission;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityDispatchToChooseWorkerData(_activity, mBundle.getString("item"));
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityDispatchToChooseWorkerTpl.class;
    }


    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        List<GetUserListBean.DataBean> data = (List<GetUserListBean.DataBean>) dataSource.getResultList();
        StringBuilder ids = new StringBuilder();
        StringBuilder names = new StringBuilder();
        String sIds = "";
        String sNames = "";
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).isSelect()){
                ids.append(data.get(i).getId()+",");
                names.append(data.get(i).getUsername()+",");
            }
        }
        if(ids.length()>1){
            sIds = ids.substring(0,ids.length()-1);
            sNames = names.substring(0,names.length()-1);
        }
        Intent intent = new Intent();
        intent.putExtra("ids",sIds);
        intent.putExtra("names",sNames);
        intent.putExtra("position",mBundle.getInt("position"));
        setResult(RESULT_OK,intent);
        finish();
    }
}
