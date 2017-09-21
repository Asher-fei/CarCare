package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;

import com.lida.carcare.R;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/30.
 */

public class ActivityIncomeDetailTpl extends BaseTpl {

    public ActivityIncomeDetailTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityIncomeDetailTpl(Context context) {
        super(context);
    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_income_detail;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
