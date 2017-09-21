package com.lida.carcare.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterOrderDetailShop;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OrderPlaceDetailBean;
import com.lida.carcare.bean.WeiXinPayBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogOrderPay;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.wxapi.WXConstants;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单详情
 * Created by xkr on 2017/7/24.
 */

public class ActivityOrderDetail extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.orderCode)
    TextView orderCode;
    @BindView(R.id.Status)
    TextView Status;
    @BindView(R.id.supplier)
    TextView supplier;
    @BindView(R.id.logisticsCode)
    TextView logisticsCode;
    @BindView(R.id.logisticsCompany)
    TextView logisticsCompany;
    @BindView(R.id.createTime)
    TextView createTime;
    @BindView(R.id.innerListView)
    InnerListView innerListView;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    @BindView(R.id.btnOK)
    Button btnOK;
    private String id = "";

    private IWXAPI iwxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        topbar.setTitle("订单详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("查询物流", listener);
        if (getIntent().getStringExtra("id") != null) {
            id = getIntent().getStringExtra("id");
            AppUtil.getCarApiClient(ac).selectOrderPlaceById(id, this);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jump(_activity, ActivityOrderLogisticsWeb.class); //网页
            // UIHelper.jump(_activity,ActivityOrderLogistics.class);
        }
    };

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
            OrderPlaceDetailBean bean = (OrderPlaceDetailBean) res;
            if (bean != null && bean.getData() != null) {
                OrderPlaceDetailBean.DataBean data = bean.getData();
                orderCode.setText(data.getOrderCode() == null ? "" : data.getOrderCode());
                supplier.setText(data.getSupplierName() == null ? "" : data.getSupplierName());
                logisticsCode.setText(data.getTrackingNumber() == null ? "" : data.getTrackingNumber());
                logisticsCompany.setText(data.getLogisticsCompany() == null ? "" : data.getLogisticsCompany());
                createTime.setText(data.getCreateDate() == null ? "" : data.getCreateDate());
                totalPrice.setText(data.getTotalPrice() == null ? "" : data.getTotalPrice());
                if (data.getState() != null) {
                    if (data.getState().equals("0")) {
                        Status.setText("待付款");
                        btnOK.setVisibility(View.VISIBLE);
                    } else if (data.getState().equals("1")) {
                        Status.setText("待发货");
                        btnOK.setVisibility(View.GONE);
                    } else if (data.getState().equals("2")) {
                        Status.setText("已发货");
                        btnOK.setVisibility(View.GONE);
                    } else if (data.getState().equals("3")) {
                        Status.setText("已收货");
                        btnOK.setVisibility(View.GONE);
                    }

                }
                if (data.getOrderPlaceGoodsList() != null && data.getOrderPlaceGoodsList().size() > 0) {
                    innerListView.setAdapter(new AdapterOrderDetailShop(data.getOrderPlaceGoodsList(), _activity));
                }
            }
        }
    }

    @OnClick(R.id.btnOK)
    public void onViewClicked() {
        final DialogOrderPay dialogOrderPay = new DialogOrderPay(_activity);
        dialogOrderPay.setOnItemClickListener(new DialogOrderPay.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                dialogOrderPay.dismiss();
                if (position == 0) {
                    toPayWX();
                }
            }
        });
        dialogOrderPay.show();
    }

    @OnClick(R.id.logisticsCode)
    public void logisticsCodeCopy() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (!logisticsCode.getText().toString().trim().equals("")) {
            cm.setText(logisticsCode.getText().toString().trim());
            UIHelper.t(_activity, "物流单号已复制");
        }
    }

    private void toPayWX() {
        iwxapi = WXAPIFactory.createWXAPI(_activity, WXConstants.APP_ID);
        iwxapi.registerApp(WXConstants.APP_ID);//将应用注册到微信
        if (isWXAppInstalledAndSupported(iwxapi) == false) {
            UIHelper.t(_activity, "请先安装微信客户端");
            return;
        }

        AppUtil.getCarApiClient(ac).WXPay(id, new BaseApiCallback() {
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
                    WeiXinPayBean bean = (WeiXinPayBean) res;
                    LogUtils.i(bean);
                    if (bean != null && bean.getData() != null) {
                        WeiXinPayBean.DataBean data = bean.getData();
                        PayReq request = new PayReq();
                        request.appId = data.getAppid();
                        request.partnerId = data.getPartnerid();
                        request.prepayId = data.getPrepayid();
                        request.packageValue = data.getPackageX();
                        request.nonceStr = data.getNoncestr();
                        request.timeStamp = data.getTimestamp();
                        request.sign = data.getSign();
                        if (iwxapi == null) {
                            iwxapi = WXAPIFactory.createWXAPI(_activity, WXConstants.APP_ID);
                        }
                        LogUtils.i(request);
                        iwxapi.sendReq(request);
                    }
                } else {
                    UIHelper.t(_activity, res.getMsg());
                }
            }

            @Override
            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                super.onApiFailure(t, errorNo, strMsg, tag);
                hideLoadingDlg();
            }
        });


    }

    /**
     * 判断是否安装了微信客户端
     */
    private boolean isWXAppInstalledAndSupported(IWXAPI msgApi) {
        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();

        return sIsWXAppInstalledAndSupported;
    }
}
