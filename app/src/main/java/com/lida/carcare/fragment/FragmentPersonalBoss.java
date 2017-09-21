package com.lida.carcare.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityInvitation;
import com.lida.carcare.activity.ActivityPositionManage;
import com.lida.carcare.activity.ActivitySetting;
import com.lida.carcare.activity.ActivityStaffManage;
import com.lida.carcare.bean.PersonalDataBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 个人中心-老板
 * Created by WeiQingFeng on 2017/4/28.
 */

public class FragmentPersonalBoss extends BaseFragment
{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    Unbinder unbinder;
    @BindView(R.id.llProjectManage)
    LinearLayout llProjectManage;
    @BindView(R.id.llInvitation)
    LinearLayout llInvitation;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvInvitationCode)
    TextView tvInvitationCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_personal_boss, null);
        unbinder = ButterKnife.bind(this, v);
        topbar.setTitle("个人中心");
        topbar.setRightImageButton(R.drawable.icon_setting, listener);
//        AppUtil.getCarApiClient(ac).personalData(ac.userId, callback);
        tvInvitationCode.setText(ac.shopCode);
        return v;
    }

    BaseApiCallback callback = new BaseApiCallback()
    {
        @Override
        public void onApiSuccess(NetResult res, String tag)
        {
            super.onApiSuccess(res, tag);
            if (res.isOK())
            {
                PersonalDataBean bean = (PersonalDataBean) res;
                String path = bean.getData().getHeadPortrait();
                ac.setImage(ivHead, Constant.BASE + path);
                ac.setProperty("head_img", path);
                tvName.setText(bean.getData().getUsername());
//                tvInvitationCode.setText(bean.getData().getInviteCode());
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        if(ac.user_type.equals("0")) {
            tvName.setText(ac.name + "(老板)");
        }else if(ac.user_type.equals("4")){
            tvName.setText(ac.name + "(店长)");
        }else {
            tvName.setText(ac.name);
        }
        if(ac.getProperty("head_img").contains("upload")){
            ac.setImage(ivHead, Constant.BASE+ac.getProperty("head_img"));//服务器
        }else{
            ivHead.setImageBitmap(BitmapFactory.decodeFile(ac.getProperty("head_img")));
        }
    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            UIHelper.jump(_activity, ActivitySetting.class);
        }
    };

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.llProjectManage, R.id.llInvitation, R.id.tvReadyToCheck, R.id.tvAlreadyPass, R.id.tvAlreadyRefuse})
    public void onViewClicked(View view)
    {
        Bundle bundle = new Bundle();
        switch (view.getId())
        {
            case R.id.tvReadyToCheck:
                bundle.putInt("flag", 0);
                UIHelper.jump(_activity, ActivityStaffManage.class, bundle);
                break;
            case R.id.tvAlreadyPass:
                bundle.putInt("flag", 1);
                UIHelper.jump(_activity, ActivityStaffManage.class, bundle);
                break;
            case R.id.tvAlreadyRefuse:
                bundle.putInt("flag", 2);
                UIHelper.jump(_activity, ActivityStaffManage.class, bundle);
                break;
            case R.id.llProjectManage:
                UIHelper.jump(_activity, ActivityPositionManage.class);
//                UIHelper.jump(_activity, ActivityProjectManege.class);
                break;
            case R.id.llInvitation:
                UIHelper.jump(_activity, ActivityInvitation.class);
                break;
        }
    }
}
