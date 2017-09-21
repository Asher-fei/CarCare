package com.lida.carcare.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarInShopBean;
import com.lida.carcare.bean.OnceCarByCustomerBean;
import com.lida.carcare.widget.DialogOnceCarByCustomer;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 结算页面
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivitySettleMent extends BaseActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvServiceItem)
    TextView tvServiceItem;
    @BindView(R.id.tvWorkers)
    TextView tvWorkers;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvPriceGoods)
    TextView tvPriceGoods;
    @BindView(R.id.tvCountPrice)
    TextView tvCountPrice;
    @BindView(R.id.rbCash)
    RadioButton rbCash;
    @BindView(R.id.tvAllPrice)
    TextView tvAllPrice;
    @BindView(R.id.tvSettleMent)
    Button tvSettleMent;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    private CarInShopBean.DataBean bean;

    @BindView(R.id.rbPrepaidPhoneCards)
    RadioButton rbPrepaidPhoneCards;
    @BindView(R.id.rbTimeCard)
    RadioButton rbTimeCard;
    @BindView(R.id.tvOnceCarByCustomer)
    EditText tvOnceCarByCustomer;

    int payMethod = 1;

    OnceCarByCustomerBean onceCarByCustomerBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        ButterKnife.bind(this);
        bean = (CarInShopBean.DataBean) mBundle.getSerializable("bean");
        LogUtils.e(bean);
        topbar.setTitle("结算页面");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvCarNo.setText(bean.getCarNo());
        tvCustomerName.setText(bean.getCustomerName());
        tvWorkers.setText(bean.getName());
        tvTime.setText(bean.getEntryTime());
        tvServiceItem.setText("服务项目："+bean.getGoodsProject());
        tvPriceGoods.setText("￥"+bean.getConsumptionAmount());
        tvAllPrice.setText(bean.getConsumptionAmount()==null?"":bean.getConsumptionAmount());
        tvCountPrice.setText("￥"+bean.getConsumptionAmount());
        StringBuilder temp = new StringBuilder();
        temp.append(bean.getGoodsProject()).append(bean.getMaintainProject()).append(bean.getRepairProject())
                .append(bean.getRefitProject());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.rbCash){
                    payMethod = 1;
                    tvAllPrice.setEnabled(true);
                }else if(checkedId==R.id.rbPrepaidPhoneCards){
                    payMethod=2;
                    tvAllPrice.setEnabled(true);
                }else if(checkedId==R.id.rbWeChat){
                    payMethod=3;
                    tvAllPrice.setEnabled(true);
                }else if(checkedId==R.id.rbZhiFuBao){
                    payMethod=4;
                    tvAllPrice.setEnabled(true);
                }else if(checkedId==R.id.rbTimeCard){
                    payMethod=5;
                    tvOnceCarByCustomer.setVisibility(View.VISIBLE);
                    tvAllPrice.setEnabled(false);
                    tvAllPrice.setText(bean.getConsumptionAmount()==null?"":bean.getConsumptionAmount());
                   /* if(onceCarByCustomerBean!=null){
                        new DialogOnceCarByCustomer(_activity,tvOnceCarByCustomer,onceCarByCustomerBean.getData()).show();
                    }*/
                }
            }
        });


        if(bean.getCustomerId()==null&&bean.getCustomerId().length()!=0){
            rbPrepaidPhoneCards.setEnabled(false);
            rbPrepaidPhoneCards.setEnabled(false);
        }else {
            AppUtil.getCarApiClient(ac).selectOnceCarByCustomerIdIdAndShopId(bean.getCustomerId(),this);
        }
    }

    @OnClick(R.id.tvSelectTimeCard)
    public void tvSelectTimeCard(){
        if(onceCarByCustomerBean!=null){
            new DialogOnceCarByCustomer(_activity,tvOnceCarByCustomer,onceCarByCustomerBean.getData()).show();
        }
    }


    @OnClick(R.id.tvSettleMent)
    public void onViewClicked() {
        String onceCardNo="";
        if(payMethod==5){
            if(tvOnceCarByCustomer.getText().toString().trim().length()==0){
                UIHelper.t(ac,"请选择次卡卡号");
                return;
            }
            onceCardNo = tvOnceCarByCustomer.getText().toString().trim();
        }
        if(tvAllPrice.getText().toString().trim().equals("")){
            UIHelper.t(ac,"请输入总金额");
            return;
        }
        AppUtil.getCarApiClient(ac).closeAccount(bean.getId(),"2",payMethod+"",onceCardNo,tvAllPrice.getText().toString().trim(),this);
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
            if("closeAccount".equals(tag)) {
                if("success".equals(res.getMsg())){
                    UIHelper.t(_activity,"操作成功");
                }else {
                    UIHelper.t(_activity, res.getMsg());
                    finish();
                }
            }
            else if("selectOnceCarByCustomerIdIdAndShopId".equals(tag)){
               onceCarByCustomerBean = (OnceCarByCustomerBean)res;
            }
        }else {
            UIHelper.t(_activity,res.getMsg());
        }
    }
}
