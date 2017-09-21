package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterPositionManage;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.WorkerTreeBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ActivityPositionManage 职业管理
 * Created by Administrator on 2017/6/19.
 */

public class ActivityPositionManage extends BaseActivity
{
    //ActivityDispatchToChooseWorkerNew
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;

    private AdapterPositionManage adapter;
    private WorkerTreeBean bean = new WorkerTreeBean();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_manage);
        ButterKnife.bind(this);
        topbar.setTitle("职位管理");
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
            adapter = new AdapterPositionManage(_activity, exListView, bean);
            exListView.setAdapter(adapter);
            exListView.setGroupIndicator(null);
            exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
            exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        }
    }

}
