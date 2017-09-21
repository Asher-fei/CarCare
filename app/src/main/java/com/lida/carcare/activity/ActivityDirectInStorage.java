package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterOtherWarehouse;
import com.lida.carcare.adapter.AdapterPurchase;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.bean.SelectPurchaseDetailListBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.widget.DialogChoosePayWay;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 其它入库
 * Created by Administrator on 2017/6/21.
 */

public class ActivityDirectInStorage extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSupplier)
    TextView tvSupplier;
    @BindView(R.id.tvPayWay)
    TextView tvPayWay;
    @BindView(R.id.etLogisticsNum)
    EditText etLogisticsNum;
    @BindView(R.id.tvLuggagePayWay)
    TextView tvLuggagePayWay;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.btnAddGood)
    Button btnAddGood;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;
    @BindView(R.id.btnIn)
    Button btnIn;
    @BindView(R.id.btnOut)
    Button btnOut;

    @BindView(R.id.logisticsCompany)
    EditText logisticsCompany;
    @BindView(R.id.logisticsCode)
    EditText logisticsCode;

    private AdapterOtherWarehouse adapter;
    private List<QueryAllGoodsBean.DataBean> listData = new ArrayList<>();

    private StorageListBean bean;
    private String supplierId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directinstorage);
        ButterKnife.bind(this);
        topbar.setTitle("其它入库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).getRepertoryList(this);

    }

    @OnClick({R.id.tvSupplier, R.id.tvPayWay, R.id.tvLuggagePayWay, R.id.btnAddGood, R.id.btnIn, R.id.btnOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSupplier:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSelect", true);
                UIHelper.jumpForResult(_activity, ActivitySupplierManage.class, bundle, 1001);
                break;
            case R.id.tvPayWay:
                new DialogChoosePayWay(_activity,tvPayWay).show();
                break;
            case R.id.tvLuggagePayWay:
                new DialogChoosePayWay(_activity,tvLuggagePayWay).show();
                break;
            case R.id.btnAddGood:
                //UIHelper.jumpForResult(_activity, ActivityGoodsClass.class, 1002);
                if(bean==null){
                    UIHelper.t(_activity,"数据异常，请稍后重新再试");
                }
                Bundle bundle1 = new Bundle();
                bundle1.putString("flag","ActivityDirectInStorage");
                UIHelper.jumpForResult(_activity,ActivityQueryAllGoods.class,bundle1,1002);
                break;
            case R.id.btnIn:
                LogUtils.e(listData);
                btnIn.setFocusable(true);
                btnIn.setFocusableInTouchMode(true);
                btnIn.requestFocus();
                saveInsertPurchase();
                break;
            case R.id.btnOut:
                break;
        }
    }


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

            if("getRepertoryList".equals(tag)){
                bean = (StorageListBean) res;
                adapter = new AdapterOtherWarehouse(_activity, listData,bean);
                listView.setAdapter(adapter);
            }

            if("saveInsertPurchase".equals(tag)){
                UIHelper.t(ac,"操作成功");
                finish();
            }

        }else {
            if("saveInsertPurchase".equals(tag)){
                UIHelper.t(ac,res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==1001){
                SupplierBean.DataBean bean = (SupplierBean.DataBean) data.getSerializableExtra("bean");
                tvSupplier.setText(bean.getSupplierCompany());
                supplierId = bean.getId();
            }
            if(requestCode==1002){
                QueryAllGoodsBean.DataBean bean = (QueryAllGoodsBean.DataBean) data.getSerializableExtra("bean");
                if(listData.toString().contains(bean.getName())){
                    UIHelper.t(_activity,"已添加该商品");
                    return;
                }
                listData.add(bean);
                if(adapter!=null) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }


    private void saveInsertPurchase() {
        if("".equals(supplierId)){
            UIHelper.t(this, "请选择供应商");
            return;
        }


        if ("".equals(tvPayWay.getText().toString().trim())) {
            UIHelper.t(this, "请选择支付方式");
            return;
        }
        if ("".equals(etLogisticsNum.getText().toString().trim())) {
            UIHelper.t(this, "请输入运费金额");
            return;
        }
        if ("".equals(tvLuggagePayWay.getText().toString().trim())) {
            UIHelper.t(this, "请选择运费支付方式");
            return;
        }
        if (logisticsCompany.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请输入物流公司");
            return;
        }
        if (logisticsCode.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请输入物流单号");
            return;
        }

        if (listData.size() == 0) {
            UIHelper.t(this, "请添加商品");
            return;
        }
        String ids = "";
        String repertoryId = "";
        String stock = "";
        String price = "";
        boolean flag = true;

        for (int i = 0; i < listData.size(); i++) {
            ids = ids + listData.get(i).getId() + ",";
            repertoryId = repertoryId + listData.get(i).getRepertoryId() + ",";
            stock = stock + (listData.get(i).getCount() == null ? "1" : listData.get(i).getCount())+",";
            price = price + (listData.get(i).getPrice() == null ? "0.00" : listData.get(i).getPrice())+",";
            if (listData.get(i).getRepertoryId() == null || listData.get(i).getRepertoryId().length() == 0) {
                flag = false;
            }
        }

        if (flag == false) {
            UIHelper.t(this, "请选择商品仓库");
            return;
        }


        AppUtil.getCarApiClient(ac).saveInsertPurchase(tvPayWay.getText().toString().trim(), etLogisticsNum.getText().toString().trim(), tvLuggagePayWay.getText().toString().trim(), etDes.getText().toString().trim(), repertoryId, supplierId,
                logisticsCompany.getText().toString().trim(), logisticsCode.getText().toString().trim(), stock, ids, price, this);
    }
}
