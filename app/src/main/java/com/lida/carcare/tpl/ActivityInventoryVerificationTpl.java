package com.lida.carcare.tpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityInventoryVerificationDetails;
import com.lida.carcare.activity.ActivityOutIntHistoryDetails;
import com.lida.carcare.bean.InventoryVerificationBean;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存盘点
 * Created by zdf on 2017/6/28.
 */

public class ActivityInventoryVerificationTpl extends BaseTpl<InventoryVerificationBean.DataBean> {
    @BindView(R.id.tvInventoryNum)
    TextView tvInventoryNum;
    @BindView(R.id.tvInventoryPeople)
    TextView tvInventoryPeople;
    @BindView(R.id.tvInventoryTime)
    TextView tvInventoryTime;
    @BindView(R.id.tvInventoryType)
    TextView tvInventoryType;
    @BindView(R.id.tvInventoryProfit)
    TextView tvInventoryProfit;
    @BindView(R.id.tvInventoryLoss)
    TextView tvInventoryLoss;

    public ActivityInventoryVerificationTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityInventoryVerificationTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_inventory_verification;
    }

    @Override
    public void setBean(final InventoryVerificationBean.DataBean bean, int position) {
        if (bean != null) {
            tvInventoryNum.setText(bean.getShoutId());
            tvInventoryPeople.setText(bean.getShoutPeason());
            tvInventoryTime.setText(bean.getShoutDate());
            tvInventoryType.setText("盘点："+bean.getAcount()+"种");
            tvInventoryProfit.setText("盘盈："+(bean.getShoutOk()==null?"0":bean.getShoutOk()));
            tvInventoryLoss.setText("盘亏："+(bean.getShoutNo()==null?"0":bean.getShoutNo().replace("-","")));
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getId());
                    UIHelper.jump(_activity,ActivityInventoryVerificationDetails.class,bundle);
                }
            });
        }
    }

}
