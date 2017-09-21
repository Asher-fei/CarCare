package com.lida.carcare.tpl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityGoodDetail;
import com.lida.carcare.adapter.AdapterGoodsManager;
import com.lida.carcare.bean.GoodListBean;
import com.midian.base.app.Constant;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityGoodTpl extends BaseTpl<GoodListBean.DataBean.JfomGoodsBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    public static boolean isSelect = false;
    public static AdapterGoodsManager.OnItemClickListener listener = null;
    public static Activity activity = null;
    @BindView(R.id.tvCount)
    TextView tvCount;

    public ActivityGoodTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityGoodTpl(Context context) {
        super(context);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
    public void setBean(final GoodListBean.DataBean.JfomGoodsBean bean, int position) {
        tvName.setText(bean.getName());
        tvPrice.setText("￥" + bean.getPricePlatform());
        if("".equals(bean.getStock())||null==bean.getStock()){
            tvCount.setVisibility(GONE);
        }else{
           // tvCount.setText("库存数量："+bean.getSafetyInventory());
            tvCount.setText("库存数量："+(bean.getStock()==null?"":bean.getStock()));
        }
        if (!bean.getPricePlatform().equals("")) {

            //直观
            Double d = Double.parseDouble(bean.getPricePlatform());
            DecimalFormat decimalFormat = new DecimalFormat("#0.0");//格式化设置
            tvPrice.setText("￥" + decimalFormat.format(d));

            //精确
           /* BigDecimal bigDecimal = new BigDecimal(bean.getPricePlatform());
            tvPrice.setText("￥" + bigDecimal.toString());*/

        }

        if (bean.getProductImg() != null) {
            ac.setImage(iv, Constant.BASE + bean.getProductImg());
        }
        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelect == false) {
                    Bundle bundle = new Bundle();
                    bundle.putString("userId", bean.getId());
                    UIHelper.jumpForResult(_activity, ActivityGoodDetail.class, bundle, 1002);
                } else {
                    if (listener != null) {
                        listener.onClick(bean);
                        activity.finish();
                    }
                }
            }
        });
    }
}
