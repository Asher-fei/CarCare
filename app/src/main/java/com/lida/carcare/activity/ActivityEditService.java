package com.lida.carcare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterPic;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceDetailBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogIfExit;
import com.lida.carcare.widget.InnerGridView;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改服务
 * Created by WeiQingFeng on 2017/4/17.
 */

public class ActivityEditService extends TakePhotoActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.gridView)
    InnerGridView gridView;
    @BindView(R.id.tvChooseClass)
    TextView tvChooseClass;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.rbMoney)
    RadioButton rbMoney;
    @BindView(R.id.rbPercentage)
    RadioButton rbPercentage;
    @BindView(R.id.etCommission)
    EditText etCommission;

    private List<String> pics = new ArrayList<>();
    private AdapterPic adapterPic;
    private DialogIfExit dialogIfExit;

    private String serviceType = "";//返回的分类id

    private AppContext ac;
    private Activity _activity;
    private String id;//服务主键id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addservice);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(com.bishilai.thirdpackage.R.color.colorPrimary));
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        }
        id = getIntent().getExtras().getString("userId");
        ac = (AppContext) getApplication();
        _activity = this;
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topbar.setTitle("编辑服务");
        topbar.setBackgroundColor(getResources().getColor(R.color.topbar));
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        adapterPic = new AdapterPic(this, pics);
        gridView.setAdapter(adapterPic);
        AppUtil.getCarApiClient(ac).findService(getIntent().getExtras().getString("userId"), callback);
    }

    @OnClick({R.id.tvChooseClass, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvChooseClass:
                Bundle bundle = new Bundle();
                bundle.putString("type", "choose");
                UIHelper.jumpForResult(this, ActivityServiceClassSetting.class, bundle, 1001);
                break;
            case R.id.btnSave:
                String name = etName.getText().toString();
                String drawType = "";
                String drawPrice = etCommission.getText().toString();
                String servicePrice = etPrice.getText().toString();
                String remark = etDes.getText().toString();
                String serviceImg = null;
                if(pics.size()>0){
                    serviceImg = pics.get(0);
                }
                if(serviceImg!=null&&serviceImg.contains(Constant.BASE)){
                    serviceImg = null;
                }
                if(rbMoney.isChecked()){
                    drawType = "1";
                }else{
                    drawType = "0";
                }
                if ("".equals(name) || "".equals(serviceType) || "".equals(drawType) || "".equals(drawPrice) ||
                        "".equals(servicePrice)) {
                    UIHelper.t(_activity,"参数填写不完整！");
                    return;
                }
               /* if("".equals(serviceImg)){
                    UIHelper.t(this, "请上传图片！");
                    return;
                }*/
                LogUtils.e(serviceImg);
                AppUtil.getCarApiClient(ac).updateSerive(id, name,serviceType,drawType,drawPrice,servicePrice,
                        remark,serviceImg,callback);
                break;
        }
    }

    BaseApiCallback callback = new BaseApiCallback(){
        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            if(res.isOK()){
                if("findService".equals(tag)){
                    ServiceDetailBean bean = (ServiceDetailBean) res;
                    if(bean!=null&&bean.getData()!=null) {
                        etName.setText(bean.getData().getName());
                        tvChooseClass.setText(bean.getData().getTypeName());
                        etPrice.setText(bean.getData().getServicePrice());
                        if ("0".equals(bean.getData().getDrawType())) {
                            rbPercentage.setChecked(true);
                            rbMoney.setChecked(false);
                        } else if ("1".equals(bean.getData().getDrawType())) {
                            rbMoney.setChecked(true);
                            rbPercentage.setChecked(false);
                        }
                        etCommission.setText(bean.getData().getDrawPrice());
                        etDes.setText(bean.getData().getRemark());
                        serviceType = bean.getData().getServiceType();
                        if (bean.getData().getServiceImg() != null) {
                            pics.add(Constant.BASE + bean.getData().getServiceImg());
                        }
                        adapterPic.notifyDataSetChanged();
                    }
                }
                if("updateSerive".equals(tag)){
                    UIHelper.t(_activity,"服务修改成功！");
                    setResult(RESULT_OK);
                    finish();
                }
            }else {
                if("updateSerive".equals(tag)){
                    UIHelper.t(_activity,res.getMsg());
                }
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                tvChooseClass.setText(data.getExtras().getString("class"));
                serviceType = data.getExtras().getString("code");
            }
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
            pics.add(result.getImage().getCompressPath());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapterPic.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        LogUtils.e(result);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
                if (dialogIfExit == null) {
                    dialogIfExit = new DialogIfExit(this);
                    dialogIfExit.show();
                } else {
                    if (dialogIfExit.isShowing()) {
                        dialogIfExit.dismiss();
                    } else {
                        dialogIfExit.show();
                    }
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
