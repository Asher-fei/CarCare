package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityPublicAppointmentData;
import com.lida.carcare.tpl.ActivityPublicAppointmentTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公众号预约
 * Created by xkr on 2017/8/7.
 */

public class ActivityPublicAppointment extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("公众号预约");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_public_appointment;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityPublicAppointmentData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityPublicAppointmentTpl.class;
    }
}
