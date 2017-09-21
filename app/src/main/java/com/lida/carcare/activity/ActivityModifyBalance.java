package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 变更金额
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityModifyBalance extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnSave)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifybalance);
        ButterKnife.bind(this);
        topbar.setTitle("变更金额");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        UIHelper.t(_activity,"保存成功！");
        finish();
    }
}
