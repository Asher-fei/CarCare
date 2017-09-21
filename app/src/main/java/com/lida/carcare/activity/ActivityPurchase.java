package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterPurchase;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.bean.SupplierBean;
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
 * 采购
 * Created by Administrator on 2017/6/20.
 */

public class ActivityPurchase extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSupplier)
    TextView tvSupplier;
    @BindView(R.id.etLogistics)
    EditText etLogistics;
    @BindView(R.id.etLogisticsNum)
    EditText etLogisticsNum;
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
    @BindView(R.id.btnOk)
    Button btnOk;

    private AdapterPurchase adapter;
    private List<QueryAllGoodsBean.DataBean> listData = new ArrayList<>();

    private String supplierId;//供应商id
    private String logisticsCompany;// 物流公司
    private String logisticsCode;// 物流单号
    private String remark;// 备注

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        ButterKnife.bind(this);
        topbar.setTitle("采购");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new AdapterPurchase(_activity, listData);
        listView.setAdapter(adapter);
    }

    @OnClick({R.id.btnAddGood, R.id.btnOk, R.id.tvSupplier})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAddGood:
                UIHelper.jumpForResult(_activity, ActivityQueryAllGoods.class, 1002);
                break;
            case R.id.btnOk:
                btnOk.setFocusable(true);
                btnOk.setFocusableInTouchMode(true);
                btnOk.requestFocus();
                logisticsCompany = etLogistics.getText().toString();
                logisticsCode = etLogisticsNum.getText().toString();
                remark = etDes.getText().toString();
                StringBuilder sbCount = new StringBuilder();
                StringBuilder sbIds = new StringBuilder();
                StringBuilder sbPrice = new StringBuilder();
                String count;
                String ids;
                String price;
                for (int i = 0; i < listData.size(); i++) {
                    sbCount.append(listData.get(i).getCount()+",");
                    sbIds.append(listData.get(i).getId()+",");
                    sbPrice.append(listData.get(i).getPrice()+",");
                }
                count = sbCount.toString();
                ids = sbIds.toString();
                price = sbPrice.toString();
                if("".equals(logisticsCompany)||"".equals(supplierId)||"".equals(logisticsCode)
                        ||"".equals(count)||"".equals(ids)||"".equals(price)){
                    UIHelper.t(_activity,"请填写完整采购信息");
                    return;
                }
                AppUtil.getCarApiClient(ac).savePurchase(supplierId,logisticsCompany,logisticsCode,remark,
                        count,ids,price,this);
                break;
            case R.id.tvSupplier:
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSelect", true);
                UIHelper.jumpForResult(_activity, ActivitySupplierManage.class, bundle, 1001);
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
        if(res.isOK()){
            UIHelper.t(_activity,"采购单提交成功!");
            finish();
        }else{
            ac.handleErrorCode(_activity,res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        ac.handleErrorCode(_activity,strMsg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                adapter.notifyDataSetChanged();
            }
        }
    }
}
