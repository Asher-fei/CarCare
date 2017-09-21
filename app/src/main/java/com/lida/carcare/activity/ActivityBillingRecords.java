package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 开单记录
 * Created by WeiQingFeng on 2017/4/7.
 */

public class ActivityBillingRecords extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billingrecords);
        ButterKnife.bind(this);
        topbar.setTitle("开单记录");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_search,null);
    }
}
