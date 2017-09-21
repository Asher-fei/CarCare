package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityServiceDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogIfDelete;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服务列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityServiceTpl extends BaseTpl<ServiceGoodBean.DataBean.JfomServiceBean> {

    @BindView(R.id.llItem)
    LinearLayout llItem;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.ivDel)
    ImageView ivDel;
    @BindView(R.id.tvDrawPrice)
    TextView tvDrawPrice;
    @BindView(R.id.tvCount)
    TextView tvCount;

    public ActivityServiceTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityServiceTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityservice;
    }

    @Override
    public void setBean(final ServiceGoodBean.DataBean.JfomServiceBean bean, int position) {
        tvName.setText(bean.getName());
        tvPrice.setText("￥" + bean.getServicePrice());
        ivDel.setVisibility(VISIBLE);
        tvDrawPrice.setVisibility(VISIBLE);
        tvCount.setVisibility(GONE);
        if (bean.getServiceImg() != null) {
            ac.setImage(iv, Constant.BASE + bean.getServiceImg());
        }
        if("0".equals(bean.getDrawType())){
            tvDrawPrice.setText("提成："+bean.getDrawPrice()+"%");
        }else{
            tvDrawPrice.setText("提成："+"￥"+bean.getDrawPrice());
        }
        ivDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogIfDelete dialogIfDelete = new DialogIfDelete(_activity);
                final BaseListActivity baseListActivity = (BaseListActivity)_activity;
                dialogIfDelete.setOnOkButtonClick(new DialogIfDelete.onOkButtonClick() {
                    @Override
                    public void delete() {
                        dialogIfDelete.dismiss();
                        AppUtil.getCarApiClient(ac).deleteService(bean.getId(), new BaseApiCallback() {

                            @Override
                            public void onApiStart(String tag) {
                                super.onApiStart(tag);
                                baseListActivity.showLoadingDlg();
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                super.onApiFailure(t, errorNo, strMsg, tag);
                                baseListActivity.hideLoadingDlg();
                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                baseListActivity.hideLoadingDlg();
                                if (res.isOK()) {
                                    UIHelper.t(_activity, "删除成功！");
                                    dialogIfDelete.dismiss();
                                    listViewHelper.refresh();
                                }else {
                                    ac.handleErrorCode(_activity,res.getMsg());
                                }
                            }
                        });
                    }
                });
                dialogIfDelete.show();
            }
        });
        llItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("userId", bean.getId());
                UIHelper.jumpForResult(_activity, ActivityServiceDetail.class, bundle, 1002);
            }
        });
    }
}
