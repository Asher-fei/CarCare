package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodListBean;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.data.ActivityQueryAllGoodsData;
import com.lida.carcare.tpl.ActivityQueryAllGoodsTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 其他出入库-添加商品列表
 * Created by Administrator on 2017/6/21.
 */

public class ActivityChooseGoodsCommen extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    EditText tvSearch;
    @BindView(R.id.tvScreen)
    TextView tvScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("选择商品");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity,ActivityGoodsClass.class);
            }
        });
        tvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AppUtil.getCarApiClient(ac).selectProductByShopId(tvSearch.getText().toString().trim(), ActivityChooseGoodsCommen.this);
            }
        });
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        hideLoadingDlg();
        if (res.isOK()) {
            QueryAllGoodsBean bean = (QueryAllGoodsBean) res;
            adapter.setData((ArrayList) bean.getData(), true);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityGoodsManager.REQUEST_CODE && resultCode == ActivityGoodsManager.REQUEST_CODE) {
            GoodListBean.DataBean.JfomGoodsBean bean = (GoodListBean.DataBean.JfomGoodsBean) data.getSerializableExtra("bean");
            ArrayList<QueryAllGoodsBean.DataBean> list = adapter.getData();
            for (QueryAllGoodsBean.DataBean tmp : list) {
                if (tmp.getName().equals(bean.getName())) {
                    UIHelper.t(_activity, "商品已存在!");
                    return;
                }
            }
            QueryAllGoodsBean.DataBean dataBean = new QueryAllGoodsBean.DataBean();
            dataBean.setId(bean.getId());
            dataBean.setInternationalCode(bean.getInternationalCode());
            dataBean.setName(bean.getName());
            dataBean.setProductImg(bean.getProductImg());
            adapter.getData().add(dataBean);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_query_all_goods;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityQueryAllGoodsData(_activity);
    }

    @Override
    protected Class getTemplateClass()
    {
        return ActivityQueryAllGoodsTpl.class;
    }
}
