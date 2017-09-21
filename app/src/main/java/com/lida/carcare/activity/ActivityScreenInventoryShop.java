package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.data.ActivitySelectOutboundGoodsData;
import com.lida.carcare.tpl.ActivityScreenInventoryShopTpl;
import com.lida.carcare.widget.BaseTextWatcher;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增盘点--筛选商品列表
 * Created by Administrator on 2017/7/7.
 */

public class ActivityScreenInventoryShop extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    EditText tvSearch;

    private TextView tvTitle;

    private ActivitySelectOutboundGoodsData dataSource;
    public static TextView tvRight;

    private String repertoryId = "";
    private String productType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvTitle = (TextView) findViewById(R.id.title_tv);
        tvRight = (TextView) findViewById(R.id.right_tv);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成(0)");
        if(getIntent().getStringExtra("repertoryId")!=null){
            repertoryId = getIntent().getStringExtra("repertoryId");
            topbar.setTitle(getIntent().getStringExtra("repertoryName"));
            dataSource.setParams("",productType,repertoryId);
            listViewHelper.refresh();
        }else {
            topbar.setTitle("选择商品");
        }
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvSearch.addTextChangedListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                dataSource.setParams(s.toString(),"",repertoryId);
                listViewHelper.refresh();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_screen_inventory_shop;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivitySelectOutboundGoodsData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityScreenInventoryShopTpl.class;
    }


    @OnClick({R.id.title_tv, R.id.right_tv,R.id.tvScreen})
    public void onViewClicked(View v){
        switch (v.getId()){
            case R.id.title_tv:

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
            case R.id.tvScreen:
                Bundle bundle = new Bundle();
                bundle.putBoolean("ActivityScreenInventoryShop", true);
                UIHelper.jumpForResult(_activity, ActivityGoodsManager.class, bundle, 1001);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            if (requestCode == 1001 && resultCode == RESULT_OK) {
                if (data.getStringExtra("productType") != null) {
                    productType = data.getStringExtra("productType");
                    dataSource.setParams("", productType, repertoryId);
                    listViewHelper.refresh();
                }
            }
        }
    }


}
