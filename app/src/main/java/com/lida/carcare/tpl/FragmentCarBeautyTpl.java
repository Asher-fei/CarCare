package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCarDetailInfo;
import com.lida.carcare.activity.ActivityDispatchToWorker;
import com.lida.carcare.activity.ActivityModifyCarInfo;
import com.lida.carcare.activity.ActivitySettleMent;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarInShopBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogCommen;
import com.lida.carcare.widget.DialogIfCancel;
import com.lida.carcare.widget.DialogIfSettleAccounts;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 在店车辆-美容
 * Created by WeiQingFeng on 2017/4/13.
 */

public class FragmentCarBeautyTpl extends BaseTpl<CarInShopBean.DataBean> {

    @BindView(R.id.tvCarNum)
    TextView tvCarNum;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvServiceItem)
    TextView tvServiceItem;
    @BindView(R.id.tvServer)
    TextView tvServer;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnChange)
    Button btnChange;
    @BindView(R.id.btnSettleMent)
    Button btnSettleMent;
    @BindView(R.id.layout)
    View layout;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.btnLayout)
    View btnLayout;
    @BindView(R.id.btnGetCar)
    Button btnGetCar;
    @BindView(R.id.btnDispatching)
    Button btnDispatching;

    public FragmentCarBeautyTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentCarBeautyTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragmentcarbeauty;
    }

    @Override
    public void setBean(final CarInShopBean.DataBean bean, int position) {
        if (bean != null) {
            final String serviceItem = bean.getGoodsProject() + bean.getMaintainProject() + bean.getRepairProject() + bean.getRefitProject();
            OnClickListener listener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                   final Bundle bundle = new Bundle();
                    switch (v.getId()) {
                        case R.id.btnCancel:
                            final DialogIfCancel dialogIfCancel = new DialogIfCancel(_activity);
                            dialogIfCancel.setOnOkButtonClick(new DialogIfCancel.onOkButtonClick() {
                                @Override
                                public void delete() {
                                    AppUtil.getCarApiClient(ac).cancel(bean.getId(), new BaseApiCallback() {
                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            if (res.isOK()) {
                                                dialogIfCancel.dismiss();
                                                UIHelper.t(_activity, "已取消");
                                                listViewHelper.refresh();
                                            }
                                        }
                                    });
                                }
                            });
                            dialogIfCancel.show();
                            break;
                        case R.id.btnSettleMent:
                            if ("结算".equals(btnSettleMent.getText().toString())) {
                                 bundle.putSerializable("bean", bean);
                                if("0".equals(bean.getWorkStatus())){
                                    final DialogIfSettleAccounts dialog = new DialogIfSettleAccounts(_activity);
                                    dialog.setOnOkButtonClick(new DialogIfSettleAccounts.onOkButtonClick() {
                                        @Override
                                        public void btnOk() {
                                            dialog.dismiss();
                                            UIHelper.jump(_activity, ActivitySettleMent.class, bundle);
                                        }

                                        @Override
                                        public void btnCancel() {
                                            dialog.dismiss();
                                            bundle.putString("item", serviceItem);
                                            bundle.putString("carNo", bean.getCarNo());
                                            bundle.putString("name", bean.getCustomerName());
                                            bundle.putString("userId", bean.getId());
                                            bundle.putBoolean("flag", true);//没有施工人员的话就不请求
                                            UIHelper.jumpForResult(_activity, ActivityDispatchToWorker.class, bundle, 1001);
                                        }
                                    });
                                    dialog.show();
                                }else {
                                    UIHelper.jump(_activity, ActivitySettleMent.class, bundle);
                                }

                            } /*else
                                if ("去派工".equals(btnSettleMent.getText().toString())) {
                                bundle.putString("item", serviceItem);
                                bundle.putString("carNo", bean.getCarNo());
                                bundle.putString("name", bean.getCustomerName());
                                bundle.putString("userId", bean.getId());
                                bundle.putBoolean("flag", true);//没有施工人员的话就不请求
                                UIHelper.jumpForResult(_activity, ActivityDispatchToWorker.class, bundle, 1001);
                            }*/
                            break;
                        case R.id.btnDispatching:
                                bundle.putString("item", serviceItem);
                                bundle.putString("carNo", bean.getCarNo());
                                bundle.putString("name", bean.getCustomerName());
                                bundle.putString("userId", bean.getId());
                                bundle.putBoolean("flag", true);//没有施工人员的话就不请求
                                UIHelper.jumpForResult(_activity, ActivityDispatchToWorker.class, bundle, 1001);
                            break;
                        case R.id.btnChange:
                            String type = bean.getWorkStatus();//0 待派工 可以增加删除项目   1 服务中，只能增加不能删除
                            bundle.putString("userId", bean.getId());
                            bundle.putString("status", type);
                            UIHelper.jump(_activity, ActivityModifyCarInfo.class, bundle);
                            break;
                        case R.id.layout:
                            bundle.putString("id", bean.getId());
                            bundle.putString("carNo", bean.getCarNo());
                            bundle.putString("WorkStatus", bean.getWorkStatus());
                            UIHelper.jump(_activity, ActivityCarDetailInfo.class, bundle);
                            break;
                        case R.id.btnGetCar:
                            final DialogCommen dialog = new DialogCommen(_activity, "确认提车？");
                            dialog.setOnOkButtonClick(new DialogCommen.onOkButtonClick() {
                                @Override
                                public void delete() {
                                    AppUtil.getCarApiClient(ac).pickCar(bean.getId(), new BaseApiCallback() {
                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            super.onApiSuccess(res, tag);
                                            if (res.isOK()) {
                                                UIHelper.t(_activity, "已提车");
                                                dialog.dismiss();
                                                listViewHelper.refresh();
                                            }
                                        }
                                    });
                                }
                            });
                            dialog.show();
                            break;
                    }
                }
            };
            int visibility = "1".equals(ac.user_type)||"6".equals(ac.user_type) ? View.INVISIBLE : View.VISIBLE;
            btnLayout.setVisibility(visibility);
            layout.setOnClickListener(listener);
            btnCancel.setOnClickListener(listener);
            btnSettleMent.setOnClickListener(listener);
            btnDispatching.setOnClickListener(listener);
            btnChange.setOnClickListener(listener);
            btnGetCar.setOnClickListener(listener);
            tvCarNum.setText(bean.getCarNo());

            String name = (bean.getCustomerName() == null ? "无" : bean.getCustomerName()) + (bean.getPhone() == null ? "" : "(" + bean.getPhone() + ")");

            tvName.setText(name);
            tvServer.setText(bean.getName());
            String tmp = serviceItem.substring(0, serviceItem.length() - 1);
            tvServiceItem.setText(tmp);
            tvDate.setText(bean.getEntryTime());
            tvPrice.setText("￥" + bean.getConsumptionAmount());
            if ("0".equals(bean.getWorkStatus()) || "1".equals(bean.getWorkStatus())) {
                btnChange.setVisibility(VISIBLE);
                btnSettleMent.setVisibility(VISIBLE);
                btnDispatching.setVisibility(VISIBLE);
                if ("0".equals(bean.getWorkStatus())) {
                    tvStatus.setText("待派工");
                   // btnSettleMent.setText("去派工");
                    btnCancel.setVisibility(VISIBLE);
                    btnGetCar.setVisibility(GONE);
                } else if ("1".equals(bean.getWorkStatus())) {
                    tvStatus.setText("服务中");
                    btnCancel.setVisibility(GONE);
                    btnSettleMent.setText("结算");
                    btnGetCar.setVisibility(GONE);
                }
            } else if ("2".equals(bean.getWorkStatus())) {
                btnCancel.setVisibility(GONE);
                btnChange.setVisibility(GONE);
                btnSettleMent.setVisibility(GONE);
                btnDispatching.setVisibility(GONE);
                tvStatus.setText("已完工");
                btnGetCar.setVisibility(VISIBLE);
            } else if ("4".equals(bean.getWorkStatus())) {
                btnCancel.setVisibility(GONE);
                btnChange.setVisibility(VISIBLE);
                btnSettleMent.setVisibility(VISIBLE);
                btnDispatching.setVisibility(VISIBLE);
                //btnSettleMent.setText("去派工");
                tvStatus.setText("服务中");
                btnGetCar.setVisibility(GONE);
            }
        }
    }
}
