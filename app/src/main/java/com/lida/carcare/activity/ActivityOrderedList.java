package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityOrderListData;
import com.lida.carcare.tpl.ActivityChooseOrderGoodTpl;
import com.lida.carcare.tpl.ActivityOrderListTpl;
import com.lida.carcare.widget.DialogChooseOrderType;
import com.lida.carcare.widget.DialogOrderListStatus;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的订单
 * Created by Administrator on 2017/7/24.
 */

public class ActivityOrderedList extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;

    ActivityOrderListData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("我的订单");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orderedlist;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityOrderListData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityOrderListTpl.class;
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
        DialogOrderListStatus dialog = new DialogOrderListStatus(_activity,tvSearch);
        dialog.setOnItemClickListener(new DialogOrderListStatus.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if(position==0){
                    dataSource.setStatus("0");
                }else if(position==1){
                    dataSource.setStatus("1");
                }else if(position==2){
                    dataSource.setStatus("2");
                }else if(position==3){
                    dataSource.setStatus("3");
                }else if(position==4){
                    dataSource.setStatus("");
                }
                listViewHelper.refresh();
            }
        });
        dialog.show();
    }
}
