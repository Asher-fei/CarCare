package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityRecordDetailData;
import com.lida.carcare.tpl.ActivityRecordDetailTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 记录明细
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityRecordDetail extends BaseListActivity {

    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("记录明细");
        listView.setDivider(null);
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityRecordDetailData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityRecordDetailTpl.class;
    }
}
