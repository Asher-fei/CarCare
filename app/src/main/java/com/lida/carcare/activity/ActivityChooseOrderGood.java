package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterPlaceAnOrder;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OrderClassificationBean;
import com.lida.carcare.data.ActivityChooseOrderGoodData;
import com.lida.carcare.tpl.ActivityChooseOrderGoodTpl;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.DialogChooseOrderType;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
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
 * 选择订货商品列表
 * Created by Administrator on 2017/7/24.
 */

public class ActivityChooseOrderGood extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    String id="";

    OrderClassificationBean bean;
    ActivityChooseOrderGoodData dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("选择订货商品");
        Log.i("TAG","ActivityChooseOrderGood onCreate");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        etSearch.addTextChangedListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if(dataSource!=null) {
                    dataSource.setParams(id,"",s.toString());
                    listViewHelper.refresh();
                }
            }
        });
        if(getIntent().getStringExtra("goodId")!=null){
            dataSource.setParams(id,getIntent().getStringExtra("goodId"),"");
            listViewHelper.refresh();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chooseordergood;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        if(getIntent().getStringExtra("id")!=null){
            id = getIntent().getStringExtra("id");
        }
        dataSource = new ActivityChooseOrderGoodData(_activity,id);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityChooseOrderGoodTpl.class;
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
        if(bean==null){
            AppUtil.getCarApiClient(ac).SelectOrderClassification(this);
        }else {
            if(bean.getData()!=null){
                DialogChooseOrderType dialogChooseOrderType = new DialogChooseOrderType(_activity,tvSearch,bean.getData());
                dialogChooseOrderType.setOnItemClickListener(new DialogChooseOrderType.onItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        if(dataSource!=null){
                            id = tvSearch.getTag().toString();
                            dataSource.setParams(id,"","");
                            listViewHelper.refresh();
                        }
                    }
                });
                dialogChooseOrderType.show();
            }
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            if("SelectOrderClassification".equals(tag)){
                 bean = (OrderClassificationBean) res;
                if(bean!=null){
                    if(bean.getData()!=null){
                        DialogChooseOrderType dialogChooseOrderType = new DialogChooseOrderType(_activity,tvSearch,bean.getData());
                        dialogChooseOrderType.setOnItemClickListener(new DialogChooseOrderType.onItemClickListener() {
                            @Override
                            public void onItemClicked(int position) {
                                if(dataSource!=null){
                                    id = tvSearch.getTag().toString();
                                    dataSource.setParams(id,"","");
                                    listViewHelper.refresh();
                                }
                            }
                        });
                        dialogChooseOrderType.show();

                    }
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("TAG","选择订货商品");
    }
}
