package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterInnerUnlimitedLimitedOfCardsService;
import com.lida.carcare.bean.UnlimitedListBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.view.BaseTpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 无限卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityUnlimitedListTpl extends BaseTpl<UnlimitedListBean.DataBean> {
    @BindView(R.id.stores)
    TextView stores;
    @BindView(R.id.onceCardNo)
    TextView onceCardNo;
    @BindView(R.id.onceCardName)
    TextView onceCardName;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.carNo)
    TextView carNo;
    @BindView(R.id.innerListView)
    InnerListView innerListView;

    public ActivityUnlimitedListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityUnlimitedListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_time_card_list;
    }

    @Override
    public void setBean(UnlimitedListBean.DataBean bean, int position) {
        if(bean!=null){
            stores.setText(ac.shopName);
            if(bean.getOnceCardNo()!=null) {
                String cardName = bean.getOnceCardType().getCardName() == null ? "" : "(" + bean.getOnceCardType().getCardName() + ")";
                onceCardNo.setText(bean.getOnceCardNo() == null ? "" : bean.getOnceCardNo() + cardName);
            }
            onceCardName.setText(bean.getOnceCardName());
            mobile.setText(bean.getMobile());
            carNo.setText(bean.getCarNo()==null?"":bean.getCarNo());
            List<UnlimitedListBean.DataBean.OnceCardProjectListsBean> beanService = bean.getOnceCardProjectLists();
            if(beanService!=null){
                AdapterInnerUnlimitedLimitedOfCardsService adapter = new AdapterInnerUnlimitedLimitedOfCardsService(beanService,ac);
                innerListView.setAdapter(adapter);
            }
        }
    }
}
