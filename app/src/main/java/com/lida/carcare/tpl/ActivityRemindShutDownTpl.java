package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityNoticeDetail;
import com.lida.carcare.bean.RemindShutDownBean;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 已关闭提醒
 * Created by Administrator on 2017/7/5.
 */

public class ActivityRemindShutDownTpl extends BaseTpl<RemindShutDownBean.DataBean> {

    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvDaysRemaining)
    TextView tvDaysRemaining;
    @BindView(R.id.tvMaturityDate)
    TextView tvMaturityDate;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvMobilePhoneNo)
    TextView tvMobilePhoneNo;
    @BindView(R.id.tvCarInfo)
    TextView tvCarInfo;

    public ActivityRemindShutDownTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRemindShutDownTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_remind_shut_down;
    }

    @Override
    public void setBean(RemindShutDownBean.DataBean bean, int position) {
        if (bean != null) {
            tvItemName.setText(bean.getReminderDetails());
            tvMaturityDate.setText(bean.getMaturityDate()+"到期");
            tvDaysRemaining.setText(bean.getDaysRemaining());
            tvCustomerName.setText(bean.getCustomerName());
            tvMobilePhoneNo.setText(bean.getMobilePhoneNo());
            if(bean.getBrandName()==null||"".equals(bean.getBrandName())){
                tvCarInfo.setText(bean.getCarNo());
            }else{
                tvCarInfo.setText(bean.getCarNo()+" "+bean.getBrandName());
            }

        }
    }
}
