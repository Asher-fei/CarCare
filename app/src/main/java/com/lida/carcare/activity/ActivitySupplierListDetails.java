package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.SupplierDetailsBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 供应商详情
 * Created by Administrator on 2017/6/20.
 */

public class ActivitySupplierListDetails extends BaseActivity
{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSupplier)
    TextView tvSupplier;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvFax)
    TextView tvFax;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvRemarks)
    TextView tvRemarks;
    @BindView(R.id.tvOtherName)
    TextView tvOtherName;
    @BindView(R.id.tvBankCard)
    TextView tvBankCard;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_list_details);
        ButterKnife.bind(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("编辑", new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UIHelper.jumpForResult(_activity, ActivitySupplierListEdit.class, mBundle, ActivitySupplierListEdit.REQUEST_CODE);
            }
        });
        SupplierDetailsBean bean = (SupplierDetailsBean) mBundle.get("bean");
        initView(bean);
    }

    private void initView(SupplierDetailsBean bean)
    {
        topbar.setTitle(bean.getData().getSupplierCompany());
        tvSupplier.setText(bean.getData().getSupplierCompany());
        tvName.setText(bean.getData().getSupplierName());
        tvPhone.setText(bean.getData().getMobilephoneNo());
        tvFax.setText(bean.getData().getFaxNo());
        tvAddress.setText(bean.getData().getAddress());
        tvRemarks.setText(bean.getData().getRemark());
        tvBankCard.setText(bean.getData().getBankName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (ActivitySupplierListEdit.REQUEST_CODE == requestCode && resultCode == ActivitySupplierListEdit.REQUEST_CODE)
        {
            SupplierDetailsBean bean = (SupplierDetailsBean) data.getSerializableExtra("bean");
            initView(bean);
        }
    }
}
