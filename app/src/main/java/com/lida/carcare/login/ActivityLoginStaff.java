package com.lida.carcare.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 员工登录
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityLoginStaff extends BaseActivity {


    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private String phone;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginstaff);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnCode, R.id.btn_login})
    public void onViewClicked(View view) {
        phone = etPhone.getText().toString().trim();
        code = etCode.getText().toString().trim();
        if ("".equals(phone)) {
            UIHelper.t(_activity, "请输入手机号");
            return;
        }
        if (Func.isMobileNO(phone)) {
            UIHelper.t(_activity, "请输入正确的手机号");
            return;
        }
        switch (view.getId()) {
            case R.id.btnCode:
                break;
            case R.id.btn_login:
                if ("".equals(phone)) {
                    UIHelper.t(_activity, "请输入手机号");
                    return;
                }
                if (Func.isMobileNO(phone)) {
                    UIHelper.t(_activity, "请输入正确的手机号");
                    return;
                }
                if ("".equals(code)) {
                    UIHelper.t(_activity, "请输入验证码");
                    return;
                }
                break;
        }
    }
}
