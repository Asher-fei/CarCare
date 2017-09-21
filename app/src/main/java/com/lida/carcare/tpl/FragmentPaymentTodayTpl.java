package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityChooseServer;
import com.lida.carcare.activity.ActivityDispatchChanged;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 出入库记录-今日出入库
 * Created by Administrator on 2017/4/5.
 */

public class FragmentPaymentTodayTpl extends BaseTpl<NetResult> {

    public FragmentPaymentTodayTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentPaymentTodayTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentpaymenttoday;
    }

    @Override
    public void setBean(NetResult bean, final int position) {
    }
}
