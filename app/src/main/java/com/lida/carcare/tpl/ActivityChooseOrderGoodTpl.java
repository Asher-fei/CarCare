package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityOrderGoodChoosed;
import com.lida.carcare.bean.ChooseOrderGoodBean;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.Constant;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择订货商品列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityChooseOrderGoodTpl extends BaseTpl<ChooseOrderGoodBean.DataBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.etCount)
    NumberWidget etCount;

    public ActivityChooseOrderGoodTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityChooseOrderGoodTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitychooseordergood;
    }

    @Override
    public void setBean(final ChooseOrderGoodBean.DataBean bean, int position) {
        if(bean!=null){
            ac.setImage(iv, Constant.BASE+bean.getGoodsImg());
            tvName.setText(bean.getName()==null?"":bean.getName());
            tvCode.setText(bean.getCode()==null?"":bean.getCode());
            tvPrice.setText(String.valueOf(bean.getPrice())==null?"":String.valueOf(bean.getPrice()));
            etCount.setVisibility(GONE);
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean",bean);
                    UIHelper.jump(_activity,ActivityOrderGoodChoosed.class,bundle);
                }
            });
        }


    }


}
