package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterServiceGood;
import com.lida.carcare.adapter.AdapterThreeExpandListView;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择服务类型
 * Created by Administrator on 2017/6/13.
 */

public class ActivitySelectServer extends BaseActivity implements AdapterServiceGood.onChildClickListener
{
    public static final int REQUEST_CODE = 0x111;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.view)
    View view;

    private List<ServiceBean.DataBean> parent = new ArrayList<>();

    private AdapterThreeExpandListView adapterExpandListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        topbar.setTitle("选择服务");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        exListView.setGroupIndicator(null);
        exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        adapterExpandListView = new AdapterThreeExpandListView(_activity, exListView, parent);
        adapterExpandListView.setOnChildClickListener(this);
        exListView.setAdapter(adapterExpandListView);
        AppUtil.getCarApiClient(ac).getCategory(ac.shopId, this);//获取一级分类
    }


    @Override
    public void onApiSuccess(NetResult res, String tag)
    {
        super.onApiSuccess(res, tag);
        if (res.isOK())
        {
            if ("getCategory".equals(tag))
            {
                ServiceBean bean = (ServiceBean) res;
                if (bean.getData() != null)
                {
                    parent.clear();
                    parent.addAll(bean.getData());
                    adapterExpandListView.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onClick(ServiceGoodBean.DataBean.JfomServiceBean bean)
    {
        Intent data = new Intent();
        data.putExtra("bean", bean);
        setResult(REQUEST_CODE, data);
        finish();
    }
}
