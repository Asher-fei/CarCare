package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterToChooseWorker;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.WorkerTreeBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 根据项目选择员工（最新页面）
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityDispatchToChooseWorkerNew extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.btnOk)
    Button btnOk;

    private AdapterToChooseWorker adapter;
    private WorkerTreeBean bean = new WorkerTreeBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchtochooseworker);
        ButterKnife.bind(this);
        topbar.setTitle("选择技师");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).getDepUserList(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag)
    {
        super.onApiSuccess(res, tag);
        if (res.isOK())
        {
            bean = (WorkerTreeBean) res;
            adapter = new AdapterToChooseWorker(_activity, exListView, bean);
            exListView.setAdapter(adapter);
            exListView.setGroupIndicator(null);
            exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
            exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        }
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        StringBuilder ids = new StringBuilder();
        StringBuilder names = new StringBuilder();
        String sIds = "";
        String sNames = "";
        for (int i = 0; i < bean.getData().size(); i++) {
            for (int j = 0; j < bean.getData().get(i).getTsBaseUser().size(); j++) {
                if(bean.getData().get(i).getTsBaseUser().get(j).isSelect()){
                    ids.append(bean.getData().get(i).getTsBaseUser().get(j).getId()+",");
                    names.append(bean.getData().get(i).getTsBaseUser().get(j).getUsername()+",");
                }
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
