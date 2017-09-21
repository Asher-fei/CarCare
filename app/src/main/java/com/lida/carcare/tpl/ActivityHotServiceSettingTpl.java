package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityHotServiceSetting;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServerHotBean;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogIfDelete;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 热门服务设置列表
 * Created by xkr on 2017/9/11.
 */

public class ActivityHotServiceSettingTpl extends BaseTpl<ServerHotBean.DataBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.ivDel)
    ImageView ivDel;

    public ActivityHotServiceSettingTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityHotServiceSettingTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_hot_service_setting;
    }

    @Override
    public void setBean(final ServerHotBean.DataBean bean, int position) {
        if (bean != null) {
            tvName.setText(bean.getName()==null?"":bean.getName());
            tvPrice.setText(bean.getPrice()==null?"":("¥"+bean.getPrice()));
            ac.setImage(iv,bean.getServiceImg());
            ivDel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogIfDelete dialog = new DialogIfDelete(_activity,"");
                    final ActivityHotServiceSetting activity = (ActivityHotServiceSetting)_activity;
                    dialog.setOnOkButtonClick(new DialogIfDelete.onOkButtonClick() {
                        @Override
                        public void delete() {
                            dialog.dismiss();
                            AppUtil.getCarApiClient(ac).deleteJfomCategory(bean.getId(),new BaseApiCallback(){
                                @Override
                                public void onApiStart(String tag) {
                                    super.onApiStart(tag);
                                    if(activity!=null){
                                        activity.showLoadingDlg();
                                    }
                                }

                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if(activity!=null){
                                        activity.hideLoadingDlg();
                                    }
                                    if(res.isOK()){
                                        UIHelper.t(_activity,"操作成功");
                                        listViewHelper.refresh();
                                    }
                                }

                                @Override
                                public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                    super.onApiFailure(t, errorNo, strMsg, tag);
                                    if(activity!=null){
                                        activity.hideLoadingDlg();
                                    }
                                }
                            });
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

}
