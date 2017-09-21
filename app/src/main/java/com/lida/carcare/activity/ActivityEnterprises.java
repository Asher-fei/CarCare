package com.lida.carcare.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.PerformanceBean;
import com.lida.carcare.data.ActivityEnterprisesData;
import com.lida.carcare.tpl.ActivityEnterprisesDetailTpl;
import com.lida.carcare.tpl.ActivityEnterprisesTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 绩效管理
 * Created by WeiQingFeng on 2017/4/7.
 */

public class ActivityEnterprises extends BaseListActivity<PerformanceBean>
{

    private TextView tvMonth;
    private ImageView ivUp, ivDown;
    public static TextView tvMoney;
    private BaseLibTopbarView topbar;

    private Animation animation;
    private ActivityEnterprisesData dataSource;

    private String year;
    private String month;


    @Override
    protected void initView() {
        Calendar cal = Calendar.getInstance();
        month = (cal.get(Calendar.MONTH) + 1)+"";
        year = cal.get(Calendar.YEAR)+"";
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvMonth.setText(month);
        ivUp = (ImageView) findViewById(R.id.ivUp);
        ivDown = (ImageView) findViewById(R.id.ivDown);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("绩效管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        animation = AnimationUtils.loadAnimation(_activity, R.anim.actiondown);
        ivUp.setOnClickListener(this);
        ivDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        int month = Integer.valueOf(tvMonth.getText().toString().trim());
        switch (arg0.getId()) {
            case R.id.ivUp:
                ivDown.clearAnimation();
                ivUp.startAnimation(animation);
                if (month == 12) {
                    month = 1;
                } else {
                    month++;
                }
                break;
            case R.id.ivDown:
                ivUp.clearAnimation();
                ivDown.startAnimation(animation);
                if (month == 1) {
                    month = 12;
                } else {
                    month--;
                }
                break;
        }
        tvMonth.setText(month + "");
        dataSource.setPartams(month+"");
        listViewHelper.refresh();
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_enterprises;
    }

    @Override
    protected IDataSource<ArrayList<PerformanceBean>> getDataSource() {
        Calendar cal = Calendar.getInstance();
        month = (cal.get(Calendar.MONTH) + 1)+"";
        year = cal.get(Calendar.YEAR)+"";
        dataSource = new ActivityEnterprisesData(_activity, year, month);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        //ActivityEnterprisesTpl 老板
        //ActivityEnterprisesDetailTpl 员工
        return "0".equals(ac.user_type) ? ActivityEnterprisesTpl.class : ActivityEnterprisesDetailTpl.class;
    }
}
