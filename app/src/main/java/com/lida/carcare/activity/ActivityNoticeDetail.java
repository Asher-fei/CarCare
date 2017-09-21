package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.RemindDetailBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogCommen;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提醒详情
 * Created by WeiQingFeng on 2017/4/14.
 */

public class ActivityNoticeDetail extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvHistory)
    TextView tvHistory;
    @BindView(R.id.btnCloseNotice)
    Button btnCloseNotice;
    @BindView(R.id.tvCarType)
    TextView tvCarType;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvMaturityDate)
    TextView tvMaturityDate;
    @BindView(R.id.tvDaysRemaining)
    TextView tvDaysRemaining;
    @BindView(R.id.tvNoticeTime)
    TextView tvNoticeTime;

    private RemindDetailBean bean;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticedetail);
        ButterKnife.bind(this);
        topbar.setTitle("提醒详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        id = mBundle.getString("id");
        AppUtil.getCarApiClient(ac).selectCarRemindlistInfo(id, this);
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
            bean = (RemindDetailBean) res;
            if(bean.getData().getBrandName()==null||"".equals(bean.getData().getBrandName())){
                tvCarType.setText(bean.getData().getCarNo());
            }else{
                tvCarType.setText(bean.getData().getCarNo()+" "+bean.getData().getBrandName());
            }
            tvCustomerName.setText(bean.getData().getCustomerName());
            tvPhone.setText(bean.getData().getMobilePhoneNo()==null?"":bean.getData().getMobilePhoneNo());
            tvItemName.setText(bean.getData().getReminderDetails());
            tvMaturityDate.setText(bean.getData().getMaturityDate()+"到期");
            tvDaysRemaining.setText(bean.getData().getDaysRemaining());
            tvHistory.setText("历史进店"+bean.getData().getCount()+"次");
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @OnClick({R.id.tvHistory, R.id.btnCloseNotice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvHistory:
//                Bundle bundle = new Bundle();
//                bundle.putString("carNo",bean.getData().getCarNo());
//                bundle.putString("carNo",bean.getData().getCarNo());
//                UIHelper.jump(_activity, ActivityCarDetail.class);
                break;
            case R.id.btnCloseNotice:
                DialogCommen dialogCommen = new DialogCommen(_activity,"确定关闭提醒？");
                dialogCommen.setOnOkButtonClick(new DialogCommen.onOkButtonClick() {
                    @Override
                    public void delete() {
                        AppUtil.getCarApiClient(ac).updateIntelligentReminder(id, "close", new BaseApiCallback(){
                            @Override
                            public void onApiStart(String tag) {
                                super.onApiStart(tag);
                                showLoadingDlg();
                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                hideLoadingDlg();
                                UIHelper.t(_activity, "已关闭该提醒");
                                setResult(RESULT_OK);
                                finish();
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                super.onApiFailure(t, errorNo, strMsg, tag);
                                hideLoadingDlg();
                            }
                        });
                    }
                });
                dialogCommen.show();
                break;
        }
    }
}
