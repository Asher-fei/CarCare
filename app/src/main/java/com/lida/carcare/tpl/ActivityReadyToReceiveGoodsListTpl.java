package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityEnterprisesDetailList;
import com.lida.carcare.activity.ActivityReceiveGoodsCar;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待领料车辆
 * Created by Administrator on 2017/4/5.
 */

public class ActivityReadyToReceiveGoodsListTpl extends BaseTpl<NetResult> {

    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityReadyToReceiveGoodsListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityReadyToReceiveGoodsListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityreadytoreceivegoodslist;
    }

    @Override
    public void setBean(NetResult bean, int position) {
        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity, ActivityReceiveGoodsCar.class);
            }
        });
    }
}
