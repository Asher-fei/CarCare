package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.widget.BaseApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 开充值卡
 * Created by Administrator on 2017/6/29.
 */

public class ActivityPrepaidPhoneCard extends BaseActivity{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @BindView(R.id.consumeCardName)
    EditText consumeCardName;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.consumeCardNo)
    EditText consumeCardNo;
    @BindView(R.id.residualAmount)
    EditText residualAmount;

    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepaid_phone_card);
        ButterKnife.bind(this);
        topbar.setTitle("开充值卡");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick({R.id.btnChooseCustomer,R.id.btnOK})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnChooseCustomer:
                Bundle bundle = new Bundle();
                bundle.putString("flag","ActivityPrepaidPhoneCard");
                UIHelper.jumpForResult(_activity, ActivityChooseCustomer.class, bundle, 1001);
                break;
            case R.id.btnOK:
                save();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e(requestCode);
        LogUtils.e(resultCode);
        LogUtils.e(data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 1001)
            {
                LogUtils.e(data);
                userId = data.getStringExtra("userId");
                consumeCardName.setText(data.getStringExtra("name"));
                mobile.setText(data.getStringExtra("phone"));
                consumeCardName.setEnabled(false);
                mobile.setEnabled(false);
            }
        }
    }

    private void save(){
        if(consumeCardName.getText().toString().trim().length()==0){
            UIHelper.t(this,"请输入客户姓名");
            return;
        }
        if(mobile.getText().toString().trim().length()==0){
            UIHelper.t(this,"请输入手机号");
            return;
        }
        if(consumeCardNo.getText().toString().trim().length()==0){
            UIHelper.t(this,"请输入卡号");
            return;
        }
        if(residualAmount.getText().toString().trim().length()==0){
            UIHelper.t(this,"请输入充值金额");
            return;
        }

        AppUtil.getCarApiClient(ac).saveConsumeCard(consumeCardNo.getText().toString().trim(),consumeCardName.getText().toString().trim(),mobile.getText().toString().trim(),
                residualAmount.getText().toString().trim(),userId,new BaseApiCallback(){
                    @Override
                    public void onApiStart(String tag) {
                        super.onApiStart(tag);
                        showLoadingDlg();
                    }

                    @Override
                    public void onApiSuccess(NetResult res, String tag) {
                        super.onApiSuccess(res, tag);
                        hideLoadingDlg();
                        if(res.isOK()){
                            Intent intent = new Intent();
                            setResult(RESULT_OK,intent);
                            UIHelper.t(_activity,"开卡成功");
                            _activity.finish();
                        }else {
                            UIHelper.t(_activity,res.getMsg());
                        }

                    }

                    @Override
                    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                        super.onApiFailure(t, errorNo, strMsg, tag);
                        hideLoadingDlg();
                    }
                });

    }
}
