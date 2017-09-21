package com.lida.carcare.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SelectPurchaseDetailListBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogChoosePayWay;
import com.lida.carcare.widget.DialogChooseStorage;
import com.lida.carcare.widget.InnerListView;
import com.makeramen.roundedimageview.RoundedImageView;
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
 * 入库
 * Created by Administrator on 2017/6/28.
 */

public class ActivityPutInStorage extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.innerListView)
    InnerListView innerListView;
    @BindView(R.id.payType)
    TextView payType;
    @BindView(R.id.priceType)
    TextView priceType;
    @BindView(R.id.payPrice)
    EditText payPrice;
    @BindView(R.id.etDes)
    EditText etDes;

    private StorageListBean bean;
    private List<SelectPurchaseDetailListBean.DataBean> pData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_in_storage);
        ButterKnife.bind(this);
        topbar.setTitle("入库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).getRepertoryList(this);
        AppUtil.getCarApiClient(ac).selectPurchaseDetailList(mBundle.getString("ids"), this);
    }


    @OnClick({R.id.payType, R.id.priceType, R.id.btnOk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payType:
                new DialogChoosePayWay(_activity, payType).show();
                break;
            case R.id.priceType:
                new DialogChoosePayWay(_activity, priceType).show();
                break;
            case R.id.btnOk:
                savePurchaseStatus();
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
            if ("selectPurchaseDetailList".equals(tag)) {
                SelectPurchaseDetailListBean bean = (SelectPurchaseDetailListBean) res;
                pData = bean.getData();
                PAdapter pAdapter = new PAdapter();
                innerListView.setAdapter(pAdapter);
            }
            if("getRepertoryList".equals(tag)){
                bean = (StorageListBean) res;
            }
            if("savePurchaseStatus".equals(tag)){
                UIHelper.t(ActivityPutInStorage.this, "操作成功");
                setResult(RESULT_OK);
                finish();
            }
        }else {
            if("savePurchaseStatus".equals(tag)){
                UIHelper.t(ActivityPutInStorage.this, res.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    private void savePurchaseStatus() {
        if ("".equals(payType.getText().toString().trim())) {
            UIHelper.t(this, "请选择支付方式");
            return;
        }
        if ("".equals(payPrice.getText().toString().trim())) {
            UIHelper.t(this, "请输入运费");
            return;
        }
        if ("".equals(priceType.getText().toString().trim())) {
            UIHelper.t(this, "请选择运费支付方式");
            return;
        }

        List<String> temp1 = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();
        for (int i = 0; i < pData.size(); i++) {
            for (int j = 0; j < pData.get(i).getJfomGoods().size(); j++) {
                temp2.add(pData.get(i).getJfomGoods().get(j).getName());
                if(!"".equals(pData.get(i).getJfomGoods().get(j).getStorageId()) && pData.get(i).getJfomGoods().get(j).getStorageId()!=null){
                    temp1.add(pData.get(i).getJfomGoods().get(j).getStorageId());
                }
            }
        }
        LogUtils.e("temp1:"+temp1);
        LogUtils.e("temp2:"+temp2);
        if(temp1.size()<temp2.size()){
            UIHelper.t(_activity,"请为商品选择入库库位");
            return;
        }

        StringBuilder sIds = new StringBuilder();
        StringBuilder sRepertoryId = new StringBuilder();
        for (int i = 0; i < pData.size(); i++) {
            for (int j = 0; j < pData.get(i).getJfomGoods().size(); j++) {
                sIds.append(pData.get(i).getCarPurchaseRecords().get(j).getId()+",");
                sRepertoryId.append(pData.get(i).getJfomGoods().get(j).getStorageId()+",");
            }
        }
        AppUtil.getCarApiClient(ac).savePurchaseStatus(sIds.toString().substring(0,sIds.length()-1), payType.getText().toString().trim(),
                payPrice.getText().toString().trim(), priceType.getText().toString().trim(),
                etDes.getText().toString().trim(), sRepertoryId.toString().substring(0,sRepertoryId.length()-1), this);
    }

    public class PAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pData == null ? 0 : pData.size();
        }

        @Override
        public Object getItem(int position) {
            return pData == null ? 0 : position;
        }

        @Override
        public long getItemId(int position) {
            return pData == null ? 0 : position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_put_in_storage, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvSupplier.setText(pData.get(position).getSupplierCompany());
            viewHolder.cListView.setAdapter(new CAdapter(pData.get(position)));
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tvSupplier)
            TextView tvSupplier;
            @BindView(R.id.cListView)
            InnerListView cListView;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public class CAdapter extends BaseAdapter {

        private SelectPurchaseDetailListBean.DataBean cData;

        public CAdapter(SelectPurchaseDetailListBean.DataBean cData) {
            this.cData = cData;
        }

        @Override
        public int getCount() {
            return cData.getJfomGoods() == null ? 0 : cData.getJfomGoods().size();
        }

        @Override
        public Object getItem(int position) {
            return cData.getJfomGoods() == null ? 0 : position;
        }

        @Override
        public long getItemId(int position) {
            return cData == null ? 0 : position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if(convertView==null){
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_put_in_storage_product, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ac.setImage(viewHolder.ivPic, Constant.BASE+cData.getJfomGoods().get(position).getProductImg());
            viewHolder.tvProductName.setText(cData.getJfomGoods().get(position).getName());
            viewHolder.tvProductCode.setText("编码："+cData.getJfomGoods().get(position).getInternationalCode());
            viewHolder.tvPrice.setText("¥"+cData.getJfomGoods().get(position).getPriceStandardBid());
            viewHolder.tvCount.setText("X"+cData.getCarPurchaseRecords().get(position).getStock());
            viewHolder.tvStorage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogChooseStorage dialogChooseStorage = new DialogChooseStorage(_activity, viewHolder.tvStorage, bean.getData());
                    dialogChooseStorage.setOnItemClickListener(new DialogChooseStorage.onItemClickListener() {
                        @Override
                        public void onItemClicked(int p) {
                            cData.getJfomGoods().get(position).setStorageId(bean.getData().get(p).getId());
                        }
                    });
                    dialogChooseStorage.show();
                }
            });
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.ivPic)
            RoundedImageView ivPic;
            @BindView(R.id.tvProductName)
            TextView tvProductName;
            @BindView(R.id.tvProductCode)
            TextView tvProductCode;
            @BindView(R.id.tvPrice)
            TextView tvPrice;
            @BindView(R.id.tvCount)
            TextView tvCount;
            @BindView(R.id.tvStorage)
            TextView tvStorage;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
