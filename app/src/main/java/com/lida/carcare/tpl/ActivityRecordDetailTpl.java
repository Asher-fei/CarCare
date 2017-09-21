package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityRechargeDetail;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 记录明细
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityRecordDetailTpl extends BaseTpl {

    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityRecordDetailTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityRecordDetailTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityrecorddetail;
    }

    @Override
    public void setBean(NetResult bean, int position) {
        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity,ActivityRechargeDetail.class);
            }
        });
    }
}
