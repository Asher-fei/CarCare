package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codbking.widget.OnSureLisener;
import com.lida.carcare.R;
import com.lida.carcare.data.ActivityReceptionData;
import com.lida.carcare.tpl.ActivityWorkOrderTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.Date;

/**
 * 施工单
 * Created by Administrator on 2017/4/6.
 */

public class ActivityWorkOrder extends BaseListActivity implements OnSureLisener {

    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("施工单");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
//        topbar.setRightText("派工",this);
        listView.setDividerHeight(10);
    }

    @Override
    public void onClick(View arg0)
    {
        super.onClick(arg0);
        switch (arg0.getId())
        {
            case R.id.right_tv:
                UIHelper.jump(_activity, ActivityDispatch.class);
                break;
        }
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_workorder;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource()
    {
        return new ActivityReceptionData(_activity, "");
    }

    @Override
    protected Class getTemplateClass()
    {
        return ActivityWorkOrderTpl.class;
    }

    @Override
    public void onSure(Date date)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            listViewHelper.refresh();
        }
    }
}
