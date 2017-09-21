package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.lida.carcare.R;
import com.lida.carcare.bean.PositionBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 职位
 * Created by Administrator on 2017/4/5.
 */

public class ActivityZhiWeiTpl extends BaseTpl<PositionBean.DataBean> {

    @BindView(R.id.cb)
    CheckBox cb;

    public ActivityZhiWeiTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityZhiWeiTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityrolepermission;
    }

    @Override
    public void setBean(final PositionBean.DataBean bean, int position) {
        if (bean != null) {
            cb.setText(bean.getDepartname());
            if(bean.isSelect()){
                cb.setChecked(true);
            }else{
                cb.setChecked(false);
            }
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    bean.setSelect(isChecked);
                }
            });
        }
    }
}
