package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.lida.carcare.adapter.AdapterOtherWarehouse;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.widget.DialogChoosePayWay;
import com.lida.carcare.widget.DialogCommen;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.api.ApiCallback;
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
 *
 * 其它出库
 * Created by Administrator on 2017/7/7.
 */

public class ActivityOtherOutBound extends BaseActivity {

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

    private String supplierId="";


    private Adapter adapter;
    private List<SelectOutboundGoodslistBean.DataBean> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_outbound);
        ButterKnife.bind(this);
        topbar.setTitle("其它出库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new Adapter();
        listView.setAdapter(adapter);
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

                UIHelper.jumpForResult(_activity, ActivitySelectOutboundGoods.class, 1002);
                break;
            case R.id.btnIn:
                LogUtils.e(listData);
                break;
            case R.id.btnOut:
                btnIn.setFocusable(true);
                btnIn.setFocusableInTouchMode(true);
                btnIn.requestFocus();
                save();
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

            if("updatePurchase".equals(tag)){
                UIHelper.t(_activity,"操作成功");
                finish();
            }
        }else {
            if("updatePurchase".equals(tag)){
                UIHelper.t(_activity,res.getMsg());
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
        if (resultCode == RESULT_OK) {
            if(requestCode==1001){
                SupplierBean.DataBean bean = (SupplierBean.DataBean) data.getSerializableExtra("bean");
                tvSupplier.setText(bean.getSupplierCompany());
                supplierId = bean.getId();
            }

            else if (requestCode == 1002) {
                LogUtils.e(data);
                ArrayList<SelectOutboundGoodslistBean.DataBean> bean =  data.getParcelableArrayListExtra("data");
                for (int i = 0; i < bean.size(); i++) {
                    if (!listData.toString().contains(bean.get(i).getProductName())) {
                        listData.add(bean.get(i));
                    }
                }
                for (int i = 0; i < listData.size(); i++) {
                    if(listData.get(i).getSelectCount()==null){
                        listData.get(i).setSelectCount("1");
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    }


    private void save(){

        if("".equals(supplierId)){
            UIHelper.t(this, "请选择供应商");
            return;
        }

        if ("".equals(tvPayWay.getText().toString().trim())) {
            UIHelper.t(this, "请选择支付方式");
            return;
        }

        if (listData.size() == 0) {
            UIHelper.t(this, "请添加商品");
            return;
        }
        StringBuilder sId = new StringBuilder();
        StringBuilder sNum = new StringBuilder();
        for (int i = 0; i < listData.size(); i++) {
            sId.append(listData.get(i).getId()+",");
            sNum.append(listData.get(i).getSelectCount()+",");
        }

        AppUtil.getCarApiClient(ac).updatePurchase(sId.toString(),etDes.getText().toString(),sNum.toString(),supplierId,tvPayWay.getText().toString(),this);


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
            ViewHolder viewHolder;
            if(convertView==null){
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_activityselecyoutboundgoods, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ac.setImage(viewHolder.iv, Constant.BASE+listData.get(position).getProductImg());
            viewHolder.tvName.setText(listData.get(position).getProductName());
            viewHolder.tvCode.setText("编码："+listData.get(position).getProductInternationalCode());
            viewHolder.tvWarehourse.setText("库位："+listData.get(position).getRepertoryName());
            viewHolder.tvCount.setText("库存："+listData.get(position).getStock());
            viewHolder.etCount.setVisibility(View.GONE);
            viewHolder.tvSelectCount.setVisibility(View.VISIBLE);
            viewHolder.cb.setVisibility(View.GONE);
            viewHolder.tvSelectCount.setText("共"+listData.get(position).getSelectCount()+"件");
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

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
