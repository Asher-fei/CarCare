package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityChooseGoodCommenData;
import com.lida.carcare.tpl.ActivityChooseCustomerTpl;
import com.lida.carcare.tpl.ActivityChooseGoodCommenTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataAdapter;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 直接入库选择商品
 * Created by Administrator on 2017/6/29.
 */

public class ActivityChooseGoodCommen extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("商品列表");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityChooseGoodCommenData(_activity,mBundle.getString("code"));
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityChooseGoodCommenTpl.class;
    }
}
