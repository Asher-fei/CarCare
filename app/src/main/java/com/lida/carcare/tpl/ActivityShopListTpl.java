package com.lida.carcare.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.SelectShopListBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车店列表
 * Created by Administrator on 2017/4/5.
 */

public class ActivityShopListTpl extends BaseTpl<SelectShopListBean.DataBean> {

    @BindView(R.id.tv)
    TextView tv;

    public ActivityShopListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityShopListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityshoplist;
    }

    @Override
    public void setBean(final SelectShopListBean.DataBean bean, int position) {
        if (bean != null) {
            tv.setText(bean.getShopName());
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("userId",bean.getId());
                    intent.putExtra("name",bean.getShopName());
                    _activity.setResult(Activity.RESULT_OK, intent);
                    _activity.finish();
                }
            });
        }
    }
}
