package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.MemberDetailBean;
import com.lida.carcare.widget.DialogCommission;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 员工详情
 * Created by WeiQingFeng on 2017/5/3.
 */

public class ActivityStaffDetail2 extends BaseActivity
{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvRole)
    TextView tvRole;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.cbStatus)
    CheckBox cbStatus;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.tvScale)
    TextView tvScale;


    private String id;//用户编号
    private String departed = "";//职位编号
    private String status = "";//状态 0禁用1启用
    private String departId = "";// 职位id
    private String roleId = "";// 角色id
    private String scale = "";// 权重

    private DialogCommission dialogCommission;//设置员工提成

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffdetail2);
        ButterKnife.bind(this);
        id = mBundle.getString("id");
        topbar.setTitle("员工详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).empManagerDetails(id, this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnOk.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnOk.setClickable(true);
        if (res.isOK()) {
            if ("empManagerDetails".equals(tag)) {
                MemberDetailBean bean = (MemberDetailBean) res;
                tvName.setText(bean.getData().getUsername());
                tvPhone.setText(bean.getData().getTsUser().getPhone());
                tvRole.setText(bean.getData().getTsRole().getRolename());
                tvScale.setText(bean.getData().getInviteCode());
                List<MemberDetailBean.DataBean.TsDepartsBean> tsDeparts = bean.getData().getTsDeparts();
                StringBuilder servers = new StringBuilder();
                for (int i = 0; i < tsDeparts.size(); i++) {
                    servers.append(tsDeparts.get(i).getDname() + ",");
                }
                tvPosition.setText(servers);
                if ("1".equals(bean.getData().getStatus())) {
                    cbStatus.setChecked(false);
                } else {
                    cbStatus.setChecked(true);
                }
                if (bean.getData().getHeadImg() != null) {
                    ac.setImage(ivHead, Constant.BASE + bean.getData().getHeadImg());
                }
                roleId = bean.getData().getTsRole().getfId();
            }
            if ("updatetempManager".equals(tag))
            {
                UIHelper.t(_activity, "修改成功！");
                finish();
            }
        }else {
            if ("updatetempManager".equals(tag))
            {
                UIHelper.t(_activity, res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnOk.setClickable(true);
    }

    @OnClick({R.id.btnCancel, R.id.btnOk, R.id.tvRole, R.id.tvPosition, R.id.tvScale})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnOk:
                scale = tvScale.getText().toString();
                status = cbStatus.isChecked() ? "0" : "1";
                AppUtil.getCarApiClient(ac).updatetempManager(id, status, departId, roleId, scale, this);
                break;
            case R.id.tvRole:
                UIHelper.jumpForResult(_activity, ActivityRolePermission.class, 1001);
                break;
            case R.id.tvPosition:
                UIHelper.jumpForResult(_activity, ActivityZhiWei.class, 1002);
                break;
            case R.id.tvScale:
                dialogCommission = new DialogCommission(_activity);
                dialogCommission.setOnOkButtonClick(new DialogCommission.onOkButtonClick()
                {
                    @Override
                    public void onOkButtonClicked()
                    {
                        tvScale.setText(dialogCommission.getContent());
                    }
                });
                dialogCommission.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 1001)
            {
                roleId = data.getStringExtra("ids");
                tvRole.setText(data.getStringExtra("names"));
            }
            if (requestCode == 1002)
            {
                departId = data.getStringExtra("ids");
                tvPosition.setText(data.getStringExtra("names"));
            }
        }
    }
}
