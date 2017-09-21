package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityGoodDetail;
import com.lida.carcare.bean.GoodSearchResultBean;
import com.midian.base.app.Constant;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品搜索结果列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityGoodSearchResultTpl extends BaseTpl<GoodSearchResultBean.DataBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvCount)
    TextView tvCount;

    public ActivityGoodSearchResultTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityGoodSearchResultTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityservice;
    }

    @Override
    public void setBean(final GoodSearchResultBean.DataBean bean, int position) {
        tvName.setText(bean.getName()==null?"":bean.getName());
        tvPrice.setText("￥"+(bean.getPricePlatform()==null?"":bean.getPricePlatform()));
        tvCount.setText("库存数量:"+(bean.getSafetyInventory()==null?"":bean.getSafetyInventory()));
        if(bean.getProductImg()!=null){
            ac.setImage(iv, Constant.BASE+bean.getProductImg());
        }
        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("userId",bean.getId());
                UIHelper.jump(_activity, ActivityGoodDetail.class, bundle);
            }
        });
    }
}
