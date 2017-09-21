package com.lida.carcare.tpl;

import android.content.Context;
import android.media.Image;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.NumberWidget;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.Constant;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/28.
 */

public class ActivityInventoryVerificationNewAddTpl extends BaseTpl<SelectOutboundGoodslistBean.DataBean> {


    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.productInternationalCode)
    TextView productInternationalCode;
    @BindView(R.id.repertoryName)
    TextView repertoryName;
    @BindView(R.id.stock)
    TextView stock;
    @BindView(R.id.etCount)
    NumberWidget etCount;


    public ActivityInventoryVerificationNewAddTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityInventoryVerificationNewAddTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_inventory_verification_new_add;
    }

    @Override
    public void setBean(final SelectOutboundGoodslistBean.DataBean bean, int position) {
        if(bean!=null){
            ac.setImage(ivPic, Constant.BASE+bean.getProductImg());
            productName.setText(bean.getProductName()==null?"":bean.getProductName());
            productInternationalCode.setText(bean.getProductInternationalCode()==null?"":bean.getProductInternationalCode());
            repertoryName.setText(bean.getRepertoryName()==null?"":bean.getRepertoryName());
            stock.setText(bean.getStock()==null?"":"库存："+bean.getStock());
            etCount.setOnNumberChangeListener(new BaseTextWatcher(){
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    bean.setSelectCount(s.toString());
                }
            });
        }
    }
}
