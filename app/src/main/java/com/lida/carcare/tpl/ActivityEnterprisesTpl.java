package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.PerformanceBean;
import com.midian.base.view.BaseTpl;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 绩效管理
 * Created by Administrator on 2017/4/5.
 */

public class ActivityEnterprisesTpl extends BaseTpl<PerformanceBean.Data.DocumentBean>
{

    @BindView(R.id.item)
    LinearLayout item;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCommission)
    TextView tvCommission;
    @BindView(R.id.head)
    CircleImageView head;

    DecimalFormat format = new DecimalFormat("#0.00");


    public ActivityEnterprisesTpl(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ActivityEnterprisesTpl(Context context)
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
        return R.layout.item_activityenterprises;
    }

    @Override
    public void setBean(PerformanceBean.Data.DocumentBean bean, int position)
    {
        tvName.setText(bean.getUsername());
        // tvCommission.setText("￥"+bean.getAmount());
        if(bean.getAmount()!=null&&!bean.getAmount().equals("")){
            Double d = Double.parseDouble(bean.getAmount());
            tvCommission.setText("￥"+format.format(d));
        }else {
            tvCommission.setText("￥"+bean.getAmount());
        }
        ac.setImage(head, bean.getHeadPortrait());
    }

    @OnClick(R.id.item)
    public void onViewClicked()
    {
       // UIHelper.jump(_activity, ActivityEnterprisesDetail.class);
    }
}
