package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.MemberBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 员工管理-已通过
 * Created by Administrator on 2017/4/5.
 */

public class FragmenPassTpl extends BaseTpl<MemberBean.DataBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;

    public FragmenPassTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmenPassTpl(Context context) {
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
    public void setBean(MemberBean.DataBean bean, final int position) {
//        llItem.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(position%3==0){
//                    UIHelper.jump(_activity, ActivityStaffDetail1.class);
//                }else if(position%3==1){
//                    UIHelper.jump(_activity, ActivityStaffDetail2.class);
//                }else if(position%3==2){
//                    UIHelper.jump(_activity, ActivityStaffDetail3.class);
//                }
//            }
//        });
        if(bean!=null){
            if(bean.getHeadPortrait()!=null){
                ac.setImage(ivHead,bean.getHeadPortrait());
            }
            tvName.setText(bean.getUsername());
            tvCode.setText("来自："+bean.getShopCode());
        }
    }
}
