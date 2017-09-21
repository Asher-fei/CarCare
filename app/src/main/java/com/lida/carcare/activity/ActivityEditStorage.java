package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.StorageListBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑仓库
 * Created by Administrator on 2017/6/20.
 */

public class ActivityEditStorage extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnSave)
    Button btnSave;

    private StorageListBean.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstorage);
        ButterKnife.bind(this);
        bean = (StorageListBean.DataBean) mBundle.getSerializable("bean");
        topbar.setTitle(bean.getRepertoryName());
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        etName.setText(bean.getRepertoryName());
        etDes.setText(bean.getRepertoryRemark());
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        String name = etName.getText().toString();
        String des = etDes.getText().toString();
        if("".equals(name)){
            UIHelper.t(_activity,"请输入仓库名称!");
            return;
        }
        AppUtil.getCarApiClient(ac).updateRepertory(name,des,bean.getId(),this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            UIHelper.t(_activity,"保存成功！");
            setResult(RESULT_OK);
            finish();
        }else {
            UIHelper.t(_activity,res.getMsg());
        }
    }
}
