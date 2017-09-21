package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityReadyToReceiveGoodsListData;
import com.lida.carcare.tpl.ActivityReadyToReceiveGoodsListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待领料车辆
 * Created by WeiQingFeng on 2017/5/9.
 */

public class ActivityReadyToReceiveGoodsList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("待领料车辆");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityReadyToReceiveGoodsListData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityReadyToReceiveGoodsListTpl.class;
    }
}
