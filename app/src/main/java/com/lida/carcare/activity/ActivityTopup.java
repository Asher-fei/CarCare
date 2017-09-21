package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ConsumeCardBean;
import com.lida.carcare.widget.DialogConsumeCard;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 充值
 * Created by Administrator on 2017/6/30.
 */

public class ActivityTopup extends BaseActivity{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    /*@BindView(R.id.selectCard)
    TextView selectCard;*/
    @BindView(R.id.selectCard)
    EditText selectCard;
    @BindView(R.id.residualAmount)
    EditText residualAmount;

    ConsumeCardBean bean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        ButterKnife.bind(this);
        topbar.setTitle("充值");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).getConsumeCard(this);
    }


    @OnClick({R.id.selectCard,R.id.btnReceive})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.selectCard:
                /*if(bean!=null){
                    if(bean.getData()!=null) {
                        Log.i("TAG","dialog");
                        new DialogConsumeCard(_activity, selectCard, bean.getData()).show();
                    }
                }*/

                break;
            case R.id.btnReceive:
                if(selectCard.getText().toString().trim().length()==0){
                    UIHelper.t(ac,"请输入卡号");
                    return;
                }
                if(residualAmount.getText().toString().trim().length()==0){
                    UIHelper.t(ac,"请输入金额");
                    return;
                }

                AppUtil.getCarApiClient(ac).rechargeTheCard(selectCard.getText().toString(),residualAmount.getText().toString().trim(),this);
                break;
        }
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
        if(res.isOK()){
            if("getConsumeCard".equals(tag)){
                bean = (ConsumeCardBean)res;
            }
            if("rechargeTheCard".equals(tag)){
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                UIHelper.t(ac,"充值成功");
                finish();
            }
        }else {
            if("rechargeTheCard".equals(tag)){
                UIHelper.t(ac,res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
