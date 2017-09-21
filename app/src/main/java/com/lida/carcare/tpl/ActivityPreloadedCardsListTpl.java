package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.PreloadedCardsListBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 充值卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityPreloadedCardsListTpl extends BaseTpl<PreloadedCardsListBean.DataBean> {

    @BindView(R.id.stores)
    TextView stores;
    @BindView(R.id.consumeCardNo)
    TextView consumeCardNo;
    @BindView(R.id.consumeCardName)
    TextView consumeCardName;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.residualAmount)
    TextView residualAmount;

    public ActivityPreloadedCardsListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityPreloadedCardsListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_preloaded_card_list;
    }

    @Override
    public void setBean(PreloadedCardsListBean.DataBean bean, int position) {
        if (bean != null) {
            stores.setText(ac.shopName);
            consumeCardNo.setText(bean.getConsumeCardNo());
            consumeCardName.setText(bean.getConsumeCardName());
            mobile.setText(bean.getMobile());
            residualAmount.setText("¥ "+bean.getResidualAmount());
        }
    }

}
