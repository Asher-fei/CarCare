package com.lida.carcare.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityShopList;
import com.lida.carcare.app.AppUtil;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *员工注册
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityRegisterStaff extends BaseActivity {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.etChooseShop)
    EditText etChooseShop;

    private String name;
    private String phone;
    private String code;
    private String shopId;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerstaff);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnCode, R.id.btn_login})
    public void onViewClicked(View view) {
        name = etName.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        code = etCode.getText().toString().trim();
        shopId = etChooseShop.getText().toString().trim();
        pass = etPass.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btnCode:
                if("".equals(shopId)){
                    UIHelper.t(_activity,"请输入店铺邀请码");
                    return;
                }
                if("".equals(phone)){
                    UIHelper.t(_activity,"请输入手机号");
                    return;
                }
                if(!Func.isMobileNO(phone)){
                    UIHelper.t(_activity,"请输入正确的手机号");
                    return;
                }
                if ("".equals(pass))
                {
                    UIHelper.t(_activity, "请输入密码");
                    return;
                }
                break;
            case R.id.btn_login:
                if("".equals(shopId)){
                    UIHelper.t(_activity,"请输入店铺邀请码");
                    return;
                }
                if("".equals(name)){
                    UIHelper.t(_activity,"请输入姓名");
                    return;
                }
                if("".equals(phone)){
                    UIHelper.t(_activity,"请输入手机号");
                    return;
                }
                if(!Func.isMobileNO(phone)){
                    UIHelper.t(_activity,"请输入正确的手机号");
                    return;
                }
                if ("".equals(pass))
                {
                    UIHelper.t(_activity, "请输入密码");
                    return;
                }
                /*if("".equals(code)){
                    UIHelper.t(_activity,"请输入验证码");
                    return;
                }*/
                AppUtil.getCarApiClient(ac).userRegister(phone,name,"",pass,shopId,this);//
                break;
//            case R.userId.tvChooseShop:
//                UIHelper.jumpForResult(_activity, ActivityShopList.class,1001);
//                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(RESULT_OK == resultCode){
//            shopId = data.getStringExtra("userId");
//            tvChooseShop.setText(data.getStringExtra("name"));
//        }
//    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnLogin.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnLogin.setClickable(true);
        if(res.isOK()){
            UIHelper.t(_activity,"注册成功！");
            finish();
        }else{
            UIHelper.t(_activity, res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnLogin.setClickable(true);
    }
}
