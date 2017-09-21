package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityRemindShutDownData;
import com.lida.carcare.tpl.ActivityRemindShutDownTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 已关闭提醒
 * Created by Administrator on 2017/7/5.
 */

public class ActivityRemindShutDown extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("已关闭提醒");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remind_shut_down;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityRemindShutDownData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityRemindShutDownTpl.class;
    }
}
