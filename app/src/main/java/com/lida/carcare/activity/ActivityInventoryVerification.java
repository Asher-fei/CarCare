package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityInventoryVerificationData;
import com.lida.carcare.tpl.ActivityInventoryVerificationTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存盘点
 */
public class ActivityInventoryVerification extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;

    ActivityInventoryVerificationData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        listView.setDividerHeight(7);
        topbar.setTitle("库存盘点");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        topbar.setRightText("新增", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jumpForResult(ActivityInventoryVerification.this,ActivityInventoryVerificationNewAdd.class,1001);
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    dataSource.setParams(etSearch.getText().toString().trim());
                    listViewHelper.refresh();
                }
                return false;
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory_verification;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityInventoryVerificationData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityInventoryVerificationTpl.class;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001&&resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }
}
