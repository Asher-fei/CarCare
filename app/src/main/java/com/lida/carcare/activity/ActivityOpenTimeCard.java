package com.lida.carcare.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CardRestrictCarnoBean;
import com.lida.carcare.camera.RectCameraActivity;
import com.lida.carcare.widget.DialogCardType;
import com.lida.carcare.widget.DialogSelectValidityPeriod;
import com.lida.carcare.widget.EditCarNumber;
import com.lida.carcare.widget.OneFontEdit;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 开次卡
 * Created by Administrator on 2017/6/29.
 */

public class ActivityOpenTimeCard extends BaseActivity implements View.OnFocusChangeListener, View.OnTouchListener {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.onceCardName)
    EditText onceCardName;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.selectCardType)
    TextView selectCardType;
    @BindView(R.id.onceCardNo)
    EditText onceCardNo;
    @BindView(R.id.carNo)
    EditCarNumber carNo;
    @BindView(R.id.keyboard_view)
    KeyboardView mKeyboardView;

    String userId = "";

    CardRestrictCarnoBean bean = null;

    boolean iscarNo = true;
    String cardTypeId = "";
    @BindView(R.id.ivScan)
    ImageView ivScan;
    @BindView(R.id.btnOK)
    Button btnOK;
    @BindView(R.id.selectValidityPeriod)
    TextView selectValidityPeriod;

    private Keyboard province_keyboard;
    private Keyboard number_keyboar;
    private EditText currentEdit;
    private int currentIndex;
    private List<OneFontEdit> fonts;

    private String number = "";

    String expirationMonth = ""; //到期月数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_time_card);
        ButterKnife.bind(this);
        topbar.setTitle("开次卡");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).getOnceCardType(this);
        fonts = carNo.getEts();
        carNo.setDefaultText(number);
        initKeyBoard();
    }

    @OnClick({R.id.btnChooseCustomer, R.id.btnOK, R.id.selectCardType, R.id.ivScan, R.id.selectValidityPeriod})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChooseCustomer:
                Bundle bundle = new Bundle();
                bundle.putString("flag", "ActivityOpenTimeCard");
                UIHelper.jumpForResult(_activity, ActivityChooseCustomer.class, bundle, 1001);
                break;
            case R.id.btnOK:
                save();
                break;
            case R.id.selectCardType:
                if (bean != null) {
                    new DialogCardType(_activity, selectCardType, bean.getData()).show();
                }
                break;
            case R.id.ivScan:
                Bundle bundle1 = new Bundle();
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
                    } else {
                        bundle1.putString("flag", "ActivityOpenTimeCard");
                        UIHelper.jumpForResult(_activity, RectCameraActivity.class, bundle1, 1003);
                    }
                } else {
                    bundle1.putString("flag", "ActivityOpenTimeCard");
                    UIHelper.jumpForResult(_activity, RectCameraActivity.class, bundle1, 1003);
                }

                break;
            case R.id.selectValidityPeriod:
                final DialogSelectValidityPeriod dialog = new DialogSelectValidityPeriod(_activity, selectValidityPeriod);
                dialog.setOnItemClickListener(new DialogSelectValidityPeriod.onItemClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        dialog.dismiss();

                        //次卡有效期  季度-3  半年-6  一年-12  无限期-""

                        if (position == 0) {
                            expirationMonth = "3";
                        } else if (position == 1) {
                            expirationMonth = "6";
                        } else if (position == 2) {
                            expirationMonth = "12";
                        } else if (position == 3) {
                            expirationMonth = "";
                        }
                        LogUtils.d(position);
                    }
                });
                dialog.show();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e(requestCode);
        LogUtils.e(resultCode);
        LogUtils.e(data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {
                LogUtils.e(data);
                onceCardName.setEnabled(false);
                mobile.setEnabled(false);
                userId = data.getStringExtra("userId");
                onceCardName.setText(data.getStringExtra("name"));
                mobile.setText(data.getStringExtra("phone"));
            }
            if (requestCode == 1003) {
                number = data.getStringExtra("number");
                LogUtils.e("number:" + number);
                carNo.setDefaultText(number);
            }
        }
    }

    private void save() {
        if (onceCardName.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请输入客户名称");
            return;
        }
        if (mobile.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请输入手机号");
            return;
        }
        if (selectCardType.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请选择卡种");
            return;
        }
        if (selectValidityPeriod.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请选择有效期");
            return;
        }

        for (int i = 0; i < bean.getData().size(); i++) {
            if (selectCardType.getText().toString().trim().equals(bean.getData().get(i).getCardName())) {
                if (bean.getData().get(i).getCardRestrictCarno().equals("0")) {
                    iscarNo = false;

                }
                cardTypeId = bean.getData().get(i).getId();

            }
        }
        if (iscarNo == true) {
            if (carNo.getNumbers() == null) {
                UIHelper.t(this, "请输入车牌号");
                return;
            }
        }
        if (onceCardNo.getText().toString().trim().length() == 0) {
            UIHelper.t(this, "请输入卡号");
            return;
        }
        LogUtils.d(expirationMonth);
        AppUtil.getCarApiClient(ac).saveOnceCard(onceCardNo.getText().toString().trim(), onceCardName.getText().toString().trim(), mobile.getText().toString().trim(), cardTypeId, userId, carNo.getNumbers(), expirationMonth, this);

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
            if ("getOnceCardType".equals(tag)) {
                bean = (CardRestrictCarnoBean) res;
            }
            if ("saveOnceCard".equals(tag)) {
                UIHelper.t(ac, "操作成功");
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        } else {
            if ("saveOnceCard".equals(tag)) {
                UIHelper.t(this, res.getMsg());
            }
        }
    }


    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    private void initKeyBoard() {
        province_keyboard = new Keyboard(this, R.xml.province_abbreviation);
        number_keyboar = new Keyboard(this, R.xml.number_or_letters);
        mKeyboardView.setKeyboard(province_keyboard);
        mKeyboardView.setEnabled(true);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(listener);
        for (int i = 0; i < fonts.size(); i++) {
            fonts.get(i).setOnFocusChangeListener(this);
            fonts.get(i).setOnTouchListener(this);
        }
        for (int i = 0; i < fonts.size(); i++) {
            final int j = i;
            fonts.get(j).addTextChangedListener(new TextWatcher() {
                private CharSequence temp;
                private int sStart;
                private int sEnd;

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    temp = s;
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    sStart = fonts.get(j).getSelectionStart();
                    sEnd = fonts.get(j).getSelectionEnd();
                    if (temp.length() == 1 && j < fonts.size() - 1) {
                        fonts.get(j + 1).setFocusable(true);
                        fonts.get(j + 1).setFocusableInTouchMode(true);
                        fonts.get(j + 1).requestFocus();
                    }
                    if (temp.length() > 1) {
                        s.delete(0, 1);
                        int tSelection = sStart;
                        fonts.get(j).setText(s);
                        fonts.get(j).setSelection(tSelection);
                        fonts.get(j).setFocusable(true);
                    }
                }
            });
        }
