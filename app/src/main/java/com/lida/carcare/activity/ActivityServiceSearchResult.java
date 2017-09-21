package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityServiceSearchResultData;
import com.lida.carcare.tpl.ActivityServiceSearchResultTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服务搜索结果页
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityServiceSearchResult extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    EditText tvSearach;

    private ActivityServiceSearchResultData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("服务");
        tvSearach.setHint("请输入商品名关键词");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvSearach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    dataSource.setName(tvSearach.getText().toString());
                    listViewHelper.refresh();
                }
                return false;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goodsearchresult;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityServiceSearchResultData(_activity,"");
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityServiceSearchResultTpl.class;
    }
}
