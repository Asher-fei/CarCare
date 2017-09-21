package com.lida.carcare.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceDetailBean;
import com.lida.carcare.widget.DialogIfDelete;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服务详情
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityServiceDetail extends BaseActivity implements DialogIfDelete.onOkButtonClick {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvClass)
    TextView tvClass;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.ivTop)
    ImageView ivTop;
    private PopupWindow popupWindow;
    private DialogIfDelete dialog;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicedetail);
        ButterKnife.bind(this);
//        tvClass.setText("分类：" + ActivityServiceList.title);
        id = mBundle.getString("userId");
        topbar.setTitle("服务详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("编辑", listener);
        dialog = new DialogIfDelete(_activity);
        dialog.setOnOkButtonClick(this);
        AppUtil.getCarApiClient(ac).findService(id, this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if("findService".equals(tag)){
                ServiceDetailBean bean = (ServiceDetailBean) res;
                if(bean.getData()!=null){
                    tvClass.setText("分类：" + bean.getData().getTypeName());
                    tvName.setText(bean.getData().getName());
                    tvPrice.setText("￥" + bean.getData().getServicePrice());
                    tvDes.setText(bean.getData().getRemark());
                    ac.setImage(ivTop, Constant.BASE + bean.getData().getServiceImg());
                }else {
                    tvClass.setText("分类：");
                    tvName.setText("");
                    tvPrice.setText("￥" );
                    tvDes.setText("");
                }
            }
            if("deleteService".equals(tag)){
                dialog.dismiss();
                UIHelper.t(_activity, "删除成功！");
                setResult(RESULT_OK);
                finish();
            }
        }else {
            if("deleteService".equals(tag)){
                dialog.dismiss();
                UIHelper.t(_activity, res.getMsg());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            AppUtil.getCarApiClient(ac).findService(id, this);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("userId",id);
            UIHelper.jumpForResult(_activity,ActivityEditService.class,bundle,1001);
//            switch (v.getId()) {
//                case R.id.right_ib:
////                    View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_serviceedit, null);
////                    view.findViewById(R.id.tvEdit).setOnClickListener(listener);
////                    view.findViewById(R.id.tvDel).setOnClickListener(listener);
////                    showMenu(topbar.getRight_ib(), view);
//                    break;
//                case R.id.tvEdit:
//                    Bundle bundle = new Bundle();
////                    bundle.putString("name", tvName.getText().toString().trim());
////                    bundle.putString("class", tvClass.getText().toString().trim());
////                    bundle.putString("price", tvPrice.getText().toString().trim());
////                    bundle.putString("des", tvDes.getText().toString().trim());
////                    UIHelper.jump(_activity, ActivityAddService.class, bundle);
//                    bundle.putString("userId",id);
//                    UIHelper.jumpForResult(_activity,ActivityEditService.class,bundle,1001);
//                    popupWindow.dismiss();
//                    break;
//                case R.id.tvDel:
//                    dialog.show();
//                    popupWindow.dismiss();
//                    break;

        }
    };

    private void showMenu(View parent, View contentView) {
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 80), Func.Dp2Px(_activity, 5));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void delete() {
        AppUtil.getCarApiClient(ac).deleteService(id, this);
    }
}
