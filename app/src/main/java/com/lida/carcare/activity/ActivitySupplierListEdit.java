package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SupplierDetailsBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * （新增）编辑供应商
 * Created by Administrator on 2017/6/20.
 */

public class ActivitySupplierListEdit extends BaseActivity
{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSupplier)
    EditText etSupplier;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etBankCard)
    EditText etBankCard;
    @BindView(R.id.etFax)
    EditText etFax;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etRemarks)
    EditText etRemarks;

    private String id = null;

    public static final int REQUEST_CODE = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_list_edit);
        ButterKnife.bind(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("新增供应商");
        if (mBundle != null) {
            SupplierDetailsBean bean = (SupplierDetailsBean) mBundle.get("bean");
            id = bean.getData().getId();
            topbar.setTitle(bean.getData().getSupplierCompany());
            etSupplier.setText(bean.getData().getSupplierCompany());
            etName.setText(bean.getData().getSupplierName());
            etPhone.setText(bean.getData().getMobilephoneNo());
            etBankCard.setText(bean.getData().getAccountNo());
            etFax.setText(bean.getData().getFaxNo());
            etAddress.setText(bean.getData().getAddress());
            etRemarks.setText(bean.getData().getRemark());
        }
        topbar.setRightText("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(getEditText(etSupplier)) || "".equals(getEditText(etName)) || "".equals(getEditText(etPhone)))
                {
                    UIHelper.t(v.getContext(), "带星号的选项不能为空!");
                    return;
                }
                if (id != null) {
                    AppUtil.getCarApiClient(ac).updatesupplier(id, getEditText(etSupplier), getEditText(etName),
                            getEditText(etPhone), getEditText(etFax), getEditText(etAddress), getEditText(etRemarks),
                            ActivitySupplierListEdit.this);
                } else {
                    AppUtil.getCarApiClient(ac).savesupplier(getEditText(etSupplier), getEditText(etName),
                            getEditText(etPhone), getEditText(etFax), getEditText(etAddress), getEditText(etRemarks),
                            getEditText(etBankCard), ActivitySupplierListEdit.this);
                }
            }
        });
    }

    private String getEditText(EditText editText)
    {
        String text = null;
        if (editText != null)
        {
            text = editText.getText().toString().trim();
        }
        return text;
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag)
    {
        hideLoadingDlg();
        if (res.isOK())
        {
            if ("savesupplier".equals(tag))
            {
                UIHelper.t(_activity,"新增成功！");
                setResult(RESULT_OK);
                finish();
            } else if ("updatesupplier".equals(tag))
            {
                Intent data = new Intent();
                SupplierDetailsBean bean = (SupplierDetailsBean) res;
                data.putExtra("bean", bean);
                setResult(REQUEST_CODE, data);
            }
        }else {
            if ("savesupplier".equals(tag))
            {
                UIHelper.t(_activity,res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
