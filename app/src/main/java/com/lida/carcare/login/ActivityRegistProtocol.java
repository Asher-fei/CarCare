package com.lida.carcare.login;

import android.os.Bundle;
import android.webkit.WebView;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 注册协议
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityRegistProtocol extends BaseActivity {


    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registprotocol);
        ButterKnife.bind(this);
    }
}
