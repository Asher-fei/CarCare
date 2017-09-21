package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.WillExpireBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ActivityExpireTpl extends BaseTpl<WillExpireBean.DataBean> {

    @BindView(R.id.stores)
    TextView stores;
    @BindView(R.id.consumeCardNo)
    TextView consumeCardNo;
    @BindView(R.id.consumeCardName)
    TextView consumeCardName;
    @BindView(R.id.mobile)
    TextView mobile;

    public ActivityExpireTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityExpireTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_will_expire;
    }

    @Override
    public void setBean(WillExpireBean.DataBean bean, int position) {
        if(bean!=null){
            stores.setText(ac.shopName);
            consumeCardNo.setText(bean.getCardNo()+"("+bean.getCardType()+")");
            consumeCardName.setText(bean.getUserName());
            mobile.setText(bean.getMobile());

        }
    }
}
