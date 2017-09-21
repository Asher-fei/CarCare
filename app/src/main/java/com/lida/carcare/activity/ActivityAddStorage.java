package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 增加仓库
 * Created by Administrator on 2017/6/20.
 */

public class ActivityAddStorage extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnSave)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstorage);
        ButterKnife.bind(this);
        topbar.setTitle("新增仓库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        String name = etName.getText().toString();
        String des = etDes.getText().toString();
        if("".equals(name)){
            UIHelper.t(_activity,"请输入仓库名称!");
            return;
        }
        AppUtil.getCarApiClient(ac).saveRepertory(name,des,this);
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
            UIHelper.t(_activity,"保存成功！");
            setResult(RESULT_OK);
            finish();
        }else {
            UIHelper.t(_activity,res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
