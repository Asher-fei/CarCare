package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 改派
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityDispatchChanged extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchchanged);
        ButterKnife.bind(this);
        topbar.setTitle("改派");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }
}
