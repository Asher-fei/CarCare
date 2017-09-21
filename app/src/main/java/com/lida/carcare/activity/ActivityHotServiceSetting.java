package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.data.ActivityHotServiceSettingData;
import com.lida.carcare.tpl.ActivityHotServiceSettingTpl;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.BaseTextWatcher;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 常用服务设置
 * Created by xkr on 2017/9/11.
 */

public class ActivityHotServiceSetting extends BaseListActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private ActivityHotServiceSettingData dataSource;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hot_service_setting;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityHotServiceSettingData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityHotServiceSettingTpl.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        topbar.setTitle("常用服务列表");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small,listener);
        etSearch.addTextChangedListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if(dataSource!=null) {
                    dataSource.setParams(s.toString());
                    listViewHelper.refresh();
                }
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jumpForResult(_activity,ActivitySelectServer.class,1001);
        }
    };

    BaseApiCallback callback = new BaseApiCallback(){
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
                if(tag.equals("saveJfomCategory")){
                    UIHelper.t(_activity,"操作成功");
                    listViewHelper.refresh();
                }
            }else {
                ac.handleErrorCode(_activity,res.getMsg());
            }
        }


        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001&&resultCode==0x111){
            ServiceGoodBean.DataBean.JfomServiceBean bean = (ServiceGoodBean.DataBean.JfomServiceBean) data.getSerializableExtra("bean");
            if(bean!=null){
                AppUtil.getCarApiClient(ac).saveJfomCategory(bean.getId(),callback);
            }
        }
    }
}
