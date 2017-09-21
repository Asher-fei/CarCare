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
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CurrentStoreUserBean;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.widget.DialogCommen;
import com.lida.carcare.widget.DialogCurrentStoreUser;
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
 * 领料出库
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityReceiveGoods extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnAddGood)
    Button btnAddGood;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.tvChooseUser)
    TextView tvChooseUser;

    private Adapter adapter;
    private List<SelectOutboundGoodslistBean.DataBean> listData = new ArrayList<>();
    private CurrentStoreUserBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receivegoods);
        ButterKnife.bind(this);
        topbar.setTitle("领料出库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new Adapter();
        listView.setAdapter(adapter);
        if (bean == null) {
            AppUtil.getCarApiClient(ac).selectCurrentStoreUserData(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
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

    @OnClick({R.id.btnAddGood, R.id.tvChooseUser, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddGood:
                UIHelper.jumpForResult(_activity, ActivitySelectOutboundGoods.class, 1001);
                break;
            case R.id.tvChooseUser:
                new DialogCurrentStoreUser(_activity, tvChooseUser, bean.getData()).show();
                break;
            case R.id.btnConfirm:
                String userId = (String) tvChooseUser.getTag();
                String remark = etDes.getText().toString();
                StringBuilder sId = new StringBuilder();
                StringBuilder sNum = new StringBuilder();
                if("".equals(userId) || userId == null){
                    UIHelper.t(_activity,"请选择领料人");
                    return;
                }
                if(listData.size()==0){
                    UIHelper.t(_activity,"请添加商品");
                    return;
                }
                for (int i = 0; i < listData.size(); i++) {
                    sId.append(listData.get(i).getId()+",");
                    sNum.append(listData.get(i).getSelectCount()+",");
                }
                AppUtil.getCarApiClient(ac).updateCarPurchaseRecordBatch(userId,sId.toString().substring(0,sId.length()-1),
                        remark,sNum.toString().substring(0,sNum.length()-1),"","","",this);
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        if("updateCarPurchaseRecordBatch".equals(tag)){
            btnConfirm.setClickable(false);
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("selectCurrentStoreUserData".equals(tag)) {
                bean = (CurrentStoreUserBean) res;
            }
            if("updateCarPurchaseRecordBatch".equals(tag)){
                btnConfirm.setClickable(true);
                UIHelper.t(_activity,"领料出库成功！");
                finish();
            }
        }else{
            if("updateCarPurchaseRecordBatch".equals(tag)){
                btnConfirm.setClickable(true);
                UIHelper.t(_activity,res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        if("updateCarPurchaseRecordBatch".equals(tag)){
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








