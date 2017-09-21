package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.PositionBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 职位
 * Created by Administrator on 2017/6/17.
 */

public class ActivityDepartment extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ButterKnife.bind(this);
        AppUtil.getCarApiClient(ac).findTsDepartAndCount(this);
        LogUtils.e("onCreate");
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            PositionBean bean = (PositionBean) res;
            for (int i = 0; i < bean.getData().size(); i++) {
                RadioButton radioButton = (RadioButton) LayoutInflater.from(_activity).inflate(R.layout.item_department, null);
                radioButton.setText(bean.getData().get(i).getDepartname());
                radioGroup.addView(radioButton);
            }
        }
    }
}
