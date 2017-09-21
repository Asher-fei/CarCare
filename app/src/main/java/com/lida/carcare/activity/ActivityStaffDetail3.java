package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.MemberDetailBean;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 员工详情
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityStaffDetail3 extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnCheck)
    Button btnCheck;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.cbStatus)
    CheckBox cbStatus;

    private String userId;//用户编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdetail3);
        ButterKnife.bind(this);
        userId = mBundle.getString("userId");
        topbar.setTitle("员工详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).empManagerDetails(userId, this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            MemberDetailBean bean = (MemberDetailBean) res;
            tvName.setText(bean.getData().getUsername());
            tvPhone.setText(bean.getData().getTsUser().getPhone());
            if(bean.getData().getHeadImg()!=null){
                ac.setImage(ivHead, Constant.BASE+bean.getData().getHeadImg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @OnClick({R.id.btnCancel, R.id.btnCheck})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnCheck:
                Bundle bundle = new Bundle();
                bundle.putString("userId",userId);
                UIHelper.jumpForResult(_activity, ActivityStaffDetail1.class, bundle, 1001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            finish();
        }
    }
}
