package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 退货详情
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityReturnGoods extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    InnerListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returngoods);
        ButterKnife.bind(this);
        topbar.setTitle("退货详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }
}














