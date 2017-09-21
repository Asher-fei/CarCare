package com.lida.carcare.activity;

import android.graphics.Color;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityEnterprisesDetailData;
import com.lida.carcare.tpl.ActivityEnterprisesDetailTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 绩效管理详情
 * Created by WeiQingFeng on 2017/4/7.
 */

public class ActivityEnterprisesDetail extends BaseListActivity
{

    private TextView tvMonth;
    private ImageView ivUp, ivDown;
    private TextView tvMoney;
    private BaseLibTopbarView topbar;

    private Animation animation;


    @Override
    protected void initView()
    {
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        ivUp = (ImageView) findViewById(R.id.ivUp);
        ivDown = (ImageView) findViewById(R.id.ivDown);
        tvMoney = (TextView) findViewById(R.id.tvMoney);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("绩效管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        animation = AnimationUtils.loadAnimation(_activity, R.anim.actiondown);
        ivUp.setOnClickListener(this);
        ivDown.setOnClickListener(this);
        TextView textView = new TextView(_activity);
        textView.setPadding(Func.Dp2Px(_activity, 10), Func.Dp2Px(_activity, 20), Func.Dp2Px(_activity, 10), Func.Dp2Px(_activity, 20));
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextSize(15);
        textView.setText("提成清单");
        listView.addHeaderView(textView);
    }

    @Override
    public void onClick(View arg0)
    {
        super.onClick(arg0);
        int month = Integer.valueOf(tvMonth.getText().toString().trim());
        switch (arg0.getId())
        {
            case R.id.ivUp:
                ivDown.clearAnimation();
                ivUp.startAnimation(animation);
                if (month == 12)
                {
                    month = 1;
                } else
                {
                    month++;
                }
                break;
            case R.id.ivDown:
                ivUp.clearAnimation();
                ivDown.startAnimation(animation);
                if (month == 1)
                {
                    month = 12;
                } else
                {
                    month--;
                }
                break;
        }
        tvMonth.setText(month + "");
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_enterprises;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource()
    {
        return new ActivityEnterprisesDetailData(_activity);
    }

    @Override
    protected Class getTemplateClass()
    {
        return ActivityEnterprisesDetailTpl.class;
    }
}
