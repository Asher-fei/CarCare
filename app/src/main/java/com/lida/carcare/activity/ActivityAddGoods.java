package com.lida.carcare.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.jaeger.library.StatusBarUtil;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterGoodParams;
import com.lida.carcare.adapter.AdapterPic;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogAddGoodParams;
import com.lida.carcare.widget.DialogIfExit;
import com.lida.carcare.widget.InnerGridView;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.camera.CameraManager;

import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增商品
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityAddGoods extends TakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.gridView)
    InnerGridView gridView;
    @BindView(R.id.tvClass)
    TextView tvClass;
    @BindView(R.id.btnAddParams)
    Button btnAddParams;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etClass)
    EditText etClass;
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.cb1)
    RadioButton cb1;
    @BindView(R.id.cb2)
    RadioButton cb2;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.rbMoney)
    RadioButton rbMoney;
    @BindView(R.id.rbPercentage)
    RadioButton rbPercentage;
    @BindView(R.id.etCommission)
    EditText etCommission;
    @BindView(R.id.etCount)
    EditText etCount;

    private DialogIfExit dialogIfExit;

    private List<String> names = new ArrayList<>();
    private List<String> params = new ArrayList<>();
    private AdapterGoodParams adapterGoodParams;

    private List<String> pics = new ArrayList<>();
    private AdapterPic adapterPic;

    private AppContext ac;
    private Activity _activity;
    private String productType = "";//选择的商品分类code;

    private CaptureFragment captureFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoods);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            StatusBarUtil.setColor(this, getResources().getColor(com.bishilai.thirdpackage.R.color.colorPrimary));
            StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        }
        ac = (AppContext) getApplication();
        _activity = this;
        ButterKnife.bind(this);
        topbar.setTitle("新增商品");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        adapterGoodParams = new AdapterGoodParams(this, names, params);
        listView.setDivider(null);
        listView.setAdapter(adapterGoodParams);
        adapterPic = new AdapterPic(this, pics);
        gridView.setAdapter(adapterPic);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.cb1) {
                    etCount.setEnabled(false);
                } else {
                    etCount.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.tvClass, R.id.btnAddParams, R.id.btnSave, R.id.ivScan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvClass:
                Bundle bundle = new Bundle();
                bundle.putString("type", "choose");
                UIHelper.jumpForResult(this, ActivityGoodsClassSetting.class, bundle, 1001);
                break;
            case R.id.btnAddParams:
                new DialogAddGoodParams(this, names, params, adapterGoodParams).show();
                break;
            case R.id.btnSave:
                String internationalCode = etCode.getText().toString();
                String name = etName.getText().toString();
                String brand = etClass.getText().toString();
                String priceStandardSell = etPrice.getText().toString();
                String type = "";
                StringBuilder sizeName = new StringBuilder();
                String sSizeName = "";
                StringBuilder sizeParm = new StringBuilder();
                String sSizeParm = "";
                String drawType = "";
                String drawPrice = etCommission.getText().toString();
                String remark = etRemark.getText().toString();
                String productImg = pics.size() > 0 ? pics.get(0) : "";
                String safetyInventory = "";
                if (cb1.isChecked()) {
                    type = "0";
                    safetyInventory = "";
                } else if (cb2.isChecked()) {
                    type = "1";
                    safetyInventory = etCount.getText().toString();
                }
                if (rbPercentage.isChecked()) {
                    drawType = "0";
                } else if (rbMoney.isChecked()) {
                    drawType = "1";
                }
                if (names.size() > 0) {
                    for (int i = 0; i < names.size(); i++) {
                        sizeName.append(names.get(i) + ",");
                    }
                    sSizeName = sizeName.substring(0, sizeName.length() - 1);
                }
                if (params.size() > 0) {
                    for (int i = 0; i < params.size(); i++) {
                        sizeParm.append(params.get(i) + ",");
                    }
                    sSizeParm = sizeParm.substring(0, sizeParm.length() - 1);
                }

                /*if ("".equals(internationalCode) || "".equals(name) || "".equals(brand) || "".equals(priceStandardSell)
                        || "".equals(type) || "".equals(drawType) || "".equals(drawPrice) || "".equals(productImg)
                        || "".equals(remark)) {
                    UIHelper.t(this, "参数填写不完整！");
                    return;
                }*/
                if ("".equals(internationalCode) || "".equals(name) || "".equals(productType) || "".equals(priceStandardSell)
                         || "".equals(drawType)|| "".equals(drawPrice)||"".equals(productImg)) {
                    UIHelper.t(this, "参数填写不完整！");
                    return;
                }
                AppUtil.getCarApiClient(ac).saveProduct(internationalCode, name, productType, brand, priceStandardSell, type,
                        sSizeName, sSizeParm, drawType, drawPrice, remark, productImg, ac.shopId, safetyInventory, callback);
                break;
            case R.id.ivScan:
                if(Build.VERSION.SDK_INT >= 23){
                    if (ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            ) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1007);
                    } else {
                        UIHelper.jumpForResult(_activity, CaptureActivity.class, 1008);
                    }
                }else{
                    UIHelper.jumpForResult(_activity, CaptureActivity.class, 1008);
                }

                break;
        }
    }

    BaseApiCallback callback = new BaseApiCallback() {
        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            if (res.isOK()) {
                UIHelper.t(_activity, "商品添加成功！");
                setResult(RESULT_OK);
                finish();
            } else {
                UIHelper.t(_activity, res.getMsg());
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtils.e(requestCode);
        LogUtils.e(resultCode);
        LogUtils.e(data);
        if (requestCode == 1003 || requestCode == 1006) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                tvClass.setText(data.getExtras().getString("class"));
                productType = data.getExtras().getString("productType");
            }
        }

        //二维码扫描结果返回
        if (requestCode == 1008 && resultCode == RESULT_OK) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    etCode.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    UIHelper.t(_activity, "扫描解析失败");
                }

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
