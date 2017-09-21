package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;

import com.lida.carcare.R;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import butterknife.ButterKnife;

/**
 * 项目详情
 * Created by Administrator on 2017/4/5.
 */

public class ActivityProjectDetailTpl extends BaseTpl<NetResult> {

    public ActivityProjectDetailTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityProjectDetailTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityprojectdetail;
    }

    @Override
    public void setBean(NetResult bean, final int position) {
    }
}
