package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityProjectDetailData;
import com.lida.carcare.tpl.ActivityProjectDetailTpl;
import com.lida.carcare.widget.DialogAddBeautyItem;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目详情
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityProjectDetail extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.btnAddProject)
    Button btnAddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("美容");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_projectdetail;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityProjectDetailData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityProjectDetailTpl.class;
    }

    @OnClick(R.id.btnAddProject)
    public void onViewClicked() {
        new DialogAddBeautyItem(_activity).show();
    }
}
