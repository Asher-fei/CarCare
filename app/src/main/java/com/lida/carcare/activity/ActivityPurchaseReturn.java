package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CurrentStoreUserBean;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.widget.DialogChoosePayWay;
import com.lida.carcare.widget.DialogChooseStorage;
import com.lida.carcare.widget.DialogCommen;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.Constant;
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
 * 采购退货
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityPurchaseReturn extends BaseActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSupplier)
    TextView tvSupplier;
    @BindView(R.id.tvPayType)
    TextView tvPayType;
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
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    private Adapter adapter;
    private List<SelectOutboundGoodslistBean.DataBean> listData = new ArrayList<>();

    private StorageListBean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_return);
        ButterKnife.bind(this);
        topbar.setTitle("采购退货");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new Adapter();
        listView.setAdapter(adapter);
        AppUtil.getCarApiClient(ac).getRepertoryList(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                ArrayList<SelectOutboundGoodslistBean.DataBean> bean = data.getParcelableArrayListExtra("data");
                for (int i = 0; i < bean.size(); i++) {
                    if (!listData.toString().contains(bean.get(i).getProductName())) {
                        listData.add(bean.get(i));
                    }
                }
                for (int i = 0; i < listData.size(); i++) {
                    if (listData.get(i).getSelectCount() == null) {
                        listData.get(i).setSelectCount("1");
                    }
                }
                adapter.notifyDataSetChanged();
            }
            if(requestCode == 1002){
                SupplierBean.DataBean bean = (SupplierBean.DataBean) data.getSerializableExtra("bean");
                tvSupplier.setText(bean.getSupplierCompany());
                tvSupplier.setTag(bean.getId());
            }
        }
    }

    @OnClick({R.id.btnAddGood, R.id.btnConfirm, R.id.tvSupplier, R.id.tvPayType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddGood:
                UIHelper.jumpForResult(_activity, ActivitySelectOutboundGoods.class, 1001);
                break;
            case R.id.btnConfirm:
                btnConfirm.setFocusable(true);
                btnConfirm.setFocusableInTouchMode(true);
                btnConfirm.requestFocus();
                String remark = etDes.getText().toString();
                String supplierId = (String) tvSupplier.getTag();
                String payType = tvPayType.getText().toString();
                StringBuilder sId = new StringBuilder();
                StringBuilder sNum = new StringBuilder();
                StringBuffer sReturnPrice = new StringBuffer();
                if("".equals(supplierId)||supplierId==null){
                    UIHelper.t(_activity, "请选择供应商");
                    return;
                }
                if("".equals(payType)||payType==null){
                    UIHelper.t(_activity, "请选择支付方式");
                    return;
                }
                if (listData.size() == 0) {
                    UIHelper.t(_activity, "请添加商品");
                    return;
                }
                for (int i = 0; i < listData.size(); i++) {
                    sId.append(listData.get(i).getId() + ",");
                    sNum.append(listData.get(i).getSelectCount() + ",");
                    if(listData.get(i).getReturnPrice()!=null) {
                        sReturnPrice.append(listData.get(i).getReturnPrice() + ",");
                    }else {
                        sReturnPrice.append("0.0" + ",");
                    }
                }
                AppUtil.getCarApiClient(ac).updateCarPurchaseRecordBatch("", sId.toString().substring(0, sId.length() - 1),
                        remark, sNum.toString().substring(0, sNum.length() - 1),supplierId,payType,sReturnPrice.toString().substring(0,sReturnPrice.length()-1),this);
                break;
            case R.id.tvSupplier:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSelect", true);

                UIHelper.jumpForResult(_activity, ActivitySupplierManage.class, bundle, 1002);
                break;
            case R.id.tvPayType:
                /*if(bean!=null) {
                    DialogChooseStorage dialogChooseStorage = new DialogChooseStorage(_activity, tvPayType, bean.getData());
                    dialogChooseStorage.show();
                }*/
                new DialogChoosePayWay(_activity, tvPayType).show();
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        if ("updateCarPurchaseRecordBatch".equals(tag)) {
            btnConfirm.setClickable(false);
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("getRepertoryList".equals(tag)) {
                bean = (StorageListBean) res;
            }
            if ("updateCarPurchaseRecordBatch".equals(tag)) {
                btnConfirm.setClickable(true);
                UIHelper.t(_activity, "采购退货成功！");
                finish();
            }
        }else {
            if ("updateCarPurchaseRecordBatch".equals(tag)) {
                btnConfirm.setClickable(true);
                UIHelper.t(_activity, res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        if ("updateCarPurchaseRecordBatch".equals(tag)) {
            btnConfirm.setClickable(true);
        }
    }

    public class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listData == null ? 0 : listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData == null ? 0 : position;
        }

        @Override
        public long getItemId(int position) {
            return listData == null ? 0 : position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_purchase_return, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ac.setImage(viewHolder.iv, Constant.BASE + listData.get(position).getProductImg());
            viewHolder.tvName.setText(listData.get(position).getProductName());
            viewHolder.tvCode.setText("编码：" + listData.get(position).getProductInternationalCode());
            viewHolder.tvWarehourse.setText("库位：" + listData.get(position).getRepertoryName());
            viewHolder.tvCount.setText("库存：" + listData.get(position).getStock());
            viewHolder.etCount.setVisibility(View.GONE);
            viewHolder.tvSelectCount.setVisibility(View.VISIBLE);
            viewHolder.cb.setVisibility(View.GONE);
            viewHolder.tvSelectCount.setText("共" + listData.get(position).getSelectCount() + "件");
            viewHolder.ivDel.setVisibility(View.VISIBLE);
            viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogCommen dialogCommen = new DialogCommen(_activity, "确定删除？");
                    dialogCommen.setOnOkButtonClick(new DialogCommen.onOkButtonClick() {
                        @Override
                        public void delete() {
                            dialogCommen.dismiss();
                            listData.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                    dialogCommen.show();
                }
            });

            viewHolder.etReturnPrice.setTag(listData.get(position).getId());
            viewHolder.etReturnPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        listData.get(position).setReturnPrice(viewHolder.etReturnPrice.getText().toString().trim());
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.cb)
            CheckBox cb;
            @BindView(R.id.iv)
            ImageView iv;
            @BindView(R.id.ivDel)
            ImageView ivDel;
            @BindView(R.id.tvName)
            TextView tvName;
            @BindView(R.id.tvCode)
            TextView tvCode;
            @BindView(R.id.tvSelectCount)
            TextView tvSelectCount;
            @BindView(R.id.tvWarehourse)
            TextView tvWarehourse;
            @BindView(R.id.tvCount)
            TextView tvCount;
            @BindView(R.id.etCount)
            NumberWidget etCount;
            @BindView(R.id.llPrice)
            LinearLayout llPrice;
            @BindView(R.id.llItem)
            LinearLayout llItem;
            @BindView(R.id.etReturnPrice)
                    EditText etReturnPrice;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}








