package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterSpendingDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SpendingDetailBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 支出明细
 * Created by Administrator on 2017/6/30.
 */

public class ActivitySpendingDetail extends BaseActivity{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.totalSpending)
    TextView totalSpending;
    @BindView(R.id.innerListView)
    ListView innerListView;

    private String dateTime = "";
    private String flag = "";


    AdapterSpendingDetail adapterSpendingDeatail = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_detail);
        ButterKnife.bind(this);
        topbar.setTitle("支出明细");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        dateTime = getIntent().getStringExtra("dateTime");
        flag = getIntent().getStringExtra("flag");
        if(flag!=null) {
            if (flag.equals("Day")) {
                AppUtil.getCarApiClient(ac).selectCarSpendingDayByTime(dateTime, this);
            } else if (flag.equals("Month")) {
                AppUtil.getCarApiClient(ac).selectCarSpendingMonthByTime(dateTime, this);
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
            SpendingDetailBean bean = (SpendingDetailBean)res;
            if (bean!=null) {
                totalSpending.setText("¥ "+bean.getData().getTotalSpending());
                if(bean.getData().getCarIncomeSpending()!=null) {
                    if (bean.getData().getCarIncomeSpending().size() > 0) {
                        adapterSpendingDeatail = new AdapterSpendingDetail(bean.getData().getCarIncomeSpending(), ac);
                        innerListView.setAdapter(adapterSpendingDeatail);
                    }
                }
            }else {
                UIHelper.t(_activity,"数据获取失败");
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
    }


}
