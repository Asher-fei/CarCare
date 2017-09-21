package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterActivityLookDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.PurchaseDetailBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 待入库-查看明细
 * Created by Administrator on 2017/6/29.
 */

public class ActivityLookDetail extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSupplier)
    TextView tvSupplier;
    @BindView(R.id.tvBillId)
    TextView tvBillId;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.etLogisticsCompany)
    EditText tvLogisticsCompany;
    @BindView(R.id.etLogisticsId)
    EditText tvLogisticsId;
    @BindView(R.id.etRemark)
    EditText tvRemark;
    @BindView(R.id.llTemp)
    LinearLayout llTemp;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.btnOK)
    Button btnOK;

    private List<TempBean> data = new ArrayList<>();
    private AdapterActivityLookDetail adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookdetail);
        ButterKnife.bind(this);
        topbar.setTitle("采购详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).selectCarPurchaseDetail(mBundle.getString("id"), this);
        if(mBundle.getString("flag")!=null){
            topbar.setTitle(mBundle.getString("flag"));
        }
        if ("采购退货".equals(mBundle.getString("flag"))) {
            llTemp.setVisibility(View.GONE);
            topbar.setTitle("采购退货");
        }
        if(mBundle.getBoolean("noUpdate")==false){
        topbar.setRightText("修改", new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btnOK.setVisibility(View.VISIBLE);
                update();
            }
        });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
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
        if (res.isOK()) {
            if("selectCarPurchaseDetail".equals(tag)) {
                PurchaseDetailBean bean = (PurchaseDetailBean) res;
                tvRemark.setText(bean.getData().getRemark());
                tvSupplier.setText("供应商："+bean.getData().getSupplierCompany());
                tvBillId.setText("单据号：" + bean.getData().getBillCode());
                tvTime.setText(bean.getData().getPurchaseDate());
                tvLogisticsCompany.setText(bean.getData().getLogisticsCompany());
                tvLogisticsId.setText(bean.getData().getLogisticsCode());
                if(bean.getData().getLogisticsCompany()==null||bean.getData().getLogisticsCompany().length()==0){
                    llTemp.setVisibility(View.GONE);
                }
                price.setText("¥" + getIntent().getStringExtra("price"));
                if (bean.getData().getCarPurchaseRecords() != null) {
                    for (int i = 0; i < bean.getData().getCarPurchaseRecords().size(); i++) {

                        TempBean temp = new TempBean();
                        temp.setStock(bean.getData().getCarPurchaseRecords().get(i).getStock());
                        if(bean.getData().getJfomGoods()!=null&&i<bean.getData().getJfomGoods().size()) {
                            temp.setName(bean.getData().getJfomGoods().get(i).getName());
                            temp.setProductImg(bean.getData().getJfomGoods().get(i).getProductImg());
                            if ("采购退货".equals(mBundle.getString("flag"))) {
                                temp.setPriceStandardBid(bean.getData().getCarPurchaseRecords().get(i).getReturnPrice());
                            }else {
                                temp.setPriceStandardBid(bean.getData().getJfomGoods().get(i).getPriceStandardBid());
                            }
                            temp.setInternationalCode(bean.getData().getJfomGoods().get(i).getInternationalCode());
                        }
                        data.add(temp);
                    }
                    adapter = new AdapterActivityLookDetail(_activity, data);
                    listView.setAdapter(adapter);
                }
                if(getIntent().getStringExtra("price")==null){
                    price.setText("");
                }
            }else if("updateCarPurchaseDetail".equals(tag)){
                UIHelper.t(this,"操作成功");
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();

            }
        }else {
            if("updateCarPurchaseDetail".equals(tag)) {
                UIHelper.t(this, res.getMsg());
            }
        }
    }

    public class TempBean {
        private String name;
        private String productImg;
        private String priceStandardBid;
        private String stock;
        private String internationalCode;

        public String getInternationalCode() {
            return internationalCode;
        }

        public void setInternationalCode(String internationalCode) {
            this.internationalCode = internationalCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProductImg() {
            return productImg;
        }

        public void setProductImg(String productImg) {
            this.productImg = productImg;
        }

        public String getPriceStandardBid() {
            return priceStandardBid;
        }

        public void setPriceStandardBid(String priceStandardBid) {
            this.priceStandardBid = priceStandardBid;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }
    }



    @OnClick({R.id.btnOK})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnOK:
                if(llTemp.getVisibility()==View.VISIBLE&&tvLogisticsCompany.getText().toString().trim().length()==0){
                    UIHelper.t(ac,"请输入物流公司");
                    return;
                }
                if(llTemp.getVisibility()==View.VISIBLE&&tvLogisticsId.getText().toString().trim().length()==0){
                    UIHelper.t(ac,"请输入物流单号");
                    return;
                }
                AppUtil.getCarApiClient(ac).updateCarPurchaseDetail(mBundle.getString("id"),tvLogisticsCompany.getText().toString().trim(),tvLogisticsId.getText().toString().trim(),tvRemark.getText().toString().trim(),this);

                break;
        }
    }

    private void update(){
        tvLogisticsCompany.setEnabled(true);
        tvLogisticsId.setEnabled(true);
        tvRemark.setEnabled(true);
        tvLogisticsCompany.setFocusable(true);
        tvLogisticsCompany.setFocusableInTouchMode(true);
        tvLogisticsCompany.requestFocus();
    }
}
