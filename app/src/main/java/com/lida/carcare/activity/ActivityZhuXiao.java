package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注销账号
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityZhuXiao extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnZhuXiao)
    Button btnZhuXiao;
    @BindView(R.id.tvPhone)
    TextView tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuxiao);
        ButterKnife.bind(this);
        topbar.setTitle("注销账号");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvPhone.setText("将"+ac.phone+"所绑定的账号注销");
    }

    @OnClick(R.id.btnZhuXiao)
    public void onViewClicked() {
        finish();
        UIHelper.t(_activity, "账号已注销！");
    }
}
