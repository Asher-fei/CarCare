package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.GetWarehouseByCodeBean;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.Constant;
import com.midian.base.view.BaseTpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 直接入库选择商品
 * Created by Administrator on 2017/6/29.
 */

public class ActivityChooseGoodCommenTpl extends BaseTpl<GetWarehouseByCodeBean.DataBean.CarWarehouseBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvWarehourse)
    TextView tvWarehourse;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.etCount)
    NumberWidget etCount;

    public ActivityChooseGoodCommenTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityChooseGoodCommenTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityselecyoutboundgoods;
    }

    @Override
    public void setBean(GetWarehouseByCodeBean.DataBean.CarWarehouseBean bean, int position) {
        if (bean != null) {
            ac.setImage(iv, Constant.BASE + bean.getProductImg());
            tvName.setText(bean.getProductName());
            tvWarehourse.setText("库位："+bean.getRepertoryName());
            tvCode.setText("库存："+bean.getStock());
        }
    }
}
