package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityDispatchToWorker;
import com.lida.carcare.bean.WorkOrderBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 施工单
 * Created by Administrator on 2017/4/5.
 */

public class ActivityWorkOrderTpl extends BaseTpl<WorkOrderBean.DataBean>
{

    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivPic)
    RoundedImageView ivPic;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;
    @BindView(R.id.tvServerItem)
    TextView tvServerItem;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvServers)
    TextView tvServers;

    public ActivityWorkOrderTpl(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ActivityWorkOrderTpl(Context context)
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
    public void setBean(final WorkOrderBean.DataBean bean, final int position) {
        if (bean != null) {
            tvName.setText(bean.getCustomerName());
            tvCarNo.setText(bean.getCarNo());
            final String item = bean.getGoodsProject() + bean.getMaintainProject()
                    + bean.getRepairProject() + bean.getRefitProject();
            tvServerItem.setText("服务项目：" + item);
            List<WorkOrderBean.CarDispatches> serverList = bean.getCarDispatches();
            final StringBuilder serversBuilder = new StringBuilder();
            for (int i = 0; i < serverList.size(); i++) {
                String s = serverList.get(i).getImplementName();
                if (s != null && !"".equals(s)) {
                    if (!serversBuilder.toString().contains(s)) {
                        serversBuilder.append(s + ",");
                    }
                }
            }
            tvServers.setText("施工人员：" + serversBuilder.toString());
            tvPrice.setText(bean.getConsumptionAmount() + "");
            final Bundle bundle = new Bundle();
            if ("0".equals(bean.getWorkStatus()) || "1".equals(bean.getWorkStatus())) {
                llItem.setOnClickListener(new OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if ("1".equals(ac.user_type) == false) {
                            boolean flag = ("".equals(serversBuilder.toString()) || serversBuilder.toString() == null) ? false : true;
                            bundle.putString("item", item);
                            bundle.putString("carNo", bean.getCarNo());
                            bundle.putString("name", bean.getCustomerName());
                            bundle.putString("userId", bean.getId());
                            bundle.putBoolean("flag", flag);//没有施工人员的话就不请求
                            UIHelper.jumpForResult(_activity, ActivityDispatchToWorker.class, bundle, 1001);
                        } else
                        {
                            UIHelper.t(_activity, "权限不够，不能查看!");
                        }
                    }
                });
                if ("0".equals(bean.getWorkStatus())) {
                    tvStatus.setText("待派工");
                } else if ("1".equals(bean.getWorkStatus())) {
                    tvStatus.setText("服务中");
                }
            } else if ("2".equals(bean.getWorkStatus())) {
                tvStatus.setText("已完成");
            }
            tvTime.setText("派工时间：" + bean.getEntryTime());
        }
    }
}
