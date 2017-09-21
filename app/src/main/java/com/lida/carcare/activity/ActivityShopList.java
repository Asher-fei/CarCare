package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityShopListData;
import com.lida.carcare.tpl.ActivityShopListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataAdapter;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车店列表
 * Created by Administrator on 2017/6/7.
 */

public class ActivityShopList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("选择车店");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityShopListData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityShopListTpl.class;
    }
}
