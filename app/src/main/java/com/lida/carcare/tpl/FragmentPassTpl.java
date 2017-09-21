package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityStaffDetail2;
import com.lida.carcare.bean.MemberBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 员工管理
 * Created by Administrator on 2017/4/5.
 */

public class FragmentPassTpl extends BaseTpl<MemberBean.DataBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;

    public FragmentPassTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentPassTpl(Context context) {
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
        final StringBuilder temp = new StringBuilder();
        if(bean!=null){
            if(bean.getHeadPortrait()!=null){
                ac.setImage(ivHead,bean.getHeadPortrait());
            }
            tvCode.setVisibility(VISIBLE);
            tvName.setText(bean.getUsername());
            List<MemberBean.DataBean.TsDeparts1Bean> bTemp = bean.getTsDeparts1();
            for (int i = 0; i < bTemp.size(); i++) {
                temp.append(bTemp.get(i).getDePname()+",");
            }
            if(temp.length()>1){
                tvCode.setText("职位："+temp.toString().substring(0,temp.length()-1));
            }else{
                tvCode.setText("职位：空");
            }
        }
        final Bundle bundle = new Bundle();
        bundle.putString("id",bean.getId());

        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if(tvCode.getText().toString().contains("老板")){
                    UIHelper.t(_activity,"老板无需对自己分配角色权限！");
                    return;
                }*/
                UIHelper.jump(_activity, ActivityStaffDetail2.class,bundle);
            }
        });
    }
}
