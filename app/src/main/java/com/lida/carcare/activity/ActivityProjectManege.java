package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityProjectManageData;
import com.lida.carcare.tpl.ActivityProjectManageTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目管理
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityProjectManege extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("项目管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_projectmanage;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityProjectManageData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityProjectManageTpl.class;
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
    }
}
