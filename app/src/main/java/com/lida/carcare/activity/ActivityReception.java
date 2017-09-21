package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityReceptionData;
import com.lida.carcare.tpl.ActivityReceptionTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 接车出单
 * Created by Administrator on 2017/4/6.
 */

public class ActivityReception extends BaseListActivity {

    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("接车出单");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("添加新车",onClickListener);
        listView.setDividerHeight(10);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jump(_activity,ActivityAddCar.class);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reception;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityReceptionData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityReceptionTpl.class;
    }
}
