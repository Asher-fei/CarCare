package com.lida.carcare.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.Constant;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/21.
 */

public class ActivityQueryAllGoodsTpl extends BaseTpl<QueryAllGoodsBean.DataBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.etCount)
    NumberWidget etCount;
    @BindView(R.id.llPrice)
    LinearLayout llPrice;

    public ActivityQueryAllGoodsTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityQueryAllGoodsTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_purchase;
    }

    @Override
    public void setBean(final QueryAllGoodsBean.DataBean bean, int position) {
        if (bean != null) {
            llPrice.setVisibility(GONE);
            tvName.setText(bean.getName());
            tvCode.setText("编码：" + bean.getInternationalCode());
            ac.setImage(iv, Constant.BASE + bean.getProductImg());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("bean", bean);
                    _activity.setResult(Activity.RESULT_OK, intent);
                    _activity.finish();
                }
            });
        }
    }
}
