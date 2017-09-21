package com.lida.carcare.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivitySetting;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseFragment;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 个人中心-员工
 * Created by Administrator on 2017/6/13.
 */

public class FragmentPersonalWorker extends BaseFragment
{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_personal_worker, null);
        unbinder = ButterKnife.bind(this, view);
        topbar.setTitle("个人中心");
        topbar.setRightImageButton(R.drawable.icon_setting, listener);
        if(ac.user_type.equals("6")){
            tvName.setText(ac.name + "(仓库管理员)");
        }else {
            tvName.setText(ac.name + "(员工)");
        }
        tvPhone.setText(ac.phone);
        return view;
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
    public void onResume() {
        super.onResume();
        if(ac.getProperty("head_img").contains("upload")){
            ac.setImage(ivHead, Constant.BASE + ac.getProperty("head_img"));//服务器
        }else{
            ivHead.setImageBitmap(BitmapFactory.decodeFile(ac.getProperty("head_img")));
        }
        if(ac.user_type.equals("6")){
            tvName.setText(ac.name + "(仓库管理员)");
        }else {
            tvName.setText(ac.name + "(员工)");
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }
}
