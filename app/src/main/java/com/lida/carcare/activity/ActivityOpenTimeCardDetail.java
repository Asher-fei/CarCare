package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterOpenTimeCardDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OpenTimeCardDetailBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.PullToRefreshListView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ActivityOpenTimeCardDetail extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.cardName)
    TextView cardName;
    @BindView(R.id.pullToRefreshListView)
    ListView pullToRefreshListView;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_time_card_detail);
        ButterKnife.bind(this);

        topbar.setTitle("次卡详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        id = getIntent().getStringExtra("id");
        topbar.setRightText("删除",listener);
        AppUtil.getCarApiClient(ac).getOnceCardTypeDetail(id,this);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppUtil.getCarApiClient(ac).deleteOnceCardType(id,ActivityOpenTimeCardDetail.this);
        }
    };

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("getOnceCardTypeDetail".equals(tag)) {
                OpenTimeCardDetailBean bean = (OpenTimeCardDetailBean) res;
                if (bean != null) {
                    cardName.setText(bean.getData().getCardName());
                    if (bean.getData().getOnceCardProjectList() != null) {
                        AdapterOpenTimeCardDetail adapter = new AdapterOpenTimeCardDetail(bean.getData().getOnceCardProjectList(), ac);
                        pullToRefreshListView.setAdapter(adapter);
                    }
                }
            }else if("deleteOnceCardType".equals(tag)){
                UIHelper.t(_activity,"操作成功");
                setResult(RESULT_OK);
                finish();
            }
        }else {
            if("deleteOnceCardType".equals(tag)){
                UIHelper.t(_activity,res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @OnClick(R.id.btnDelete)
    public void delete(){
        AppUtil.getCarApiClient(ac).deleteOnceCardType(id,this);
    }
}
