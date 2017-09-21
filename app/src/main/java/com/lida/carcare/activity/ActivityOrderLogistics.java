package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterLogistics;
import com.lida.carcare.bean.LogisticsInfoBean;
import com.lida.carcare.widget.logisticsview.LogisticsNodeListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单物流
 * Created by xkr on 2017/8/16.
 */

public class ActivityOrderLogistics extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.Count)
    TextView Count;
    @BindView(R.id.Status)
    TextView Status;
    @BindView(R.id.LogisticsCode)
    TextView LogisticsCode;
    @BindView(R.id.LogisticsCompany)
    TextView LogisticsCompany;
    @BindView(R.id.listview)
    LogisticsNodeListView listview;
    @BindView(R.id.layout_logistics)
    ScrollView layoutLogistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_logistics);
        ButterKnife.bind(this);
        topbar.setTitle("订单物流");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        initLogisticsView();
    }

    private void initLogisticsView() {
        LogisticsCode.setText("9700434538104");
        LogisticsCompany.setText("EMS");
        Status.setText("已签收");
        List<LogisticsInfoBean> list = new ArrayList<>();
        list.add(new LogisticsInfoBean("投递并签收，签收人：本人收", "2017-05-22 16:57:44"));
        list.add(new LogisticsInfoBean("广州市邮政速递物流公司番禺分公司南村营业部安排投递，预计23:59:00前投递（投递员姓名：陈永舜;联系电话：18988900206）", "2017-05-22 16:57:28"));
        list.add(new LogisticsInfoBean("未妥投，原因：收件人不在指定地址", "2017-05-21 12:51:06"));
        list.add(new LogisticsInfoBean("广州市邮政速递物流公司番禺分公司南村营业部安排投递，预计13:00:00前投递（投递员姓名：凌耀兴;联系电话：18922183870）", "2017-05-21 08:24:09"));
        list.add(new LogisticsInfoBean("已离开广州同城处理中心，发往番禺大石速递经营部", "2017-05-21 07:00:55"));
        list.add(new LogisticsInfoBean("到达广州同城处理中心", "2017-05-21 06:28:19"));
        list.add(new LogisticsInfoBean("离开广州航站 发往广州同城处理中心", "2017-05-21 05:45:17"));
        list.add(new LogisticsInfoBean("到达 广州航站 处理中心", "2017-05-19 20:31:00"));
        list.add(new LogisticsInfoBean("离开郑州市 发往广州市", "2017-05-19 13:57:00"));
        list.add(new LogisticsInfoBean("中国邮政速递物流股份有限公司河南省电商物流分公司西已收件（揽投员姓名：荆保生,联系电话:0371-53302448", "2017-05-18 17:36:39"));
        AdapterLogistics adapterLogistics = new AdapterLogistics(list, _activity);
        listview.setAdapter(adapterLogistics);
    }


}
