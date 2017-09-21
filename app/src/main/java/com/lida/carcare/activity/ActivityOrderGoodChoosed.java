package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ChooseOrderGoodBean;
import com.lida.carcare.bean.ProductsInfo;
import com.lida.carcare.data.ActivityOrderGoodChoosedData;
import com.lida.carcare.data.ActivityOrderListData;
import com.lida.carcare.tpl.ActivityChooseOrderGoodTpl;
import com.lida.carcare.tpl.ActivityOrderGoodChoosedTpl;
import com.lida.carcare.tpl.ActivityWorkOrderTpl;
import com.lida.carcare.widget.SharepreferenceUtils;
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
 * 已选择订货商品列表
 * Created by Administrator on 2017/7/24.
 */

public class ActivityOrderGoodChoosed extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.btnAddGood)
    Button btnAddGood;
    @BindView(R.id.btnOrder)
    Button btnOrder;

    private ChooseOrderGoodBean.DataBean temp;
    private ArrayList<ChooseOrderGoodBean.DataBean> dataBeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("已选商品");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        temp = (ChooseOrderGoodBean.DataBean) mBundle.getSerializable("bean");
        if (temp != null) {
            dataBeen.add(temp);
            listViewHelper.refresh();
            LogUtils.i(dataBeen);
        }
        String goodId = mBundle.getString("goodId");
        if (goodId != null) {
            AppUtil.getCarApiClient(ac).SelectProductById(goodId, this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ordergoodchoosed;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityOrderGoodChoosedData(_activity, dataBeen);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityOrderGoodChoosedTpl.class;
    }

    @OnClick({R.id.btnAddGood, R.id.btnOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddGood:
                UIHelper.jump(_activity, ActivityChooseOrderGood.class);
                break;
            case R.id.btnOrder:
                save();
                break;
        }
    }


    private void save() {
        if (dataBeen.size() == 0) {
            UIHelper.t(_activity, "请先选择商品");
            return;
        }
        StringBuilder price = new StringBuilder();
        StringBuilder goodNum = new StringBuilder();
        StringBuilder goodId = new StringBuilder();
        for (ChooseOrderGoodBean.DataBean item : dataBeen) {
            price.append(item.getPrice() + ",");
            if (item.getCount() == null) {
                goodNum.append("1,");
            } else {
                goodNum.append(item.getCount() + ",");
            }
            goodId.append(item.getId()+",");
        }
        AppUtil.getCarApiClient(ac).saveplaceAnOrder("", price.toString(), goodNum.toString(), goodId.toString(), this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("saveplaceAnOrder".equals(tag)) {
                UIHelper.t(_activity, "操作成功");
                UIHelper.jump(_activity, ActivityOrderedList.class);
            }
            if ("SelectProductById".equals(tag)) {
                ProductsInfo info = (ProductsInfo) res;
                if (info != null && info.getData() != null) {
                    ChooseOrderGoodBean.DataBean dataBean = new ChooseOrderGoodBean.DataBean();
                    dataBean.setId(info.getData().getId());
                    dataBean.setCode(info.getData().getCode());
                    dataBean.setCount("1");
                    dataBean.setPrice(info.getData().getPrice());
                    dataBean.setName(info.getData().getName());
                    dataBean.setGoodsImg(info.getData().getGoodsImg());
                    dataBeen.add(dataBean);
                    listViewHelper.refresh();
                }
            }
        } else {
            if ("saveplaceAnOrder".equals(tag)) {
                UIHelper.t(_activity, res.getMsg());
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("TAG", "intent");
        setIntent(intent);
        temp = (ChooseOrderGoodBean.DataBean) intent.getSerializableExtra("bean");
        if (temp != null) {
            boolean flag = false;
            for (ChooseOrderGoodBean.DataBean dataBean : dataBeen) {
                if (dataBean.getCode().equals(temp.getCode())) {
                    flag = true;
                }
            }
            if (flag == true) {
                UIHelper.t(_activity, "已添加该商品");
            } else {
                dataBeen.add(temp);
                LogUtils.i(dataBeen);
                listViewHelper.refresh();
            }
        }

    }
}
