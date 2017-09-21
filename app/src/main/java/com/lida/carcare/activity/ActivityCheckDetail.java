package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterCheckDetail;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检测明细
 * Created by WeiQingFeng on 2017/4/17.
 */

public class ActivityCheckDetail extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.btnSendReport)
    Button btnSendReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdetail);
        ButterKnife.bind(this);
        topbar.setTitle("检测明细");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        listView.setAdapter(new AdapterCheckDetail(_activity));
    }

    @OnClick(R.id.btnSendReport)
    public void onViewClicked() {
    }
}
