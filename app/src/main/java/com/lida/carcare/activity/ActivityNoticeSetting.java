package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarRemindSetBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提醒设置
 * Created by WeiQingFeng on 2017/4/14.
 */

public class ActivityNoticeSetting extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.cbMainTain)
    CheckBox cbMainTain;
    @BindView(R.id.llMainTainExpire)
    LinearLayout llMainTainExpire;
    @BindView(R.id.llMainTainNotice)
    LinearLayout llMainTainNotice;
    @BindView(R.id.cbInsurance)
    CheckBox cbInsurance;
    @BindView(R.id.llInsuranceNotice)
    LinearLayout llInsuranceNotice;
    @BindView(R.id.cbYearCheck)
    CheckBox cbYearCheck;
    @BindView(R.id.llYearCheckNotice)
    LinearLayout llYearCheckNotice;
    @BindView(R.id.cbCheckCar)
    CheckBox cbCheckCar;
    @BindView(R.id.llCheckCarExpire)
    LinearLayout llCheckCarExpire;
    @BindView(R.id.llCheckCarNotice)
    LinearLayout llCheckCarNotice;
    @BindView(R.id.llExpire)
    LinearLayout llExpire;
    @BindView(R.id.llNotice)
    LinearLayout llNotice;
    @BindView(R.id.tvMainTainExpire)
    TextView tvMainTainExpire;
    @BindView(R.id.tvMainTainNotice)
    TextView tvMainTainNotice;
    @BindView(R.id.tvInsuranceNotice)
    TextView tvInsuranceNotice;
    @BindView(R.id.tvYearCheckNotice)
    TextView tvYearCheckNotice;
    @BindView(R.id.tvCheckCarExpire)
    TextView tvCheckCarExpire;
    @BindView(R.id.tvCheckCarNotice)
    TextView tvCheckCarNotice;
    @BindView(R.id.tvExpire)
    TextView tvExpire;
    @BindView(R.id.tvNotice)
    TextView tvNotice;
    @BindView(R.id.btnReceive)
    Button btnReceive;


    int cbMainTainState = 0;
    int cbInsuranceState = 0;
    int cbYearCheckState = 0;
    int cbCheckCarState = 0;

    int iMainTainExpire = 1;
    int iMainTainNotice = 10;
    int iInsuranceNotice = 10;
    int iYearCheckNotice = 10;
    int iCheckCarExpire = 1;
    int iCheckCarNotice = 10;
    int illExpire = 1;
    int iNotice = 10;

    CarRemindSetBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticesetting);
        ButterKnife.bind(this);
        topbar.setTitle("提醒设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).selectCarRemindSet(this);
        initCheck();
    }

    @OnClick({R.id.llMainTainExpire, R.id.llMainTainNotice,
            R.id.llInsuranceNotice, R.id.llYearCheckNotice,
            R.id.llCheckCarExpire, R.id.llCheckCarNotice,
            R.id.llExpire, R.id.llNotice, R.id.btnReceive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llMainTainExpire:
                UIHelper.jumpForResult(_activity, ActivityDateExpire.class, 1001);
                break;
            case R.id.llMainTainNotice:
                UIHelper.jumpForResult(_activity, ActivityDateNotice.class, 1002);
                break;
            case R.id.llInsuranceNotice:
                UIHelper.jumpForResult(_activity, ActivityDateNotice.class, 1003);
                break;
            case R.id.llYearCheckNotice:
                UIHelper.jumpForResult(_activity, ActivityDateNotice.class, 1004);
                break;
            case R.id.llCheckCarExpire:
                UIHelper.jumpForResult(_activity, ActivityDateExpire.class, 1005);
                break;
            case R.id.llCheckCarNotice:
                UIHelper.jumpForResult(_activity, ActivityDateNotice.class, 1006);
                break;
            case R.id.llExpire:
                UIHelper.jumpForResult(_activity, ActivityDateExpire.class, 1007);
                break;
            case R.id.llNotice:
                UIHelper.jumpForResult(_activity, ActivityDateNotice.class, 1008);
                break;
            case R.id.btnReceive:
                saveSetting();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == RESULT_OK) {
                String timeData = data.getStringExtra("timeData") == null ? "" : data.getStringExtra("timeData");
                if (requestCode == 1001) {
                    tvMainTainExpire.setText(timeData);
                    iMainTainExpire = data.getIntExtra("time", 1);
                } else if (requestCode == 1002) {
                    tvMainTainNotice.setText(timeData);
                    iMainTainNotice = data.getIntExtra("time", 10);
                } else if (requestCode == 1003) {
                    tvInsuranceNotice.setText(timeData);
                    iInsuranceNotice = data.getIntExtra("time", 10);
                } else if (requestCode == 1004) {
                    tvYearCheckNotice.setText(timeData);
                    iYearCheckNotice = data.getIntExtra("time", 10);
                } else if (requestCode == 1005) {
                    tvCheckCarExpire.setText(timeData);
                    iCheckCarExpire = data.getIntExtra("time", 1);
                } else if (requestCode == 1006) {
                    tvCheckCarNotice.setText(timeData);
                    iCheckCarNotice = data.getIntExtra("time", 10);
                } else if (requestCode == 1007) {
                    tvExpire.setText(timeData);
                    illExpire = data.getIntExtra("time", 1);
                } else if (requestCode == 1008) {
                    tvNotice.setText(timeData);
                    iNotice = data.getIntExtra("time", 10);
                }
            }
        }
    }


    private void saveSetting() {
        Log.i("TAG","maintenanceState = "+cbMainTainState);
        Log.i("TAG","maintenanceDate  = "+iMainTainExpire);
        Log.i("TAG","maintenanceRemindDate  = "+iMainTainNotice);
        Log.i("TAG","insuranceState  = "+cbInsuranceState);
        Log.i("TAG","insuranceDate   = "+0);
        Log.i("TAG","insuranceRemindDate  = "+iInsuranceNotice);
        Log.i("TAG","annualState   = "+cbYearCheckState);
        Log.i("TAG","annualDate  = "+0);
        Log.i("TAG","annualRemindDate  = "+iYearCheckNotice);
        Log.i("TAG","inspectionState  = "+cbCheckCarState);
        Log.i("TAG","inspectionDate  = "+iCheckCarExpire);
        Log.i("TAG","inspectionRemindDate  = "+iCheckCarNotice);
        AppUtil.getCarApiClient(ac).savecarRemindSet(cbMainTainState,iMainTainExpire,iMainTainNotice,cbInsuranceState,0,iInsuranceNotice,cbYearCheckState,0,iYearCheckNotice,cbCheckCarState,iCheckCarExpire,iCheckCarNotice,this);
    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnReceive.setEnabled(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnReceive.setEnabled(true);
        if (res.isOK()) {
            if ("selectCarRemindSet".equals(tag)) {
                bean = (CarRemindSetBean) res;
                if (bean.getData() != null) {

                    cbMainTainState = String.valueOf(bean.getData().getMaintenanceState()) == null ? 0 : bean.getData().getMaintenanceState();
                    if (cbMainTainState == 0) {
                        cbMainTain.setChecked(false);
                    } else {
                        cbMainTain.setChecked(true);
                    }
                    iMainTainExpire = String.valueOf(bean.getData().getMaintenanceDate()) == null ? 1 : bean.getData().getMaintenanceDate();
                    tvMainTainExpire.setText("到期前" + iMainTainExpire + "个月");
                    iMainTainNotice = String.valueOf(bean.getData().getMaintenanceRemindDate()) == null ? 10 : bean.getData().getMaintenanceRemindDate();
                    tvMainTainNotice.setText("到期前" + iMainTainNotice + "天");


                    cbInsuranceState = String.valueOf(bean.getData().getInsuranceState()) == null ? 0 : bean.getData().getInsuranceState();
                    if (cbInsuranceState == 0) {
                        cbInsurance.setChecked(false);
                    } else {
                        cbInsurance.setChecked(true);
                    }
                    iInsuranceNotice = String.valueOf(bean.getData().getInsuranceRemindDate()) == null ? 10 : bean.getData().getInsuranceRemindDate();
                    tvInsuranceNotice.setText("到期前" + iInsuranceNotice + "天");


                    cbYearCheckState = String.valueOf(bean.getData().getAnnualState()) == null ? 0 : bean.getData().getAnnualState();
                    if (cbYearCheckState == 0) {
                        cbYearCheck.setChecked(false);
                    } else {
                        cbYearCheck.setChecked(true);
                    }
                    iYearCheckNotice = String.valueOf(bean.getData().getAnnualRemindDate()) == null ? 10 : bean.getData().getAnnualRemindDate();
                    tvYearCheckNotice.setText("到期前" + iYearCheckNotice + "天");

                    cbCheckCarState = String.valueOf(bean.getData().getInspectionState()) == null ? 0 : bean.getData().getInspectionState();
                    if (cbCheckCarState == 0) {
                        cbCheckCar.setChecked(false);
                    } else {
                        cbCheckCar.setChecked(true);
                    }
                    iCheckCarExpire = String.valueOf(bean.getData().getInspectionDate()) == null ? 1 : bean.getData().getInspectionDate();
                    tvCheckCarExpire.setText("到期前" + iCheckCarExpire + "个月");
                    iCheckCarNotice = String.valueOf(bean.getData().getInspectionRemindDate()) == null ? 10 : bean.getData().getInspectionRemindDate();
                    tvCheckCarNotice.setText("到期前" + iCheckCarNotice + "天");

                }
            } else if ("savecarRemindSet".equals(tag)) {
                UIHelper.t(_activity, "操作成功");
                setResult(RESULT_OK);
                finish();
            } else {
                if ("savecarRemindSet".equals(tag)) {
                    UIHelper.t(_activity, res.getMsg());
                }
            }
        }
    }


    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnReceive.setEnabled(true);
    }

    private void initCheck(){
        cbMainTain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    cbMainTainState=1;
                }else {
                    cbMainTainState = 0;
                }
            }
        });
        cbYearCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    cbYearCheckState=1;
                }else {
                    cbYearCheckState = 0;
                }
            }
        });
        cbInsurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    cbInsuranceState=1;
                }else {
                    cbInsuranceState = 0;
                }
            }
        });
        cbCheckCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    cbCheckCarState=1;
                }else {
                    cbCheckCarState = 0;
                }
            }
        });
    }
}
