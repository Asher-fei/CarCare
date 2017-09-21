package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterInnerTimeCardService;
import com.lida.carcare.bean.TimeCardListBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 次卡列表
 * Created by Administrator on 2017/7/4.
 */

public class ActivityTimeCardListTpl extends BaseTpl <TimeCardListBean.DataBean>{
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


    public ActivityTimeCardListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityTimeCardListTpl(Context context) {
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
    public void setBean(TimeCardListBean.DataBean bean, int position) {
        if(bean!=null){
             stores.setText(ac.shopName);

            if(bean.getOnceCardNo()!=null) {
                String cardName = bean.getOnceCardType().getCardName()==null?"":"("+bean.getOnceCardType().getCardName()+")";
                onceCardNo.setText(bean.getOnceCardNo() == null ? "" : bean.getOnceCardNo()+cardName);
            }
             onceCardName.setText(bean.getOnceCardName());
             mobile.setText(bean.getMobile());
             carNo.setText(bean.getCarNo()==null?"":bean.getCarNo());
           List<TimeCardListBean.DataBean.OnceCardProjectListsBean> serviceList = bean.getOnceCardProjectLists();
            if(serviceList!=null){
                AdapterInnerTimeCardService adapter = new AdapterInnerTimeCardService(serviceList,ac);
                innerListView.setAdapter(adapter);
            }
        }
    }
}
