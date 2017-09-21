package com.lida.carcare.tpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityAddCustomer;
import com.lida.carcare.activity.ActivityChooseCustomer;
import com.lida.carcare.activity.ActivityCustomerDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CustomerMainBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogDeleteCustomer;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择联系人
 * Created by Administrator on 2017/4/5.
 */

public class ActivityChooseCustomerTpl extends BaseTpl<CustomerMainBean.DataBean> {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvGrade)
    TextView tvGrade;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityChooseCustomerTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityChooseCustomerTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitychoosecustomer;
    }

    @Override
    public void setBean(final CustomerMainBean.DataBean bean, int position) {
        if (bean != null) {
            tvName.setText(bean.getCustomerName());
            tvGrade.setText(bean.getCustomerLevelName());
            tvPhone.setText(bean.getMobilePhoneNo());
            llItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String flag = ((ActivityChooseCustomer) _activity).flag;
                    if("ActivityAddCar".equals(flag) || "ActivityPrepaidPhoneCard".equals(flag)||"ActivityOpenTimeCard".equals(flag)||"FragmentCarInfoDetail".equals(flag)){
                        Intent intent = new Intent();
                        intent.putExtra("name",bean.getCustomerName());
                        intent.putExtra("phone",bean.getMobilePhoneNo());
                        intent.putExtra("userId",bean.getId());
                        intent.putExtra("sex",bean.getSex());
                        intent.putExtra("nickname",bean.getNickname());
                        _activity.setResult(Activity.RESULT_OK, intent);
                        _activity.finish();
                    }else {
                        Bundle bundle = new Bundle();
                        bundle.putString("updateId",bean.getId());
                        UIHelper.jumpForResult(_activity, ActivityCustomerDetail.class,bundle,1002);
                    }
                }
            });
            final ActivityChooseCustomer activity = (ActivityChooseCustomer)_activity;
            root.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final DialogDeleteCustomer dialog = new DialogDeleteCustomer(_activity,bean.getCustomerName());
                    dialog.setOnSelectOperation(new DialogDeleteCustomer.OnDialogOperation() {
                        @Override
                        public void onDelClick() {
                            dialog.dismiss();
                            AppUtil.getCarApiClient(ac).deleteCustomerById(bean.getId(),new BaseApiCallback(){
                                @Override
                                public void onApiStart(String tag) {
                                    super.onApiStart(tag);
                                    if(activity!=null) {
                                        activity.showLoadingDlg();
                                    }
                                }

                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if(activity!=null) {
                                        activity.hideLoadingDlg();
                                    }
                                    if(res.isOK()){
                                        UIHelper.t(_activity,"操作成功");
                                        listViewHelper.refresh();
                                    }else {
                                        UIHelper.t(_activity,res.getMsg());
                                    }
                                }

                                @Override
                                public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                    super.onApiFailure(t, errorNo, strMsg, tag);
                                    if(activity!=null) {
                                        activity.hideLoadingDlg();
                                    }
                                }
                            });
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }
    }
}
