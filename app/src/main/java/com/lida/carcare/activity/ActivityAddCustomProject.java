package com.lida.carcare.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.AddCustomProjectBean;
import com.lida.carcare.bean.ServiceCustomResultBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.tencent.connect.common.BaseApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加新车--自定义服务项目
 * Created by xkr on 2017/9/15.
 */

public class ActivityAddCustomProject extends BaseActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.count)
    NumberWidget count;
    @BindView(R.id.rbMoney)
    RadioButton rbMoney;
    @BindView(R.id.rbPercentage)
    RadioButton rbPercentage;
    @BindView(R.id.etCommission)
    EditText etCommission;
    @BindView(R.id.btnOK)
    Button btnOK;
    @BindView(R.id.tvServiceClass)
    TextView tvServiceClass;

    private String serviceType = "";//返回的分类id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customproject);
        ButterKnife.bind(this);
        topbar.setTitle("自定义");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnOK, R.id.tvServiceClass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOK:

                if (serviceType == null || serviceType.equals("")) {
                    UIHelper.t(_activity, "请选择项目类型");
                    return;
                }

                if (name.getText().toString().trim().equals("")) {
                    UIHelper.t(_activity, "请输入项目名称");
                    return;
                }
                if (price.getText().toString().trim().equals("")) {
                    UIHelper.t(_activity, "请输入项目单价");
                    return;
                }
                if (etCommission.getText().toString().trim().equals("")) {
                    UIHelper.t(_activity, "请输入项目提成");
                    return;
                }

                String sName = name.getText().toString().trim();
                String sPrice = price.getText().toString().trim();
                String sCommission = etCommission.getText().toString().trim();
                String sCount = count.getNumber();
                String drawType = "";
                if (rbMoney.isChecked()) {
                    drawType = "1";
                } else {
                    drawType = "0";
                }

                AppUtil.getCarApiClient(ac).saveSeriveCustom(sName,serviceType,drawType,sPrice,sCommission,sCount,apiCallback);
               /* AddCustomProjectBean bean = new AddCustomProjectBean(sName, sPrice, sCount, drawType, sCommission);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", bean);
                setResult(RESULT_OK, bundle);
                finish();*/
                break;
            case R.id.tvServiceClass:
                Bundle bundle1 = new Bundle();
                bundle1.putString("type", "choose");
                UIHelper.jumpForResult(this, ActivityServiceClassSetting.class, bundle1, 1001);
                break;
        }
    }


    BaseApiCallback apiCallback = new BaseApiCallback(){
        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            LogUtils.e(res);
            if (res != null) {
                if (res.isOK()) {
                    UIHelper.t(_activity, "操作成功");
                    ServiceCustomResultBean bean = (ServiceCustomResultBean) res;
                    if (bean != null  && bean.getData() != null) {
                        ServiceCustomResultBean.DataBean data = bean.getData();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", data);
                        setResult(RESULT_OK, bundle);
                        finish();
                    } else {
                        UIHelper.t(_activity, "数据异常，请从服务项目里选择");
                    }
                } else {
                    ac.handleErrorCode(_activity, res.getMsg());
                }
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                tvServiceClass.setText(data.getExtras().getString("class"));
                serviceType = data.getExtras().getString("code");
            }
        }
    }


}
