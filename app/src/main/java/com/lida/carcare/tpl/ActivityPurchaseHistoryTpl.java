package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityLookDetail;
import com.lida.carcare.bean.PurchaseHistoryBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 采购记录
 * Created by Administrator on 2017/6/21.
 */

public class ActivityPurchaseHistoryTpl extends BaseTpl<PurchaseHistoryBean.DataBean> {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvLogisticsCode)
    TextView tvLogisticsCode;
    @BindView(R.id.tvLogisticsCompany)
    TextView tvLogisticsCompany;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    public ActivityPurchaseHistoryTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityPurchaseHistoryTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitypurchasehistory;
    }

    @Override
    public void setBean(final PurchaseHistoryBean.DataBean bean, int position) {
        if (bean != null) {
            tvName.setText(bean.getScompany());
            tvTime.setText(bean.getPurchaseDate());
            tvPrice.setText("￥"+bean.getPurchasePrice());
            tvLogisticsCode.setText("物流单号：" + bean.getLogisticsCode());
            tvLogisticsCompany.setText("物流公司：" + bean.getLogisticsCompany());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", bean.getId());
                    bundle.putString("flag", "采购详情");
                    bundle.putString("price",bean.getPurchasePrice());
                    UIHelper.jumpForResult(_activity, ActivityLookDetail.class, bundle,1001);
                }
            });
        }
    }
}
