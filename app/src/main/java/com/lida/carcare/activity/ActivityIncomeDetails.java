package com.lida.carcare.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterIncomeDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.IncomeDetailsBean;
import com.lida.carcare.data.ActivityIncomeDetailData;
import com.lida.carcare.tpl.ActivityIncomeDetailTpl;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收入明细
 * Created by Administrator on 2017/6/30.
 */

public class ActivityIncomeDetails extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.cashReceipts)
    TextView cashReceipts;
    @BindView(R.id.weChatIncome)
    TextView weChatIncome;
    @BindView(R.id.alipayRevenue)
    TextView alipayRevenue;
    @BindView(R.id.creditCardIncome)
    TextView creditCardIncome;
    @BindView(R.id.innerListView)
    ListView innerListView;

    private String dateTime = "";
    private String flag = "";
    ActivityIncomeDetailData dataSource = null;

    AdapterIncomeDetail adapterIncomeDetail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_details);
        ButterKnife.bind(this);
        topbar.setTitle("收入明细");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        dateTime = getIntent().getStringExtra("dateTime");
        flag = getIntent().getStringExtra("flag");
        if(flag!=null) {
            if (flag.equals("Day")) {
                AppUtil.getCarApiClient(ac).selectCarIncomeDayByTime(dateTime, this);
            } else if (flag.equals("Month")) {
                AppUtil.getCarApiClient(ac).selectCarIncomeMonthByTime(dateTime, this);
            }
        }



    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            IncomeDetailsBean bean = (IncomeDetailsBean)res;
            if (bean!=null) {
                cashReceipts.setText("¥ "+bean.getData().getCashReceipts());
                weChatIncome.setText("¥ "+bean.getData().getWeChatIncome()+"");
                alipayRevenue.setText("¥ "+bean.getData().getAlipayRevenue()+"");
                creditCardIncome.setText("¥ "+bean.getData().getCreditCardIncome()+"");
                if(bean.getData().getCarIncomeSpending()!=null) {
                    if (bean.getData().getCarIncomeSpending().size() > 0) {
                        adapterIncomeDetail = new AdapterIncomeDetail(bean.getData().getCarIncomeSpending(), ac);
                        innerListView.setAdapter(adapterIncomeDetail);
                    }
                }
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
    }
}
