package com.lida.carcare.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterAddProject;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.ItemListViewHeader;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 充值
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityRecharge extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnAddPackage)
    Button btnAddPackage;
    @BindView(R.id.btnAddProject)
    Button btnAddProject;
    @BindView(R.id.btnGiveProject)
    Button btnGiveProject;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.listAddProject)
    InnerListView listAddProject;
    @BindView(R.id.listGiveProject)
    InnerListView listGiveProject;
    @BindView(R.id.oldPrice)
    TextView oldPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        topbar.setTitle("充值");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, null);
        oldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        listAddProject.addHeaderView(new ItemListViewHeader(_activity, "添加项目"));
        listAddProject.setAdapter(new AdapterAddProject(_activity));
        listGiveProject.addHeaderView(new ItemListViewHeader(_activity, "赠送项目"));
        listGiveProject.setAdapter(new AdapterAddProject(_activity));
    }


    @OnClick({R.id.btnAddPackage, R.id.btnAddProject, R.id.btnGiveProject, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddPackage:
                break;
            case R.id.btnAddProject:
                UIHelper.jump(_activity, ActivityAddProject.class);
                break;
            case R.id.btnGiveProject:
                break;
            case R.id.btnSave:
                break;
        }
    }
}
