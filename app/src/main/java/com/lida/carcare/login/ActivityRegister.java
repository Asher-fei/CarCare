package com.lida.carcare.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCommitData;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.RegisterBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 老板注册
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityRegister extends BaseActivity
{


    @BindView(R.id.etShopName)
    EditText etShopName;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    private String shopName;
    private String name;
    private String phone;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked()
    {
        shopName = etShopName.getText().toString().trim();
        name = etName.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        pass = etPass.getText().toString().trim();
        if ("".equals(shopName))
        {
            UIHelper.t(_activity, "请输入车店名称");
            return;
        }
        if ("".equals(name))
        {
            UIHelper.t(_activity, "请输入姓名");
            return;
        }
        if ("".equals(phone))
        {
            UIHelper.t(_activity, "请输入手机号");
            return;
        }
        if ("".equals(pass))
        {
            UIHelper.t(_activity, "请输入密码");
            return;
        }
        if (!Func.isMobileNO(phone))
        {
            UIHelper.t(_activity, "请输入正确的手机号");
            return;
        }
        AppUtil.getCarApiClient(ac).userRegister(phone, name, shopName, pass, "", this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnRegister.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnRegister.setClickable(true);
        if (res.isOK()) {
            RegisterBean bean = (RegisterBean) res;
            UIHelper.t(_activity, "下一步，提交店铺审核资料！");
            Bundle bundle = new Bundle();
            bundle.putString("shopName", shopName);
            bundle.putString("phone", phone);
            bundle.putString("name", name);
            bundle.putString("userid", bean.getData().getUserid());
            bundle.putString("password", bean.getData().getPassword());
            UIHelper.jump(_activity, ActivityCommitData.class, bundle);
            finish();
        } else {
            UIHelper.t(_activity, res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnRegister.setClickable(true);
    }
}