//        fonts.get(0).setFocusable(true);
//        fonts.get(0).requestFocus();
    }


    /**
     * 软键盘展示状态
     */
    public boolean isShow() {
        return mKeyboardView.getVisibility() == View.VISIBLE;
    }

    /**
     * 软键盘隐藏
     */
    public void hideKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            mKeyboardView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 指定切换软键盘 isnumber false表示要切换为省份简称软键盘 true表示要切换为数字软键盘
     */
    public void changeKeyboard(boolean isnumber) {
        if (isnumber) {
            mKeyboardView.setKeyboard(number_keyboar);
        } else {
            mKeyboardView.setKeyboard(province_keyboard);
        }
    }

    /**
     * 软键盘展示
     */
    public void showKeyboard() {
        int visibility = mKeyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 禁掉系统软键盘
     */
    public void hideSoftInputMethod() {

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        int currentVersion = Build.VERSION.SDK_INT;
        String methodName = null;
        if (currentVersion >= 16) {
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        } else if (currentVersion >= 14) {
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }
        if (methodName == null) {
            currentEdit.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName,
                        boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(currentEdit, false);
            } catch (NoSuchMethodException e) {
                currentEdit.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = currentEdit.getText();
            int start = currentEdit.getSelectionStart();
            if (primaryCode == -1) {//
                //隐藏键盘
                if (isShow()) {
                    hideKeyboard();
                }
            } else if (primaryCode == -3) {// 回退
                if (editable != null) {
                    //没有输入内容时软键盘重置为省份简称软键盘
                    editable.clear();
                    if (currentIndex > 0) {
                        currentIndex = currentIndex - 1;
                        fonts.get(currentIndex).setFocusable(true);
                        fonts.get(currentIndex).requestFocus();
                    }
                }
            } else {
                editable.clear();
                editable.insert(0, Character.toString((char) primaryCode));
                if (currentIndex == 6 && fonts.get(6).getText().length() > 0) {
                    hideKeyboard();
                }
            }
        }
    };

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            currentEdit = (EditText) v;
            for (int i = 0; i < fonts.size(); i++) {
                EditText temp = fonts.get(i);
                if (temp == (EditText) v) {
                    currentIndex = i;
                }
            }
            if (currentIndex == 0) {
                changeKeyboard(false);
            } else {
                changeKeyboard(true);
            }
            hideSoftInputMethod();
            if (!isShow()) {
                showKeyboard();
            }
        } else {
            hideKeyboard();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!isShow()) {
            showKeyboard();
        }
        return false;
    }

}
