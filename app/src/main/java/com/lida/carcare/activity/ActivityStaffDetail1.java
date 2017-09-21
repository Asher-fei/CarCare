package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearSmoothScroller;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterStaffratio;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.MemberDetailBean;
import com.lida.carcare.bean.StaffratioBean;
import com.lida.carcare.widget.DialogCommission;
import com.lida.carcare.widget.InnerListView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 员工详情
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityStaffDetail1 extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvRolePermission)
    TextView tvRolePermission;
    @BindView(R.id.tvZhiWei)
    TextView tvZhiWei;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.tvScale)
    TextView tvScale;
    @BindView(R.id.btnPass)
    Button btnPass;
    @BindView(R.id.innerListView)
    InnerListView innerListView;

    private String userId;//用户编号
    private String ids = "";//权限编号
    private String departed = "";//职位编号
    private String status = "0"; //0未激活；1激活
    private DialogCommission dialogCommission;//设置员工提成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdetail);
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
        btnPass.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnPass.setClickable(true);
        if (res.isOK()) {
            if ("empManagerDetails".equals(tag)) {
                MemberDetailBean bean = (MemberDetailBean) res;
                tvName.setText(bean.getData().getUsername());
                tvPhone.setText(bean.getData().getTsUser().getPhone());
                if (bean.getData().getHeadImg() != null) {
                    ac.setImage(ivHead, Constant.BASE + bean.getData().getHeadImg());
                }
            }
            if ("updateEmpInfo".equals(tag)) {
                UIHelper.t(_activity, res.getMsg());
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnPass.setClickable(true);
    }

    @OnClick({R.id.tvRolePermission, R.id.tvZhiWei, R.id.btnRefuse, R.id.btnPass, R.id.tvScale})
    public void onViewClicked(View view) {
        if (checkBox.isChecked()) {
            status = "0";
        } else {
            status = "1";
        }
        String scale = tvScale.getText().toString();
        switch (view.getId()) {
            case R.id.tvRolePermission:
                UIHelper.jumpForResult(_activity, ActivityRolePermission.class, 1001);
                break;
            case R.id.tvZhiWei:
                UIHelper.jumpForResult(_activity, ActivityZhiWei.class, 1002);
                break;
            case R.id.btnRefuse:
                AppUtil.getCarApiClient(ac).updateEmpInfo(ids, departed, status, userId, "2", scale, this);
                break;
            case R.id.btnPass:
                if ("".equals(ids) || "".equals(departed)) {
                    UIHelper.t(_activity, "请为用户分配角色权限和职位！");
                    return;
                }
                if ("".equals(scale)) {
                    UIHelper.t(_activity, "请为用户设置提成权重！");
                    return;
                }
                AppUtil.getCarApiClient(ac).updateEmpInfo(ids, departed, status, userId, "1", scale, this);
                break;
            case R.id.tvScale:
                dialogCommission = new DialogCommission(_activity);
                dialogCommission.setOnOkButtonClick(new DialogCommission.onOkButtonClick() {
                    @Override
                    public void onOkButtonClicked() {
                        tvScale.setText(dialogCommission.getContent());
                    }
                });
                dialogCommission.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                ids = data.getStringExtra("ids");
                tvRolePermission.setText(data.getStringExtra("names"));
            }
            if (requestCode == 1002) {
                departed = data.getStringExtra("ids");
                tvZhiWei.setText(data.getStringExtra("names"));

               /* if(departed!=null&&data.getStringExtra("names")!=null){
                    String[] zwIds = departed.split(",");
                    String[] zwNames = data.getStringExtra("names").split(",");
                    List<StaffratioBean> list = new ArrayList<>();
                    for(int i=0;i<zwIds.length;i++){
                        list.add(new StaffratioBean(zwNames[i],""));
                    }

                        innerListView.setAdapter(new AdapterStaffratio(list,_activity));

                }*/
            }
        }
    }
}
