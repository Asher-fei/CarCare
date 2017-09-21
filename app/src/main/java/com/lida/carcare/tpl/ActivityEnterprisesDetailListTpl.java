package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.GetDecumentByCarnoBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 绩效管理详情-列表
 * Created by Administrator on 2017/4/5.
 */

public class ActivityEnterprisesDetailListTpl extends BaseTpl<GetDecumentByCarnoBean.DataBean.DocumentBean> {

    @BindView(R.id.tvItem)
    TextView tvItem;
    @BindView(R.id.tvMoney)
    TextView tvMoney;

    public ActivityEnterprisesDetailListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityEnterprisesDetailListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityenterprisesdetaillist;
    }

    @Override
    public void setBean(GetDecumentByCarnoBean.DataBean.DocumentBean bean, int position) {
        if(bean!=null){
            tvItem.setText(bean.getProject());
            tvMoney.setText("￥"+bean.getAmount());
        }
    }
}
