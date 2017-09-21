package com.lida.carcare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import com.lida.carcare.bean.GoodDetailBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogAddGoodParams;
import com.lida.carcare.widget.DialogIfExit;
import com.lida.carcare.widget.InnerGridView;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑商品
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityEditGoods extends TakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    InnerListView listView;
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
    @BindView(R.id.gridView)
    InnerGridView gridView;
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
    private String id;//商品主键id;


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
        id = getIntent().getExtras().getString("userId");
        ButterKnife.bind(this);
        topbar.setTitle("编辑商品");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        adapterPic = new AdapterPic(this, pics);
        gridView.setAdapter(adapterPic);
        AppUtil.getCarApiClient(ac).findProduct(id, callback);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                if (checkedId == R.id.cb1)
                {
                    etCount.setEnabled(false);
                } else
                {
                    etCount.setEnabled(true);
                }
            }
        });
    }

    @OnClick({R.id.tvClass, R.id.btnAddParams, R.id.btnSave})
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
                String serviceImg = "";
                String safetyInventory = "";
                if(pics.size()>0){
                    serviceImg = pics.get(0);
                }
                if(serviceImg!=null&&serviceImg.contains(Constant.BASE)){
                    serviceImg = null;
                }
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


                if ("".equals(internationalCode) || "".equals(name) || "".equals(priceStandardSell)
                        || "".equals(type) || "".equals(drawType) || "".equals(drawPrice) ) {
                    UIHelper.t(this, "参数填写不完整！");
                    return;
                }
                if("".equals(serviceImg)){
                    UIHelper.t(this, "请上传图片！");
                    return;
                }
                AppUtil.getCarApiClient(ac).updateProduct(id, internationalCode, name, brand, priceStandardSell, type,
                        sSizeName, sSizeParm, drawType, drawPrice, remark,serviceImg, productType,safetyInventory, callback);
                break;
        }
    }

    BaseApiCallback callback = new BaseApiCallback() {
        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            if (res.isOK()) {
                if ("updateProduct".equals(tag)) {
                    UIHelper.t(_activity, "商品修改成功！");
                    setResult(RESULT_OK);
                    finish();
                }
                if ("findProduct".equals(tag)) {
                    GoodDetailBean bean = (GoodDetailBean) res;
                    if(bean!=null&&bean.getData()!=null) {
                        etCode.setText(bean.getData().getInternationalCode());
                        etName.setText(bean.getData().getName());
                        tvClass.setText(bean.getData().getTypeName());
                        etClass.setText(bean.getData().getBrand());
                        etPrice.setText(bean.getData().getPricePlatform());
                        etRemark.setText(bean.getData().getRemark());
                        etCount.setText(bean.getData().getSafetyInventory());
                        if ("0".equals(bean.getData().getType())) {
                            cb1.setChecked(true);
                            cb2.setChecked(false);
                        } else {
                            cb2.setChecked(true);
                            cb1.setChecked(false);
                        }
                        if ("0".equals(bean.getData().getDrawType())) {
                            rbPercentage.setChecked(true);
                            rbMoney.setChecked(false);
                        } else if ("1".equals(bean.getData().getDrawType())) {
                            rbMoney.setChecked(true);
                            rbPercentage.setChecked(false);
                        }
                        etCommission.setText(bean.getData().getDrawPrice());
                        names = Arrays.asList(bean.getData().getSizeName().split(","));
                        params = Arrays.asList(bean.getData().getSizeParem().split(","));
                        productType = bean.getData().getProductType();
                        adapterGoodParams = new AdapterGoodParams(_activity, names, params);
                        listView.setDivider(null);
                        listView.setAdapter(adapterGoodParams);
                        if (bean.getData().getProductImg() != null) {
                            pics.add(Constant.BASE + bean.getData().getProductImg());
                        }
                        adapterPic.notifyDataSetChanged();
                    }
                }
            }else {
                if("updateProduct".equals(tag)){
                    UIHelper.t(_activity,res.getMsg());
                }
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
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
            LogUtils.e(result);
            pics.clear();
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
