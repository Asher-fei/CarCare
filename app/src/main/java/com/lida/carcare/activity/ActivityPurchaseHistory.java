package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityPurchaseHistoryData;
import com.lida.carcare.tpl.ActivityPurchaseHistoryTpl;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.DialogPurchaseHistoryType;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 采购记录
 * Created by Administrator on 2017/6/21.
 */

public class ActivityPurchaseHistory extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private ActivityPurchaseHistoryData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("采购记录");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        etSearch.addTextChangedListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                dataSource.setParams("3",s.toString());
                listViewHelper.refresh();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchasehistory;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityPurchaseHistoryData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityPurchaseHistoryTpl.class;
    }

    @OnClick(R.id.tvType)
    public void onViewClicked() {
        DialogPurchaseHistoryType dialog = new DialogPurchaseHistoryType(_activity, tvType);
        dialog.setOnItemClickListener(new DialogPurchaseHistoryType.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                dataSource.setParams(position + "", "");
                listViewHelper.refresh();
            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001&&resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }
}
