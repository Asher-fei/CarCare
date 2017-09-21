package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityEnterprisesDetailData;
import com.lida.carcare.tpl.ActivityEnterprisesDetailListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 绩效管理-个人绩效收入详情列表
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityEnterprisesDetailList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;

    private String carNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle(carNo);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvAllMoney.setText("￥"+mBundle.getString("money"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enterprisesdetaillist;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        carNo = mBundle.getString("carNo");
        return new ActivityEnterprisesDetailData(_activity, carNo);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityEnterprisesDetailListTpl.class;
    }
}
