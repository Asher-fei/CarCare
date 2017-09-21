package com.lida.carcare.tpl;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.ChooseOrderGoodBean;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.Constant;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 已选择订货商品列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityOrderGoodChoosedTpl extends BaseTpl<ChooseOrderGoodBean.DataBean> {

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
    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityOrderGoodChoosedTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityOrderGoodChoosedTpl(Context context) {
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
        if (bean != null) {
            ac.setImage(iv, Constant.BASE+bean.getGoodsImg());
            tvName.setText(bean.getName()==null?"":bean.getName());
            tvCode.setText(bean.getCode()==null?"":bean.getCode());
            tvPrice.setText(String.valueOf(bean.getPrice())==null?"":String.valueOf(bean.getPrice()));
            etCount.setTag(bean.getId());
            etCount.setOnNumberChangeListener(new BaseTextWatcher(){
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    if(etCount.getTag().toString().equals(bean.getId())){
                        if(!s.toString().trim().equals("")) {
                            bean.setCount(s.toString());
                        }else {
                            bean.setCount("1");
                        }
                    }
                }
            });
        }
    }
}
