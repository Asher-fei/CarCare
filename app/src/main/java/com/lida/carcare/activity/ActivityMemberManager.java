package com.lida.carcare.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.MemberManagementBean;
import com.lida.carcare.widget.PopMemberManagement;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员管理
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityMemberManager extends BaseActivity implements OnItemClickListener {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvMemberNumber)
    TextView tvMemberNumber;
    @BindView(R.id.llMemberNumber)
    LinearLayout llMemberNumber;
    @BindView(R.id.tvMoneyNotEnough)
    TextView tvMoneyNotEnough;
    @BindView(R.id.llMoneyNotEnough)
    LinearLayout llMoneyNotEnough;
    @BindView(R.id.tvCountNotEnough)
    TextView tvCountNotEnough;
    @BindView(R.id.llCountNotEnough)
    LinearLayout llCountNotEnough;
    @BindView(R.id.tvBeOverdue)
    TextView tvBeOverdue;
    @BindView(R.id.llBeOverdue)
    LinearLayout llBeOverdue;
    @BindView(R.id.tvOverdue)
    TextView tvOverdue;
    @BindView(R.id.llOverdue)
    LinearLayout llOverdue;
    @BindView(R.id.llMoney)
    LinearLayout llMoney;
    @BindView(R.id.timeCardNumber)
    TextView timeCardNumber;
    @BindView(R.id.numberOfPreloadedCards)
    TextView numberOfPreloadedCards;
    @BindView(R.id.limitedNumberOfCards)
    TextView limitedNumberOfCards;
    @BindView(R.id.unlimitedNumber)
    TextView unlimitedNumber;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.view)
    View view;

    private PopMemberManagement popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membermanager);
        ButterKnife.bind(this);
        topbar.setTitle("会员管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setBackgroundResource(R.drawable.bg_topbar);
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
        AppUtil.getCarApiClient(ac).selectMemberManagementMemberber(this);
    }

    @OnClick({R.id.llMemberNumber, R.id.llMoneyNotEnough, R.id.llCountNotEnough, R.id.llBeOverdue, R.id.llOverdue, R.id.llMoney,R.id.llPreloadedCards,R.id.lltimeCardNumber,R.id.lllimitedNumberOfCards,R.id.llunlimitedNumber})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.llMemberNumber:
                bundle.putString("from", "会员管理");
               // UIHelper.jump(_activity, ActivityMoneyNotEnough.class, bundle);
                break;
            case R.id.llMoney:
                bundle.putString("from", "会员管理");
                //UIHelper.jump(_activity, ActivityMoneyNotEnough.class, bundle);
                break;
            case R.id.llMoneyNotEnough://余额不足
                bundle.putString("from", "余额不足");
                 UIHelper.jump(_activity, ActivityLackOfBalance.class, bundle);
              //  UIHelper.t(ac, "正在建设中...");
                break;
            case R.id.llCountNotEnough://余次不足
                bundle.putString("from", "余次不足");
                UIHelper.jump(_activity, ActivityOverTimeLessThan.class, bundle);
               // UIHelper.t(ac, "正在建设中...");
                break;
            case R.id.llBeOverdue://即将过期
                bundle.putString("from", "即将过期");
                UIHelper.jump(_activity, ActivityWillExpire.class, bundle);
               // UIHelper.t(ac, "正在建设中...");
                break;
            case R.id.llOverdue://已过期
                bundle.putString("from", "已过期");
                 UIHelper.jump(_activity, ActivityExpire.class, bundle);
                //UIHelper.t(ac, "正在建设中...");
                break;
            case R.id.llPreloadedCards:     //充值卡
                UIHelper.jump(_activity,ActivityPreloadedCardsList.class);
                break;
            case R.id.lltimeCardNumber:     //次卡
                UIHelper.jump(_activity,ActivityTimeCardList.class);
                break;
            case R.id.lllimitedNumberOfCards:     //有限卡
                 UIHelper.jump(_activity,ActivityLimitedOfCardsList.class);
                break;
            case R.id.llunlimitedNumber:     //无限卡
                 UIHelper.jump(_activity,ActivityUnlimitedList.class);
                break;
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showMenu(v);
        }
    };

    private void showMenu(View parent) {
        View contentView = LayoutInflater.from(_activity).inflate(R.layout.spinner_notice, null);
        popupWindow = new PopMemberManagement(_activity, contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOnItemClickListener(this);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 90), Func.Dp2Px(_activity, 5));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void doNext(int positon, String text) {
        popupWindow.dismiss();
        if (positon == 0) {
            UIHelper.jumpForResult(this, ActivityPrepaidPhoneCard.class, 1001);
        } else if (positon == 1) {
            UIHelper.jumpForResult(this, ActivityOpenTimeCard.class, 1001);
        } else if (positon == 2) {
            UIHelper.jump(this, ActivityCardSetting.class);
        } else if (positon == 3) {
            UIHelper.jumpForResult(this, ActivityTopup.class, 1001);
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            MemberManagementBean bean = (MemberManagementBean) res;
            if (bean != null) {
                tvMemberNumber.setText(bean.getData().getMembership() + "");
                timeCardNumber.setText(bean.getData().getTimeCardNumber() + "");
                numberOfPreloadedCards.setText(bean.getData().getNumberOfPreloadedCards() + "");
                limitedNumberOfCards.setText(bean.getData().getLimitedNumberOfCards() + "");
                unlimitedNumber.setText(bean.getData().getUnlimitedNumber() + "");
                amount.setText("¥"+bean.getData().getAmount() + "");
                tvBeOverdue.setText(bean.getData().getWillExpire() + "");
                tvOverdue.setText(bean.getData().getExpired() + "");

                tvMoneyNotEnough.setText(bean.getData().getCardInsufficient() + "");
                tvCountNotEnough.setText(bean.getData().getOverTimeLessThan() + "");

            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            AppUtil.getCarApiClient(ac).selectMemberManagementMemberber(this);
        }
    }
}
