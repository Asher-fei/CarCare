package com.lida.carcare.activity;

import android.os.Bundle;

import com.codbking.widget.OnSureLisener;
import com.lida.carcare.R;
import com.lida.carcare.data.ActivityDispatchData;
import com.lida.carcare.tpl.ActivityDispatchTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.Date;

/**
 * 派单
 * Created by Administrator on 2017/4/6.
 */

public class ActivityDispatch extends BaseListActivity implements OnSureLisener
{

    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
//        tvTime = (TextView) findViewById(R.userId.tvTime);
//        tvTime.setOnClickListener(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("派工");
        listView.setDividerHeight(10);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_dispatch;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource()
    {
        return new ActivityDispatchData(_activity);
    }

    @Override
    protected Class getTemplateClass()
    {
        return ActivityDispatchTpl.class;
    }

    @Override
    public void onSure(Date date)
    {

    }
}
