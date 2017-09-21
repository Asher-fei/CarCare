package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.widget.PopupIncomePrevent;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupOilType;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收入明细录入
 * Created by Administrator on 2017/6/30.
 */

public class ActivityIncomeDetailAdd extends BaseActivity implements OnItemClickListener{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.selectIncomeType)
    TextView selectIncomeType;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.remake)
    EditText remake;
    @BindView(R.id.name)
    EditText name;

    PopupIncomePrevent pop;

    int item = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail_add);
        ButterKnife.bind(this);
        topbar.setTitle("收入明细录入");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        initPop();
    }

    @OnClick({R.id.selectIncomeType,R.id.btnOK})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.selectIncomeType:
                if (pop.isShowing())
                {
                    pop.dismiss();
                } else
                {
                    pop.showAsDropDown(selectIncomeType);
                }
                break;
            case R.id.btnOK:
                if(name.getText().toString().trim().length()==0){
                    UIHelper.t(_activity,"请输入名称");
                    return;
                }

                if(price.getText().toString().trim().length()==0){
                    UIHelper.t(_activity,"请输入收入金额");
                    return;
                }
                if(selectIncomeType.getText().toString().length()==0){
                    UIHelper.t(_activity,"请选择收入方式");
                    return;
                }
                AppUtil.getCarApiClient(ac).savaCarIncomeSpending(name.getText().toString().trim(),"1",price.getText().toString(),item+1+"",remake.getText().toString(),this);
                break;
        }
    }

    @Override
    public void doNext(int positon, String text) {
        selectIncomeType.setText(text);
        item = positon;
        pop.dismiss();
    }

    private void initPop()
    {
        View view = LayoutInflater.from(_activity).inflate(R.layout.layout_spinner, null);
        pop = new PopupIncomePrevent(_activity, view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setOnItemClickListener(this);
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
            UIHelper.t(this,"操作成功");
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            finish();
        }else {
            UIHelper.t(this,"操作失败");
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
