package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityEnterprisesDetailList;
import com.lida.carcare.bean.PerformanceBean;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 绩效管理详情
 * Created by Administrator on 2017/4/5.
 */

public class ActivityEnterprisesDetailTpl extends BaseTpl<PerformanceBean.Data.DocumentBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    public ActivityEnterprisesDetailTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityEnterprisesDetailTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityenterprisesdetail;
    }

    @Override
    public void setBean(final PerformanceBean.Data.DocumentBean bean, int position) {
        if(bean!=null){
            tvCarNo.setText(bean.getCarNo());
            tvPrice.setText("￥"+bean.getAmount());
            tvTime.setText(bean.getEntryTime());
            llItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("carNo",bean.getCarNo());
                    bundle.putString("money",bean.getAmount());
                    UIHelper.jump(_activity, ActivityEnterprisesDetailList.class, bundle);
                }
            });
        }
    }
}
