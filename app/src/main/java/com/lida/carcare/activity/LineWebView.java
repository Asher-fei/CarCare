package com.lida.carcare.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/14.
 */

public class LineWebView extends BaseActivity
{
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.title)
    BaseLibTopbarView title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ButterKnife.bind(this);
        title.setTitle("公告信息");
        title.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        String data = mBundle.getString("data");
        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }
}
