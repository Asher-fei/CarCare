package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterCustomerDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CustomerDetailBean;
import com.lida.carcare.widget.DialogCall;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 客户详情
 * Created by Administrator on 2017/7/17.
 */

public class ActivityCustomerDetail extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.customerName)
    TextView customerName;
    @BindView(R.id.mobilePhoneNo)
    TextView mobilePhoneNo;
    @BindView(R.id.customerType)
    TextView customerType;
    @BindView(R.id.customerLevelName)
    TextView customerLevelName;
    @BindView(R.id.promoterName)
    TextView promoterName;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthDates)
    TextView birthDates;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.customerCompany)
    TextView customerCompany;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.innerListView)
    InnerListView innerListView;

    String id = "";
    CustomerDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        ButterKnife.bind(this);
        topbar.setTitle("客户详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("编辑", listener);
        if (getIntent().getStringExtra("updateId") != null) {
            id = getIntent().getStringExtra("updateId");
            AppUtil.getCarApiClient(ac).selectCustomerByIdInfonation(id, this);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!id.equals("")) {
                Bundle bundle = new Bundle();
                bundle.putString("updateId", id);
                UIHelper.jumpForResult(_activity, ActivityAddCustomer.class, bundle, 1001);
            }
        }
    };

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        bean = (CustomerDetailBean) res;
        if (bean != null) {
            if (bean.getData() != null) {
                customerName.setText(bean.getData().getCustomerName() == null ? "" : bean.getData().getCustomerName());
                mobilePhoneNo.setText(bean.getData().getMobilePhoneNo() == null ? "" : bean.getData().getMobilePhoneNo());
                if (bean.getData().getCustomerType() != null) {
                    if (bean.getData().getCustomerType().equals("0")) {
                        customerType.setText("个人");
                    } else if (bean.getData().getCustomerType().equals("1")) {
                        customerType.setText("单位");
                    }
                }
                if(bean.getData().getSex()!=null){
                    if(bean.getData().getSex().equals("0")){
                        sex.setText("男");
                    }else if(bean.getData().getSex().equals("1")){
                        sex.setText("女");
                    }
                }

                if (bean.getData().getCustomerLevel() != null) {
                    customerLevelName.setText(bean.getData().getCustomerLevel().getCustomerLevelName() == null ? "" : bean.getData().getCustomerLevel().getCustomerLevelName());
                }
                if (bean.getData().getPromoter() != null) {
                    promoterName.setText(bean.getData().getPromoter().getPromoterName() == null ? "" : bean.getData().getPromoter().getPromoterName());
                }
                birthDates.setText(bean.getData().getBirthDates() == null ? "" : bean.getData().getBirthDates());
                nickname.setText(bean.getData().getNickname()==null?"":bean.getData().getNickname());
                customerCompany.setText(bean.getData().getCustomerCompany() == null ? "" : bean.getData().getCustomerCompany());
                remark.setText(bean.getData().getRemark() == null ? "" : bean.getData().getRemark());
                if (bean.getData().getCustomerContactList() != null) {
                    if (bean.getData().getCustomerContactList().size() > 0) {
                        AdapterCustomerDetail adapter = new AdapterCustomerDetail(bean.getData().getCustomerContactList(), _activity);
                        innerListView.setAdapter(adapter);
                    }
                }
            }
        }
    }

    @OnClick(R.id.mobilePhoneNo)
    public void callPhone(){
        if(!mobilePhoneNo.getText().toString().trim().equals("")){
            new DialogCall(_activity,mobilePhoneNo.getText().toString().trim()).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
