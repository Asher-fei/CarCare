package com.lida.carcare.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 试用期结束，签约
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityOverDue extends BaseActivity {
    @BindView(R.id.btnSign)
    Button btnSign;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSign, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSign:
                break;
            case R.id.btnLogin:
                break;
        }
    }
}
