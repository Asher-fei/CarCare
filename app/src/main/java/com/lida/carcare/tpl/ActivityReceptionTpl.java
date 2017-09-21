package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;

import com.lida.carcare.R;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

/**
 * 接车出单
 * Created by Administrator on 2017/4/5.
 */

public class ActivityReceptionTpl extends BaseTpl<NetResult> {

    public ActivityReceptionTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityReceptionTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityreception;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
