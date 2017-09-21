package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 采购详情
 * Created by Administrator on 2017/6/28.
 */

public class ActivityPurchaseDetail extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSupplierId)
    TextView tvSupplierId;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvLogisticsCode)
    TextView tvLogisticsCode;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvLogisticsCompany)
    TextView tvLogisticsCompany;
    @BindView(R.id.listView)
    InnerListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchasedetail);
        ButterKnife.bind(this);
        topbar.setTitle("采购详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).selectCarPurchaseDetail(mBundle.getString("id"), this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
