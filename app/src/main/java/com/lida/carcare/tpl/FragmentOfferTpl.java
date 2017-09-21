package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.SelectInfoOfferBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public class FragmentOfferTpl extends BaseTpl<SelectInfoOfferBean.DataBean.JfomServiceBean> {

    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvDiscount)
    TextView tvDiscount;
    @BindView(R.id.tvCount)
    TextView tvCount;

    public FragmentOfferTpl(Context context) {
        super(context);
    }

    public FragmentOfferTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentoffer;
    }

    @Override
    public void setBean(SelectInfoOfferBean.DataBean.JfomServiceBean bean, int position) {
        if(bean!=null){
            if(bean.getFrequency()==null||bean.getFrequency().equals("")) {
                tvItemName.setText(bean.getName());
            }else {
                tvItemName.setText(bean.getName()+" x "+bean.getFrequency());
            }
                tvPrice.setText("单价："+bean.getServicePrice());
        }
    }
}
