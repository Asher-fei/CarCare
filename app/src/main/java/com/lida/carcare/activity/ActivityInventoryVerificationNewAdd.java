package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodClassABean;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.data.ActivityInventoryVerificationNewAddData;
import com.lida.carcare.data.ActivitySelectOutboundGoodsData;
import com.lida.carcare.tpl.ActivityInventoryVerificationNewAddTpl;
import com.lida.carcare.tpl.ActivitySelectOutboundGoodsTpl;
import com.lida.carcare.widget.DialogChooseStorage;
import com.lida.carcare.widget.DialogCommen;
import com.lida.carcare.widget.DialogSelectServer;
import com.lida.carcare.widget.DialogSelectShopClassification;
import com.lida.carcare.widget.NumberWidget;
import com.lida.carcare.widget.rightdialog.DialogClassA;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ActivityInventoryVerificationNewAdd extends BaseActivity implements DialogChooseStorage.onItemClickListener {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvVerificationQingdan)
    TextView tvVerificationQingdan;
    @BindView(R.id.listView)
    ListView listView;

    private ImageView title_iv;

    private List<GoodClassABean.DataBean> parent = new ArrayList<>();
    private Map<String, List<GoodClassABean.DataBean>> data = new HashMap<>();

    private StorageListBean bean;
    private DialogChooseStorage dialogChooseStorage;

    private TextView tvTitle;
    private ActivitySelectOutboundGoodsData dataSource;
    private String repertoryId = "";
    private String repertoryName = "";

    private Adapter adapter;
    private List<SelectOutboundGoodslistBean.DataBean> listData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_verification_new_add);
        ButterKnife.bind(this);
        tvTitle = (TextView) findViewById(R.id.title_tv);
        title_iv = (ImageView)findViewById(R.id.title_iv);
        title_iv.setImageResource(R.drawable.icon_arrow_down);
        topbar.setTitle("选择仓库");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new Adapter();
        listView.setAdapter(adapter);
        AppUtil.getCarApiClient(ac).getRepertoryList(this);
        // AppUtil.getCarApiClient(ac).getProductCategory(ac.shopId, this);//获取一级分类
    }

    @OnClick({R.id.tvVerificationQingdan, R.id.tvScreen, R.id.title_tv, R.id.btnOK})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tvVerificationQingdan:

                break;
            case R.id.tvScreen:
               /* if(parent.size()>0){
                    new DialogSelectShopClassification(_activity,parent,data).show();
                }*/
               Bundle bundle = new Bundle();
                bundle.putString("repertoryId",repertoryId);
                bundle.putString("repertoryName",repertoryName);
                UIHelper.jumpForResult(_activity, ActivityScreenInventoryShop.class,bundle, 1001);
                break;
            case R.id.title_tv:
                if (bean == null) {
                    AppUtil.getCarApiClient(ac).getRepertoryList(this);
                    return;
                }
                dialogChooseStorage = new DialogChooseStorage(_activity, tvTitle, bean.getData());
                dialogChooseStorage.setOnItemClickListener(this);
                dialogChooseStorage.show();
                break;
            case R.id.btnOK:
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
            if ("getProductCategory".equals(tag)) {
                GoodClassABean bean = (GoodClassABean) res;
                if (bean.getData() != null) {
                    parent.clear();
                    parent.addAll(bean.getData());
                }
            }

            if ("getRepertoryList".equals(tag)) {
                bean = (StorageListBean) res;
                dialogChooseStorage = new DialogChooseStorage(_activity, tvTitle, bean.getData());
                dialogChooseStorage.setOnItemClickListener(this);
                if (bean != null) {
                    if (bean.getData().size() > 0) {
                        tvTitle.setText(bean.getData().get(0).getRepertoryName());
                        tvTitle.setTag(bean.getData().get(0).getId());
                        repertoryId = bean.getData().get(0).getId();
                        repertoryName = bean.getData().get(0).getRepertoryName();
                    }
                }
            }
            if ("saveShout".equals(tag)) {
                UIHelper.t(ac, "操作成功");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        } else {
            if ("saveShout".equals(tag)) {
                UIHelper.t(ac, res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }


    @Override
    public void onItemClicked(int position) {
        repertoryId = bean.getData().get(position).getId();
        repertoryName = bean.getData().get(position).getRepertoryName();
    }

    private void save() {

        if (listData.size() == 0) {
            UIHelper.t(_activity, "请添加商品");
            return;
        }
        String goodsid = "";
        String repertoryId = "";
        String shoutStock = "";
        String stock = "";
        for (int i = 0; i < listData.size(); i++) {
            goodsid = goodsid + listData.get(i).getProductId() + ",";
            repertoryId = repertoryId + listData.get(i).getRepertoryId() + ",";
            shoutStock = shoutStock + (listData.get(i).getSelectCount() == null ? "1" : listData.get(i).getSelectCount()) + ",";
            stock = stock + listData.get(i).getStock() + ",";

        }
        AppUtil.getCarApiClient(ac).saveShout(goodsid, repertoryId, shoutStock, stock, this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                LogUtils.e(data);
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
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_screen_inventory_shop, null);
                viewHolder = new Adapter.ViewHolder(convertView);
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
            viewHolder.tvSelectCount.setText("盘点" + listData.get(position).getSelectCount() + "件");
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
