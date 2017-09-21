package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityChooseCustomerData;
import com.lida.carcare.tpl.ActivityChooseCustomerTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择客户
 * Created by Administrator on 2017/4/5.
 */

public class ActivityChooseCustomer extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private ActivityChooseCustomerData dataSource;
    public String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        flag = getIntent().getExtras().getString("flag");
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("选择客户");
        topbar.setRightText("新增客户", listener);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    dataSource.setStr(etSearch.getText().toString());
                    listViewHelper.refresh();
                }
                return false;
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jumpForResult(_activity, ActivityAddCustomer.class,1001);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choosecustomer;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityChooseCustomerData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityChooseCustomerTpl.class;
    }
}
