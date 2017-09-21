package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityStaffDetail1;
import com.lida.carcare.activity.ActivityStaffDetail2;
import com.lida.carcare.activity.ActivityStaffDetail3;
import com.lida.carcare.bean.MemberBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 员工管理
 * Created by Administrator on 2017/4/5.
 */

public class FragmentReadyToCheckTpl extends BaseTpl<MemberBean.DataBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;

    public FragmentReadyToCheckTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentReadyToCheckTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentreadytocheck;
    }

    @Override
    public void setBean(final MemberBean.DataBean bean, final int position) {
        if(bean!=null){
            if(bean.getHeadPortrait()!=null){
                ac.setImage(ivHead,bean.getHeadPortrait());
            }
            tvName.setText(bean.getUsername());
            tvCode.setText("来自："+bean.getShopCode());
        }

        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("userId",bean.getId());
                UIHelper.jump(_activity, ActivityStaffDetail1.class, bundle);
            }
        });
    }
}
