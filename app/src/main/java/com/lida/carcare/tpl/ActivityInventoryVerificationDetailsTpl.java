package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;

import com.lida.carcare.R;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

/**
 * 盘点详情
 * Created by zdf on 2017/6/28.
 */

public class ActivityInventoryVerificationDetailsTpl extends BaseTpl {
    public ActivityInventoryVerificationDetailsTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityInventoryVerificationDetailsTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_inventory_verification_details;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
