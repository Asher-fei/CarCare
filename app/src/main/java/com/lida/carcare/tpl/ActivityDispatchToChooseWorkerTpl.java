package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.lida.carcare.R;
import com.lida.carcare.bean.GetUserListBean;
import com.lida.carcare.bean.RoleBean;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 根据项目选择技师
 * Created by Administrator on 2017/4/5.
 */

public class ActivityDispatchToChooseWorkerTpl extends BaseTpl<GetUserListBean.DataBean> {

    @BindView(R.id.cb)
    CheckBox cb;

    public ActivityDispatchToChooseWorkerTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityDispatchToChooseWorkerTpl(Context context) {
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
    public void setBean(final GetUserListBean.DataBean bean, int position) {
        if (bean != null) {
            cb.setText(bean.getUsername());
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
