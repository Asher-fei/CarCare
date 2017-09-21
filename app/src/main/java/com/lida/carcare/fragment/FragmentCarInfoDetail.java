package com.lida.carcare.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.AdapterNotice;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCarDetail;
import com.lida.carcare.activity.ActivityChooseCustomer;
import com.lida.carcare.activity.ActivityCreateNotice;
import com.lida.carcare.adapter.AdapterCheckQuestion;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarInfoBean;
import com.lida.carcare.widget.DialogCall;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 车辆信息
 * Created by WeiQingFeng on 2017/4/17.
 */

public class FragmentCarInfoDetail extends BaseFragment {

    @BindView(R.id.tvHistory)
    TextView tvHistory;
    @BindView(R.id.listCheck)
    InnerListView listCheck;
    @BindView(R.id.listNotice)
    InnerListView listNotice;
    @BindView(R.id.btnHaveCar)
    Button btnHaveCar;
    Unbinder unbinder;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.tvCarType)
    TextView tvCarType;
    @BindView(R.id.tvInTime)
    TextView tvInTime;
    @BindView(R.id.tvMile)
    TextView tvMile;
    @BindView(R.id.tvOil)
    TextView tvOil;
    @BindView(R.id.tvIsWait)
    TextView tvIsWait;
    @BindView(R.id.tvDeliveryTime)
    TextView tvDeliveryTime;
    @BindView(R.id.tvRemindCount)
    TextView tvRemindCount;
    @BindView(R.id.tvConclusion)
    TextView tvConclusion;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;
    @BindView(R.id.btnChooseCustomer)
    Button btnChooseCustomer;
    @BindView(R.id.remark)
    TextView remark;

    private String id;//;documentid
    public String carId;//
    public String WorkStatus = "";
    private List<CarInfoBean.DataBean.CarInspectBean> carInspect;//检车问题
    private List<CarInfoBean.DataBean.CarRemindBean> carRemind;//提醒事项


    private String conclusion="";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        id = getArguments().getString("id");
        AppUtil.getCarApiClient(ac).selectModifyVehicleServiceDetails(id, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_carinfo, null);
        unbinder = ButterKnife.bind(this, v);
        Log.i("TAG","WorkStatus = "+WorkStatus);
        WorkStatus = getArguments().getString("WorkStatus");
        if(WorkStatus.equals("2")){
            btnChooseCustomer.setVisibility(View.GONE);
        }
        btnHaveCar.setEnabled(true);
        listCheck.setDivider(null);
        return v;
    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            if("selectModifyVehicleServiceDetails".equals(tag)) {
                CarInfoBean bean = (CarInfoBean) res;
                tvCarNo.setText(bean.getData().getCarNo());
                tvCustomerName.setText(bean.getData().getCustomerName());
                tvPhone.setText(bean.getData().getMobilePhoneNo());
                tvCarType.setText(bean.getData().getBrandName());
                tvInTime.setText(bean.getData().getEntryTime() + "进店");
                tvMile.setText("进店里程 " + bean.getData().getMileage()==null?"":bean.getData().getMileage());
                tvOil.setText("进店油表 " + bean.getData().getOiltable());
                tvDeliveryTime.setText("预计交车时间：" + bean.getData().getDeliveryTime());
                carId = bean.getData().getCarId();
                remark.setText("备注："+(bean.getData().getRemark()==null?"":bean.getData().getRemark()));
                if ("1".equals(bean.getData().getIsWait())) {
                    tvIsWait.setText("在店等 是");
                } else {
                    tvIsWait.setText("在店等 否");
                }
                tvHistory.setText("历史进店" + bean.getData().getCount() + "次");
                if (bean.getData().getConclusion() == null) {
                    tvConclusion.setText("检测结论：未填写");
                } else {
                    tvConclusion.setText("检测结论：" + bean.getData().getConclusion());
                    conclusion = bean.getData().getConclusion();
                }
                carInspect = bean.getData().getCarInspect();
                carRemind = bean.getData().getCarRemind();
                tvAllMoney.setText("￥" + bean.getData().getConsumptionAmount());
                if (carInspect != null) {
                    listCheck.setAdapter(new AdapterCheckQuestion(_activity, carInspect));
                }
                if (carRemind != null) {
                    listNotice.setAdapter(new AdapterNotice(_activity, carRemind));
                    tvRemindCount.setText(carRemind.size() + "");
                } else {
                    tvRemindCount.setText("0");
                }
            }else if("updateDocumentCustomer".equals(tag)){
                UIHelper.t(ac,"操作成功");
                AppUtil.getCarApiClient(ac).selectModifyVehicleServiceDetails(id, this);
            }
        }else {
            if("updateDocumentCustomer".equals(tag)){
                UIHelper.t(ac,res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        _activity.hideLoadingDlg();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //unbinder.unbind();
    }

    @OnClick({R.id.tvHistory, R.id.btnHaveCar, R.id.btnCreateNotice,R.id.btnChooseCustomer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvHistory:
                Bundle bundle = new Bundle();
                bundle.putString("documentId",id);
                bundle.putString("carId",carId);
                Log.i("TAG","cardId === "+carId);
                bundle.putString("carNo",tvCarNo.getText().toString());
                UIHelper.jump(_activity, ActivityCarDetail.class, bundle);
                break;
            case R.id.btnHaveCar:
                UIHelper.t(_activity, "Onclick");
                break;
            case R.id.btnCreateNotice:
                Intent intent = new Intent();
                intent.setClass(_activity, ActivityCreateNotice.class);
                this.startActivityForResult(intent, 1001);
                break;
            case R.id.btnChooseCustomer:
                Bundle bundle1 = new Bundle();
                bundle1.putString("flag","FragmentCarInfoDetail");
                UIHelper.jumpForResult(_activity, ActivityChooseCustomer.class, bundle1, 1002);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            AppUtil.getCarApiClient(ac).selectModifyVehicleServiceDetails(id, this);
        }
        if(requestCode==1002&&resultCode==Activity.RESULT_OK){
            String name = data.getStringExtra("name");
            String phone = data.getStringExtra("phone");
            String userId = data.getStringExtra("userId");
            String sex = data.getStringExtra("sex");
           AppUtil.getCarApiClient(ac).updateDocumentCustomer(id,userId,carId,this);
        }
    }

    @OnClick(R.id.tvPhone)
    public void callPhone(){
        if(!tvPhone.getText().toString().equals("")){
            new DialogCall(_activity,tvPhone.getText().toString()).show();
        }
    }


    public String getConclusion(){
        return conclusion;
    }
}
