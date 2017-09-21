package com.lida.carcare.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.widget.DialogCall;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupMemberType;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 会员详情
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityMemberDetail extends BaseActivity implements OnItemClickListener {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llCustomer)
    LinearLayout llCustomer;
    @BindView(R.id.view)
    View view;

    private PopupMemberType popupWindow;
    private ImageButton right_ib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetail);
        ButterKnife.bind(this);
        topbar.setTitle("会员详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
        right_ib = topbar.getRight_ib();
    }

    private void showMenu(View parent, View contentView) {
        popupWindow = new PopupMemberType(_activity,contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 110), Func.Dp2Px(_activity, 5));
        popupWindow.setOnItemClickListener(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_member, null);
            showMenu(v,view);
        }
    };

    @OnClick({R.id.tvPhone, R.id.llCustomer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvPhone:
                String phone = tvPhone.getText().toString().trim();
                new DialogCall(_activity, phone).show();
                break;
            case R.id.llCustomer:
                break;
        }
    }

    @Override
    public void doNext(int positon, String text) {
        popupWindow.dismiss();
        if(positon==0){
            UIHelper.jump(_activity,ActivityRecharge.class);
        }else if(positon==1){
            UIHelper.jump(_activity,ActivityRecordDetail.class);
        }else if(positon==2){
            UIHelper.jump(_activity,ActivityModifyItem.class);
        }else if(positon==3){
            UIHelper.jump(_activity,ActivityModifyBalance.class);
        }else if(positon==4){
            UIHelper.jump(_activity,ActivityProjectExtension.class);
        }else if(positon==5){
            UIHelper.jump(_activity,ActivityModifyCard.class);
        }else if(positon==7){
            UIHelper.jump(_activity,ActivityCardInvalid.class);
        }
    }
}
