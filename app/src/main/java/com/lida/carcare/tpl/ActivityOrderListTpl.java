package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityOrderDetail;
import com.lida.carcare.activity.ActivityOrderedList;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OrderPlaceBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogMyOrderDelete;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityOrderListTpl extends BaseTpl<OrderPlaceBean.DataBean> {

    @BindView(R.id.orderCode)
    TextView orderCode;
    @BindView(R.id.Status)
    TextView Status;
    @BindView(R.id.trackingNumber)
    TextView trackingNumber;
    @BindView(R.id.logisticsCompany)
    TextView logisticsCompany;
    @BindView(R.id.createDate)
    TextView createDate;
    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityOrderListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityOrderListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityorderedlist;
    }

    @Override
    public void setBean(final OrderPlaceBean.DataBean bean, int position) {
        if (bean != null) {
            orderCode.setText(bean.getOrderCode()==null?"":bean.getOrderCode());
           if( bean.getStatus()!=null){
                if(bean.getState().equals("0")){
                    Status.setText("待付款");
                }else if(bean.getState().equals("1")){
                    Status.setText("待发货");
                }else if(bean.getState().equals("2")){
                    Status.setText("已发货");
                }else if(bean.getState().equals("3")){
                    Status.setText("已收货");
                }
            }
            logisticsCompany.setText(bean.getLogisticsCompany()==null?"":bean.getLogisticsCompany());
            trackingNumber.setText(bean.getTrackingNumber()==null?"":bean.getTrackingNumber());
            createDate.setText(bean.getCreateDate()==null?"":bean.getCreateDate());
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getId());
                    UIHelper.jump(_activity, ActivityOrderDetail.class,bundle);
                }
            });
            final ActivityOrderedList activity = (ActivityOrderedList) (_activity);
            root.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final DialogMyOrderDelete dialog = new DialogMyOrderDelete(_activity);
                    dialog.setOnSelectOperation(new DialogMyOrderDelete.onOperation() {
                        @Override
                        public void onDelClick() {
                            dialog.dismiss();
                            AppUtil.getCarApiClient(ac).deleteplaceAnOrder(bean.getId(),new BaseApiCallback(){
                                @Override
                                public void onApiStart(String tag) {
                                    super.onApiStart(tag);
                                    activity.showLoadingDlg();
                                }

                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    activity.hideLoadingDlg();
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
                                    activity.hideLoadingDlg();
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
