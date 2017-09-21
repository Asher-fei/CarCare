package com.lida.carcare.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityLackOfBalanceData;
import com.lida.carcare.tpl.ActivityLackOfBalanceTpl;
import com.lida.carcare.widget.BaseTextWatcher;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 余额不足
 * Created by Administrator on 2017/7/3.
 */

public class ActivityLackOfBalance extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private ActivityLackOfBalanceData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("余额不足详情");
        topbar.setBackgroundColor(getResources().getColor(R.color.topbar));
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        listView.setDividerHeight(10);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    dataSource.setParams(etSearch.getText().toString());
                    listViewHelper.refresh();
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lack_of_balance;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityLackOfBalanceData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityLackOfBalanceTpl.class;
    }
}
