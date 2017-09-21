package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityOpenTimeCardDetail;
import com.lida.carcare.bean.CardRestrictCarnoBean;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ActivityCardSettingTpl extends BaseTpl<CardRestrictCarnoBean.DataBean>{

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.price)
    TextView price;

    public ActivityCardSettingTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityCardSettingTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_card_setting;
    }

    @Override
    public void setBean(final CardRestrictCarnoBean.DataBean bean, int position) {
        if(bean!=null){
            name.setText(bean.getCardName());
            price.setText("¥"+bean.getCardPrice());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getId());
                    UIHelper.jumpForResult(_activity, ActivityOpenTimeCardDetail.class,bundle,1001);
                }
            });
        }
    }
}
