package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.lida.carcare.R;
import com.lida.carcare.data.ActivityUnlimitedListData;
import com.lida.carcare.tpl.ActivityUnlimitedListTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 无限卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityUnlimitedList extends BaseListActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;


    private ActivityUnlimitedListData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("无限卡列表");
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
        return R.layout.activity_unlimited_cardslist;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityUnlimitedListData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityUnlimitedListTpl.class;
    }
}
