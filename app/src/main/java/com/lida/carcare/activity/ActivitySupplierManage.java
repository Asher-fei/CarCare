package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterSupplier;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.bean.SupplierDetailsBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 供应商清单列表
 * Created by Administrator on 2017/6/20.
 */

public class ActivitySupplierManage extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.lvSupplier)
    ListView lvSupplier;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.etFind)
    EditText etFind;

    private AdapterSupplier adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_manage);
        ButterKnife.bind(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("供应商清单");
        topbar.setRightText("新增", new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                UIHelper.jumpForResult(_activity, ActivitySupplierListEdit.class,1001);
            }
        });
        adapter = new AdapterSupplier(this);
        lvSupplier.setAdapter(adapter);
        etFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = etFind.getText().toString().trim();
                AppUtil.getCarApiClient(ac).findsupplierbyname(str, ActivitySupplierManage.this);
            }
        });
        if (mBundle != null) {
            if (mBundle.getBoolean("isSelect")) {
                adapter.setOnItemClickListener(new AdapterSupplier.OnItemClickListener()
                {
                    @Override
                    public void onClick(NetResult result)
                    {
                        Intent data = new Intent();
                        data.putExtra("bean", result);
                        setResult(RESULT_OK, data);
                        finish();
                    }
                });
            }
        }
        AppUtil.getCarApiClient(ac).getSupplierList(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            AppUtil.getCarApiClient(ac).getSupplierList(this);
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if(res.isOK()){
            if ("getSupplierList".equals(tag) || "findsupplierbyname".equals(tag)) {
                SupplierBean bean = (SupplierBean) res;
                adapter.setData(bean.getData());
                tvCount.setText("供应商数:" + bean.getData().size());
            } else if ("getSupplierListDetails".equals(tag)) {
                SupplierDetailsBean bean = (SupplierDetailsBean) res;
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", bean);
                UIHelper.jump(this, ActivitySupplierListDetails.class, bundle);
            } else if ("deletesupplier".equals(tag)) {
                if (res.isOK()) {
                    UIHelper.t(this, "删除成功");
                    AppUtil.getCarApiClient(ac).getSupplierList(this);
                } else {
                    UIHelper.t(this, "删除失败");
                }
            }
        }
    }
}
