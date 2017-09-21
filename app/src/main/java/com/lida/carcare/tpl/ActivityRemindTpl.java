package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityNoticeDetail;
import com.lida.carcare.bean.RemindBean;
import com.lida.carcare.widget.DialogCall;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 当前关注
 * Created by WeiQingFeng on 2017/4/13.
 */

public class ActivityRemindTpl extends BaseTpl<RemindBean.DataBean> {

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

    public ActivityRemindTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRemindTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentcurrentnotice;
    }

    @Override
    public void setBean(final RemindBean.DataBean bean, int position) {
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
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getId());
                    UIHelper.jumpForResult(_activity, ActivityNoticeDetail.class,bundle,1002);
                }
            });

            tvMobilePhoneNo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = tvMobilePhoneNo.getText().toString().trim();
                    new DialogCall(_activity, phone).show();
                }
            });
        }
    }
}
