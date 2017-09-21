package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterInnerOverTimeLessService;
import com.lida.carcare.bean.OverTimeLessThanBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/3.
 */

public class ActivityOverTimeLessThanTpl extends BaseTpl<OverTimeLessThanBean.DataBean> {

    @BindView(R.id.stores)
    TextView stores;
    @BindView(R.id.consumeCardNo)
    TextView consumeCardNo;
    @BindView(R.id.consumeCardName)
    TextView consumeCardName;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.carNo)
    TextView carNo;
    @BindView(R.id.innerListView)
    InnerListView innerListView;

    public ActivityOverTimeLessThanTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityOverTimeLessThanTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_over_time_less_than;
    }

    @Override
    public void setBean(OverTimeLessThanBean.DataBean bean, int position) {
        if (bean != null) {
            stores.setText(ac.shopName);
            if (bean.getOnceCardNo() != null) {
                String cardName = bean.getOnceCardType().getCardName() == null ? "" : "(" + bean.getOnceCardType().getCardName() + ")";
                consumeCardNo.setText(bean.getOnceCardNo() == null ? "" : bean.getOnceCardNo() + cardName);
            }
            consumeCardName.setText(bean.getOnceCardName());
            mobile.setText(bean.getMobile());
            carNo.setText(bean.getCarNo()==null?"":bean.getCarNo());
            if (bean.getOnceCardProjectLists() != null) {
                if (bean.getOnceCardProjectLists().size() > 0) {
                    AdapterInnerOverTimeLessService adapter = new AdapterInnerOverTimeLessService(bean.getOnceCardProjectLists(),ac);
                    innerListView.setAdapter(adapter);
                }
            }


        }
    }
}
