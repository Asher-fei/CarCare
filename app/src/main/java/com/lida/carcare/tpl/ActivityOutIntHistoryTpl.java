package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityLookDetail;
import com.lida.carcare.activity.ActivityOutIntHistoryDetails;
import com.lida.carcare.bean.OutIntHistoryBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zdf on 2017/6/26.
 */

public class ActivityOutIntHistoryTpl extends BaseTpl<OutIntHistoryBean.DataBean> {
    @BindView(R.id.money_tv)
    TextView money_tv;
    @BindView(R.id.number_tv)
    TextView number_tv;
    @BindView(R.id.time_tv)
    TextView time_tv;
    @BindView(R.id.tvType)
    TextView tvType;

    public ActivityOutIntHistoryTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityOutIntHistoryTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_out_int_history;
    }

    @Override
    public void setBean(final OutIntHistoryBean.DataBean bean, int position) {
        if (bean != null) {
            final Bundle bundle = new Bundle();
            bundle.putBoolean("noUpdate",true);
            bundle.putString("id", bean.getId());
            if ("0".equals(bean.getWorkStatus())) {
                tvType.setText("采购入库");
                bundle.putString("flag","采购入库");
            }else  if ("1".equals(bean.getWorkStatus())) {
                tvType.setText("其他入库");
                bundle.putString("flag","其他入库");
            }else  if ("2".equals(bean.getWorkStatus())) {
                tvType.setText("其他出库");
                bundle.putString("flag","其他出库");
            }else  if ("3".equals(bean.getWorkStatus())) {
                tvType.setText("采购退货");
                bundle.putString("flag","采购退货");
            }
            bundle.putString("price",bean.getPurchasePrice());
            time_tv.setText(bean.getPurchaseDate());
            number_tv.setText("单号：" + bean.getCode());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    UIHelper.jump(_activity, ActivityOutIntHistoryDetails.class, bundle);
                    UIHelper.jumpForResult(_activity, ActivityLookDetail.class, bundle,1001);
                }
            });
        }
    }
}
