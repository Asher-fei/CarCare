package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 领料
 * Created by WeiQingFeng on 2017/5/9.
 */

public class ActivityReceiveGoodsCar extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnCarNum)
    TextView btnCarNum;
    @BindView(R.id.btnName)
    TextView btnName;
    @BindView(R.id.tvServiceItem)
    TextView tvServiceItem;
    @BindView(R.id.tvServer)
    TextView tvServer;
    @BindView(R.id.tvDate)
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivegoodscar);
        ButterKnife.bind(this);
        topbar.setTitle("领料");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnReveiceGoods)
    public void onViewClicked() {
        UIHelper.jump(_activity,ActivityReceiveGoods.class);
    }
}
