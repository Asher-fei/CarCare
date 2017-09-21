package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodListBean;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.data.ActivityQueryAllGoodsData;
import com.lida.carcare.data.ActivitySelectOutboundGoodsData;
import com.lida.carcare.tpl.ActivityQueryAllGoodsTpl;
import com.lida.carcare.tpl.ActivitySelectOutboundGoodsTpl;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.DialogChooseStorage;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 领料出库商品列表
 * Created by Administrator on 2017/6/21.
 */

public class ActivitySelectOutboundGoods extends BaseListActivity implements DialogChooseStorage.onItemClickListener {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    EditText tvSearch;

    private TextView tvTitle;

    private StorageListBean bean;
    private DialogChooseStorage dialogChooseStorage;
    private ActivitySelectOutboundGoodsData dataSource;
    public static TextView tvRight;
    private String storageId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvTitle = (TextView) findViewById(R.id.title_tv);
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成(0)");
        topbar.setTitle("选择仓库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvSearch.addTextChangedListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                dataSource.setParams(s.toString(),"",storageId);
                listViewHelper.refresh();
            }
        });
    }

    @OnClick({R.id.title_tv, R.id.right_tv})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.title_tv:
                if(bean==null){
                    AppUtil.getCarApiClient(ac).getRepertoryList(this);
                    return;
                }
                dialogChooseStorage = new DialogChooseStorage(_activity, tvTitle, bean.getData());
                dialogChooseStorage.setOnItemClickListener(this);
                dialogChooseStorage.show();
                break;
            case R.id.right_tv:
                ArrayList<SelectOutboundGoodslistBean.DataBean> temp = new ArrayList<>();
                ArrayList<SelectOutboundGoodslistBean.DataBean> data = dataSource.getResultList();
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < data.size(); j++) {
                        if(data.get(i).isSelect()){
                            temp.add(data.get(i));
                        }
                    }
                }
                LogUtils.e("temp:"+temp);
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("data",temp);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            bean = (StorageListBean) res;
            dialogChooseStorage = new DialogChooseStorage(_activity, tvTitle, bean.getData());
            dialogChooseStorage.setOnItemClickListener(this);
            dialogChooseStorage.show();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_outbound;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivitySelectOutboundGoodsData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivitySelectOutboundGoodsTpl.class;
    }

    @Override
    public void onItemClicked(int position) {
        storageId = bean.getData().get(position).getId();
        dataSource.setParams("","",storageId);
        listViewHelper.refresh();
    }
}
