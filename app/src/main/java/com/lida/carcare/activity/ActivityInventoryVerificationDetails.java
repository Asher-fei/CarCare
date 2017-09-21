package com.lida.carcare.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterInventoryVerificationDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.InventoryVerificationDetailBean;
import com.lida.carcare.widget.DialogScreenInventory;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 盘点详情
 */
public class ActivityInventoryVerificationDetails extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    String id = "";
    @BindView(R.id.shoutId)
    TextView shoutId;
    @BindView(R.id.shoutPeason)
    TextView shoutPeason;
    @BindView(R.id.shoutDate)
    TextView shoutDate;
    @BindView(R.id.shoutStock)
    TextView shoutStock;
    @BindView(R.id.shoutOk)
    TextView shoutOk;
    @BindView(R.id.shoutNo)
    TextView shoutNo;
    @BindView(R.id.show_tv)
    TextView showTv;
    @BindView(R.id.pullToRefreshListView)
    ListView pullToRefreshListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_verification_details);
        ButterKnife.bind(this);
        topbar.setTitle("盘点详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        id = getIntent().getStringExtra("id");
        AppUtil.getCarApiClient(ac).selectShoutDetail(id, "", "", this);
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
        if (res != null) {
            if (res.isOK()) {
                InventoryVerificationDetailBean bean = (InventoryVerificationDetailBean) res;
                if (bean != null) {
                    if (bean.getData() != null&&bean.getData().size()>0) {
                        shoutId.setText(bean.getData().get(0).getShoutId());
                        shoutPeason.setText(bean.getData().get(0).getShoutPeason());
                        shoutDate.setText(bean.getData().get(0).getShoutDate());
                       // shoutStock.setText("盘点：" + (bean.getData().getCarShout().get(0).getShoutStock() == null ? "0" : bean.getData().getCarShout().get(0).getShoutStock()));
                        shoutStock.setText("盘点：" + (bean.getData().get(0).getAcount() == null ? "0种" : bean.getData().get(0).getAcount())+"种");
                        shoutOk.setText("盘盈：" + (bean.getData().get(0).getShoutOk() == null ? "0" : bean.getData().get(0).getShoutOk()));
                        shoutNo.setText("盘亏：" + (bean.getData().get(0).getShoutNo() == null ? "0" : bean.getData().get(0).getShoutNo().replace("-", "")));
                        if (bean.getData().get(0).getCarWarehouse() != null && bean.getData().get(0).getCarWarehouse().size() > 0) {
                            AdapterInventoryVerificationDetail adapter = new AdapterInventoryVerificationDetail(bean.getData().get(0).getCarRepertory(), bean.getData().get(0).getCarWarehouse(), bean.getData().get(0).getShoutProductRecord(), _activity);
                            pullToRefreshListView.setAdapter(adapter);
                        }
                    } else {
                        pullToRefreshListView.setAdapter(null);
                    }
                }
            }
        }
    }

    @OnClick(R.id.show_tv)
    public void doScreen() {
        DialogScreenInventory dialog = new DialogScreenInventory(_activity, showTv);
        dialog.setOnItemClickListener(new DialogScreenInventory.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (position == 0) {
                    AppUtil.getCarApiClient(ac).selectShoutDetail(id, "", "", ActivityInventoryVerificationDetails.this);
                } else if (position == 1) {
                    AppUtil.getCarApiClient(ac).selectShoutDetail(id, "1", "", ActivityInventoryVerificationDetails.this);
                } else if (position == 2) {
                    AppUtil.getCarApiClient(ac).selectShoutDetail(id, "", "2", ActivityInventoryVerificationDetails.this);
                }
            }
        });
        dialog.show();
    }

}
