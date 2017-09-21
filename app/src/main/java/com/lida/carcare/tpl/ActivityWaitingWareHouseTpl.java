package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityLookDetail;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.WaitWareHouseBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogCancellationsRemark;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待入库
 * Created by Administrator on 2017/6/26.
 */

public class ActivityWaitingWareHouseTpl extends BaseTpl<WaitWareHouseBean.DataBean> {

    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.tvCheckDetails)
    TextView tvCheckDetails;
    @BindView(R.id.innerListView)
    InnerListView innerListView;
    @BindView(R.id.logisticsCode)
    TextView logisticsCode;
    @BindView(R.id.logisticsCompany)
    TextView logisticsCompany;
    @BindView(R.id.layout_parent_item)
    LinearLayout layout_parent_item;
    @BindView(R.id.cancellations)
    TextView cancellations;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
   @BindView(R.id.purchaseDate)
   TextView purchaseDate;

    public ActivityWaitingWareHouseTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityWaitingWareHouseTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activity_waitting_ware_house;
    }

    @Override
    public void setBean(final WaitWareHouseBean.DataBean bean, int position) {
        if (bean != null) {
            checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        bean.setCheck(true);
                    } else {
                        bean.setCheck(false);
                    }
                }
            });
            if (bean.isCheck()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
            LogUtils.e("bean.getSupplierCompany():"+bean.getSupplierCompany());
            checkBox.setText(bean.getSupplierCompany());
            tvPrice.setText("¥"+bean.getPrice());
            logisticsCode.setText(bean.getLogisticsCode());
            logisticsCompany.setText(bean.getLogisticsCompany());
            purchaseDate.setText(bean.getPurchaseDate());
            //撤单
            cancellations.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DialogCancellationsRemark dialogCancellationsRemark = new DialogCancellationsRemark(getContext());
                    dialogCancellationsRemark.setOnCancellationsRemarkClick(new DialogCancellationsRemark.OnCancellationsRemarkClick() {
                        @Override
                        public void onCancellationsRemarkClick() {
                            AppUtil.getCarApiClient(ac).updateCarPurchaseStatus(bean.getId(), dialogCancellationsRemark.getContent(), new BaseApiCallback() {
                                @Override
                                public void onApiStart(String tag) {
                                    super.onApiStart(tag);
                                    cancellations.setEnabled(false);
                                }

                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if (res.isOK()) {
                                        listViewHelper.refresh();
                                    }
                                    cancellations.setEnabled(true);
                                    UIHelper.t(getContext(), "操作成功");
                                }

                                @Override
                                public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                    super.onApiFailure(t, errorNo, strMsg, tag);
                                    cancellations.setEnabled(true);
                                    UIHelper.t(getContext(), tag);
                                }
                            });
                        }
                    });
                    dialogCancellationsRemark.show();
                }
            });
            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getId());
                    bundle.putString("flag","采购详情");
                    bundle.putString("price",bean.getPrice());
                    UIHelper.jumpForResult(_activity, ActivityLookDetail.class,bundle,1001);
                }
            });
        }
    }
}
