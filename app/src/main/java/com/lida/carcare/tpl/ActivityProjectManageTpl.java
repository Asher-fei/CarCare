package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityProjectDetail;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目管理
 * Created by Administrator on 2017/4/5.
 */

public class ActivityProjectManageTpl extends BaseTpl<NetResult> {

    @BindView(R.id.tv)
    TextView tv;

    public ActivityProjectManageTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityProjectManageTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityprojectmanege;
    }

    @Override
    public void setBean(NetResult bean, final int position) {
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity, ActivityProjectDetail.class);
            }
        });
    }
}
