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
 * 派单
 * Created by Administrator on 2017/4/5.
 */

public class ActivityDispatchTpl extends BaseTpl<NetResult>
{

    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityDispatchTpl(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ActivityDispatchTpl(Context context)
    {
        super(context);
    }

    @Override
    protected void initView()
    {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.item_activityworkorder;
    }

    @Override
    public void setBean(NetResult bean, final int position)
    {
        llItem.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (position % 2 == 0)
                {
                    UIHelper.jump(_activity, ActivityDispatchChanged.class);
                } else
                {
                    UIHelper.jump(_activity, ActivityChooseServer.class);
                }
            }
        });
    }
}
