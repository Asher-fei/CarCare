package com.lida.carcare.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.MainActivity;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.LoginBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登陆页
 */
public class ActivityLogin extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.cbRemeber)
    CheckBox cbRemeber;

    private String name;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ac.isUserIdExsit()) {
            UIHelper.jump(_activity, MainActivity.class);
            onDestroy();
            LogUtils.e(ac.isUserIdExsit());
        } else {
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);
            etName.setText(ac.getProperty("name"));
            if (ac.getBoolean("havePass")) {
                cbRemeber.setChecked(true);
                etPass.setText(ac.getProperty("passWord"));
            }
            etName.setSelection(etName.getText().toString().length());
            etPass.setSelection(etPass.getText().toString().length());
        }
    }

    @OnClick({R.id.btn_login, R.id.tvBossReg, R.id.tvMemReg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                name = etName.getText().toString();
                pass = etPass.getText().toString();
                if ("".equals(name)) {
                    AnimatorUtils.onVibrationView(etName);
                    UIHelper.t(_activity, "请输入用户名");
                    return;
                }
                if ("".equals(pass)) {
                    AnimatorUtils.onVibrationView(etPass);
                    UIHelper.t(_activity, "请输入密码");
                    return;
                }
                AppUtil.getCarApiClient(ac).login(name, pass, this);
                break;
            case R.id.tvBossReg:
                UIHelper.jump(_activity,ActivityRegister.class);
//                UIHelper.jump(_activity,ActivityCommitData.class);
                break;
            case R.id.tvMemReg:
                UIHelper.jump(_activity,ActivityRegisterStaff.class);
                break;
        }
    }

//    @OnClick(R.userId.btn_login)
//    public void onClick() {

//    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnLogin.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiStart(tag);
        hideLoadingDlg();
        btnLogin.setClickable(true);
        if (res.isOK()) {
            ac.setProperty("userName", name);
            if (cbRemeber.isChecked()) {
                ac.setBoolean("havePass", true);
                ac.setProperty("passWord", pass);
            } else {
                ac.setBoolean("havePass", false);
                ac.setProperty("passWord", "");
            }
            LoginBean bean = (LoginBean) res;
            //角色标志：0店长1员工2管理员
            ac.saveUserInfo(bean.getData().getUserName(),bean.getData().getShopName(),bean.getData().getShopCode(),
                    bean.getData().getUserId(), bean.getData().getShopId(), bean.getData().getToken(),
                    bean.getData().getHeadPortrait(),bean.getData().getType(),bean.getData().getPhone());
            UIHelper.t(_activity, "登录成功！");
            UIHelper.jump(_activity, MainActivity.class);
            onDestroy();
        } else {
            AnimatorUtils.onVibrationView(btnLogin);
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
