package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityDispatchToChooseWorkerNew;
import com.lida.carcare.bean.ServiceItemBean;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 派单到员工
 * Created by Administrator on 2017/4/5.
 */

public class ActivityDispatchToWorkerTpl extends BaseTpl<ServiceItemBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.tvItem)
    TextView tvItem;
    @BindView(R.id.tvName)
    TextView tvName;

    public ActivityDispatchToWorkerTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityDispatchToWorkerTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitydispatcktoworker;
    }

    @Override
    public void setBean(final ServiceItemBean bean, final int position) {
        if(bean!=null){
            tvItem.setText(bean.getItemName());
            tvName.setText(bean.getWorkers());
            llItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
//                    bundle.putString("item",bean.getItemName());
                    bundle.putInt("position",position);
//                    UIHelper.jumpForResult(_activity, ActivityDispatchToChooseWorker.class,bundle,1001);
                    UIHelper.jumpForResult(_activity, ActivityDispatchToChooseWorkerNew.class, bundle, 1001);
                }
            });
        }
    }
}
