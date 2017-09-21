package com.lida.carcare.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterAddCustomProject;
import com.lida.carcare.adapter.AdapterAddcarSearchService;
import com.lida.carcare.adapter.AdapterHotService;
import com.lida.carcare.adapter.AdapterProject;
import com.lida.carcare.adapter.AdapterSmartSearch;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.AddCustomProjectBean;
import com.lida.carcare.bean.CarAndDocumentBean;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.bean.GetCarMainTainBean;
import com.lida.carcare.bean.ServerHotBean;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceCustomResultBean;
import com.lida.carcare.bean.ServiceEditBean;
import com.lida.carcare.bean.ServiceSearchResultBean;
import com.lida.carcare.camera.RectCameraActivity;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.CarClassDialog;
import com.lida.carcare.widget.DialogCarAndDocument;
import com.lida.carcare.widget.DialogIfExit;
import com.lida.carcare.widget.DialogQuickSetting;
import com.lida.carcare.widget.EditCarNumber;
import com.lida.carcare.widget.InnerGridView;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.NumberWidgetBig;
import com.lida.carcare.widget.OneFontEdit;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupOilType;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加新车
 * Created by Administrator on 2017/4/5.
 */

public class ActivityAddCar extends BaseActivity implements OnItemClickListener, OnSureLisener, View.OnFocusChangeListener, View.OnTouchListener {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.btnChooseCustomer)
    TextView btnChooseCustomer;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvChooseCar)
    TextView tvChooseCar;
    @BindView(R.id.tvChooseTime)
    TextView tvChooseTime;
    @BindView(R.id.rbMan)
    RadioButton rbMan;
    @BindView(R.id.rbWomen)
    RadioButton rbWomen;
    @BindView(R.id.tvOil)
    TextView tvOil;
    @BindView(R.id.tvQuickSetting)
    TextView tvQuickSetting;
    @BindView(R.id.etCarNumber)
    EditCarNumber etCarNumber;
    @BindView(R.id.etCarId)
    EditText etCarId;
    @BindView(R.id.keyboard_view)
    KeyboardView mKeyboardView;
    @BindView(R.id.gvSmart)
    InnerGridView gvSmart;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etMile)
    EditText etMile;//输入里程数
    @BindView(R.id.rbSex)
    RadioGroup rbSex;
    @BindView(R.id.cbWait)
    CheckBox cbWait;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.btnReceive)
    Button btnReceive;
    @BindView(R.id.etNickName)
    EditText etNickName;


    @BindView(R.id.gridViewHotService)
    InnerGridView gridViewHotService;

    public static TextView tvPrice;
    public static EditText etUpdatePrice;
    public static TextView tvCount;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.gvSearchService)
    InnerGridView gvSearchService;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.btnAddCustomProject)
    Button btnAddCustomProject;
    @BindView(R.id.lvCustomProject)
    InnerListView lvCustomProject;
    @BindView(R.id.listViewPrepaidPhone)
    InnerListView listViewPrepaidPhone;
    @BindView(R.id.listViewTimeCard)
    InnerListView listViewTimeCard;


    private PopupOilType pop;//油箱剩余数
    private DialogIfExit dialogIfExit;
    private SimpleDateFormat simpleDateFormat;
    private Keyboard province_keyboard;
    private Keyboard number_keyboar;
    private EditText currentEdit;
    private int currentIndex;
    private List<OneFontEdit> fonts;

    private String number = "";
    private AdapterSmartSearch adapterSmartSearch;//根据公里数智能搜索
    private CarClassDialog carClassDialog;//车型选择；

    private String customerName = "";
    private String sex = "";
    private String mobile = "";
    private String customerId = "";
    private final int CUSTOMERORDER = 1001;//选择客户
    private final int CHOOSECUSTOMER = 1002;//车主嘱咐

    private AdapterProject adapterProject;
    private ServiceBean bean = new ServiceBean();

    public static StringBuilder goodsProject = new StringBuilder();//美容
    public static StringBuilder maintainProject = new StringBuilder();//保养
    public static StringBuilder repairProject = new StringBuilder();//维修
    public static StringBuilder refitProject = new StringBuilder();//换装

    public static List<ServiceEditBean> serviceEditData = new ArrayList<>();

    public static InnerListView innerListViewService;
    public static ServiceEditAdapter serviceEditAdapter;
    public static Activity context;


    private List<AddCustomProjectBean> customProjectList = new ArrayList<>();
    private AdapterAddCustomProject adapterAddCustomProject = null;


    private String ids = "";
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
        ButterKnife.bind(this);
        innerListViewService = (InnerListView) findViewById(R.id.innerListViewService);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvCount = (TextView) findViewById(R.id.tvCount);
        etUpdatePrice = (EditText) findViewById(R.id.etUpdatePrice);
        context = _activity;
        etUpdatePrice.setEnabled(true);
        try {
            number = mBundle.getString("number");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtils.e(number);
        initView();
        String[] items = {"美容", "保养", "维修", "改装"};
        String[] codes = {"A01", "A02", "A03", "A04"};
        List<ServiceBean.DataBean> temp = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ServiceBean.DataBean bean = new ServiceBean.DataBean();
            bean.setName(items[i]);
            bean.setCode(codes[i]);
            temp.add(bean);
        }
        bean.setData(temp);
        innerListViewService.setDividerHeight(10);
        adapterProject = new AdapterProject(_activity, bean);
        listView.setAdapter(adapterProject);
        etName.requestFocus();
        serviceEditAdapter = new ServiceEditAdapter();
        AppUtil.getCarApiClient(ac).selectJfomCategory(apiCallback);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    AppUtil.getCarApiClient(ac).findServiceByName(etSearch.getText().toString(), apiCallback);
                }
                return false;
            }
        });

       /* etUpdatePrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (serviceEditAdapter != null) {
                        serviceEditAdapter.setPriceEnable(false);
                    }
                }
            }
        });

        etUpdatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etUpdatePrice.isEnabled()){
                    UIHelper.t(_activity,"单价与总价只能点击修改一项");
                }
            }
        });*/
        adapterAddCustomProject = new AdapterAddCustomProject(_activity, customProjectList);
        lvCustomProject.setAdapter(adapterAddCustomProject);

    }

    private void initView() {
        topbar.setTitle("添加新车");
        topbar.setBackgroundColor(getResources().getColor(R.color.topbar));
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        Date date = new Date();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tvChooseTime.setText(simpleDateFormat.format(date));
        fonts = etCarNumber.getEts();
        etMile.setOnEditorActionListener(onEditorActionListener);
        etCarNumber.setDefaultText(number);
        initPop();
        initKeyBoard();
    }

    /**
     * 输入里程数搜索项目
     */
    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    AppUtil.getCarApiClient(ac).getCarMaintain(etMile.getText().toString(), apiCallback);
                }
            }
            return false;
        }
    };

    /**
     * 网络请求回调
     */
    ApiCallback apiCallback = new BaseApiCallback() {
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
                if ("getCarMaintain".equals(tag)) {
                    GetCarMainTainBean bean = (GetCarMainTainBean) res;
                    if (bean.getData() != null) {
                        adapterSmartSearch = new AdapterSmartSearch(_activity, bean);
                        gvSmart.setAdapter(adapterSmartSearch);
                    }
                }
                if ("getCarType".equals(tag)) {
                    CarTypeBean bean = (CarTypeBean) res;
                    if (bean.getData() != null) {
                        carClassDialog = new CarClassDialog(_activity, tvChooseCar, bean.getData());
                        carClassDialog.show();
                    }
                }
                if ("selectJfomCategory".equals(tag)) {
                    ServerHotBean bean = (ServerHotBean) res;
                    if (bean != null) {
                        if (bean.getData() != null) {
                            if (bean.getData().size() > 0) {
                                addHotService(bean.getData());
                            }
                        }
                    }
                }
                if ("selectCarAndDocumentByCarNo".equals(tag)) {
                    final CarAndDocumentBean bean = (CarAndDocumentBean) res;
                    if (bean != null && bean.getData() != null && bean.getData().size() > 0) {
                        final DialogCarAndDocument dialog = new DialogCarAndDocument(_activity, bean.getData());
                        dialog.setOnItemClickListener(new DialogCarAndDocument.onItemClickListener() {
                            @Override
                            public void onItemClicked(int position) {
                                dialog.dismiss();
                                if (bean.getData().get(position).getCustomer() != null) {
                                    customerId = bean.getData().get(position).getCustomer().getId() == null ? "" : bean.getData().get(position).getCustomer().getId();
                                    etName.setText(bean.getData().get(position).getCustomer().getCustomerName() == null ? "" : bean.getData().get(position).getCustomer().getCustomerName());
                                    etPhone.setText(bean.getData().get(position).getCustomer().getMobilePhoneNo() == null ? "" : bean.getData().get(position).getCustomer().getMobilePhoneNo());
                                    etNickName.setText(bean.getData().get(position).getCustomer().getNickname() == null ? "" : bean.getData().get(position).getCustomer().getNickname());
                                    String sex = bean.getData().get(position).getCustomer().getSex() == null ? "" : bean.getData().get(position).getCustomer().getSex();
                                    if ("0".equals(sex)) {
                                        rbMan.setChecked(true);
                                    } else {
                                        rbWomen.setChecked(true);
                                    }
                                }
                            }
                        });
                        dialog.show();
                    }
                }
                if ("findServiceByName".equals(tag)) {
                    ServiceSearchResultBean bean = (ServiceSearchResultBean) res;
                    if (bean != null && bean.getData() != null) {
                        AdapterAddcarSearchService adapter = new AdapterAddcarSearchService(bean.getData(), _activity);
                        gvSearchService.setAdapter(adapter);
                    }
                }
            } else {
                ac.handleErrorCode(_activity, res.getMsg());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }
    };

    private void initKeyBoard() {
        province_keyboard = new Keyboard(_activity, R.xml.province_abbreviation);
        number_keyboar = new Keyboard(_activity, R.xml.number_or_letters);
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

    private void initPop() {
        View view = LayoutInflater.from(_activity).inflate(R.layout.layout_spinner, null);
        pop = new PopupOilType(_activity, view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setOnItemClickListener(this);
    }

    @OnClick({R.id.btnChooseCustomer, R.id.tvOrder, R.id.tvChooseCar, R.id.tvChooseTime, R.id.tvOil,
            R.id.tvQuickSetting, R.id.btnReceive, R.id.btnSearch, R.id.ivScan, R.id.clear, R.id.btnAddCustomProject})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tvChooseCar:
                if (carClassDialog == null) {
                    AppUtil.getCarApiClient(ac).getCarType(apiCallback);
                } else {
                    carClassDialog.show();
                }
                break;
            case R.id.btnChooseCustomer:
                bundle.putString("flag", "ActivityAddCar");
                UIHelper.jumpForResult(_activity, ActivityChooseCustomer.class, bundle, CHOOSECUSTOMER);
                break;
            case R.id.tvOrder:
                UIHelper.jumpForResult(_activity, ActivityCustomerOrder.class, CUSTOMERORDER);
                break;
            case R.id.tvChooseTime:
                initTimeDialog();
                break;
            case R.id.tvOil:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    pop.showAsDropDown(tvOil);
                }
                break;
            case R.id.tvQuickSetting:
                new DialogQuickSetting(_activity, tvChooseTime).show();
                break;
            case R.id.btnReceive:
                btnReceive.setFocusable(true);
                btnReceive.setFocusableInTouchMode(true);
                btnReceive.requestFocus();
                String carNo = etCarNumber.getNumbers();
                String carFrameNo = etCarId.getText().toString();
                String brandId = (String) tvChooseCar.getTag();
                customerName = etName.getText().toString();
                mobile = etPhone.getText().toString();
                String isWait;
                String mileage = etMile.getText().toString();
                String oiltable = tvOil.getText().toString();
                String deliveryTime = tvChooseTime.getText().toString();
                String registerTime = "";
                String remark = tvOrder.getText().toString();
                String commercialTime = "";
                String userName = ac.userId;
                String compulsoryTime = "";
                String sGoodsProject = goodsProject.toString();
                String sMaintainProject = maintainProject.toString();
                String sRepairProject = repairProject.toString();
                String sRefitProject = refitProject.toString();
                String sNickName = etNickName.getText().toString();
                if (rbSex.getCheckedRadioButtonId() == R.id.rbMan) {
                    sex = "0";
                } else {
                    sex = "1";
                }
                if (cbWait.isChecked()) {
                    isWait = "1";
                } else {
                    isWait = "0";
                }
//                || "".equals(carFrameNo) || "".equals(brandId) || "".equals(customerName) || "".equals(sex)
//                    || "".equals(mobile) || "".equals(customerId) || "".equals(isWait) || "".equals(mileage)
//                    || "".equals(oiltable) || "".equals(deliveryTime) || "".equals(userName)
                if ("".equals(carNo) || carNo == null) {
                    UIHelper.t(_activity, "请填写车牌号！");
                    return;
                }

                StringBuilder goodsProjectCount = new StringBuilder();//美容
                StringBuilder sgoodsProjectPrice = new StringBuilder();//美容
                StringBuilder maintainProjectCount = new StringBuilder();//保养
                StringBuilder smaintainProjectPrice = new StringBuilder();//保养
                StringBuilder repairProjectCount = new StringBuilder();//维修
                StringBuilder srepairProjectPrice = new StringBuilder();//维修
                StringBuilder refitProjectCount = new StringBuilder();//换装
                StringBuilder srefitProjectPrice = new StringBuilder();//换装
                for (ServiceEditBean bean : serviceEditData) {
                    if (bean.getProject().equals("goodsProject")) {
                        if (bean.getCount() != null && !bean.getCount().equals("")) {
                            goodsProjectCount.append(bean.getCount() + ",");
                            sgoodsProjectPrice.append(bean.getPrice() + ",");
                        } else {
                            goodsProjectCount.append("1,");
                            sgoodsProjectPrice.append(null + ",");
                        }
                    } else if (bean.getProject().equals("maintainProject")) {
                        if (bean.getCount() != null && !bean.getCount().equals("")) {
                            maintainProjectCount.append(bean.getCount() + ",");
                            smaintainProjectPrice.append(bean.getPrice() + ",");
                        } else {
                            maintainProjectCount.append("1,");
                            smaintainProjectPrice.append(null + ",");
                        }
                    } else if (bean.getProject().equals("repairProject")) {
                        if (bean.getCount() != null && !bean.getCount().equals("")) {
                            repairProjectCount.append(bean.getCount() + ",");
                            srepairProjectPrice.append(bean.getPrice() + ",");
                        } else {
                            repairProjectCount.append("1,");
                            srepairProjectPrice.append(null + ",");
                        }
                    } else if (bean.getProject().equals("refitProject")) {
                        if (bean.getCount() != null && !bean.getCount().equals("")) {
                            refitProjectCount.append(bean.getCount() + ",");
                            srefitProjectPrice.append(bean.getPrice() + ",");
                        } else {
                            refitProjectCount.append("1,");
                            srefitProjectPrice.append(null + ",");
                        }
                    }
                }


                LogUtils.e(sGoodsProject);
                LogUtils.e(goodsProjectCount.toString());
                LogUtils.e(sgoodsProjectPrice.toString());
                LogUtils.e(sMaintainProject);
                LogUtils.e(maintainProjectCount.toString());
                LogUtils.e(smaintainProjectPrice.toString());
                LogUtils.e(sRepairProject);
                LogUtils.e(repairProjectCount.toString());
                LogUtils.e(srepairProjectPrice.toString());
                LogUtils.e(sRefitProject);
                LogUtils.e(refitProjectCount.toString());
                LogUtils.e(srefitProjectPrice.toString());


                AppUtil.getCarApiClient(ac).saveCar(carNo, carFrameNo, brandId, customerName, sex, mobile, customerId,
                        isWait, mileage, oiltable, deliveryTime, registerTime, remark, commercialTime, userName,
                        compulsoryTime, sGoodsProject, sMaintainProject, sRepairProject, sRefitProject, ac.shopId, tvPrice.getText().toString(),
                        goodsProjectCount.toString(), maintainProjectCount.toString(), repairProjectCount.toString(), refitProjectCount.toString(), sNickName,
                        sgoodsProjectPrice.toString(), smaintainProjectPrice.toString(), srepairProjectPrice.toString(), srefitProjectPrice.toString(),
                        new BaseApiCallback() {
                            @Override
                            public void onApiStart(String tag) {
                                super.onApiStart(tag);
                                showLoadingDlg();
                                btnReceive.setClickable(false);
                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                hideLoadingDlg();
                                btnReceive.setClickable(true);
                                if (res.isOK()) {
                                    if ("saveCar".equals(tag)) {
                                        UIHelper.t(_activity, "添加成功！");
                                        finish();
                                    }
                                } else {
                                    ac.handleErrorCode(_activity, res.getMsg());
                                }
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                super.onApiFailure(t, errorNo, strMsg, tag);
                                hideLoadingDlg();
                                btnReceive.setClickable(true);
                            }
                        });
                break;
            case R.id.btnSearch:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                AppUtil.getCarApiClient(ac).getCarMaintain(etMile.getText().toString(), apiCallback);
                break;
            case R.id.ivScan:

                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(_activity,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
                    } else {
                        bundle.putString("flag", "ActivityAddCar");
                        UIHelper.jumpForResult(_activity, RectCameraActivity.class, bundle, 1003);
                    }
                } else {
                    bundle.putString("flag", "ActivityAddCar");
                    UIHelper.jumpForResult(_activity, RectCameraActivity.class, bundle, 1003);
                }

                break;
            case R.id.clear:
                btnReceive.setFocusable(true);
                btnReceive.setFocusableInTouchMode(true);
                btnReceive.requestFocus();
                goodsProject.delete(0, ActivityAddCar.goodsProject.length());
                maintainProject.delete(0, ActivityAddCar.maintainProject.length());
                repairProject.delete(0, ActivityAddCar.repairProject.length());
                refitProject.delete(0, ActivityAddCar.refitProject.length());
                serviceEditData.clear();
                tvPrice.setText("0.0");
                tvCount.setText("共0项");
                UIHelper.t(context, "已重置，请重新选择项目");
                ServiceEditAdapterRefresh();
                if (serviceEditAdapter != null) {
                    serviceEditAdapter.setPriceEnable(true);
                }
                etUpdatePrice.setEnabled(true);
                break;
            case R.id.btnAddCustomProject:
               /* final DialogAddCustomProjectPrice dialog = new DialogAddCustomProjectPrice(_activity);
                dialog.setOnOkButtonClick(new DialogAddCustomProjectPrice.onOkButtonClick() {
                    @Override
                    public void add() {
                        dialog.dismiss();
                        String priceType = dialog.getContentType();
                        UIHelper.jumpForResult(_activity,ActivityAddCustomProject.class,1004);
                    }
                });
                dialog.show();*/
                UIHelper.jumpForResult(_activity, ActivityAddCustomProject.class, 1004);
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
            if (requestCode == CUSTOMERORDER) {
                tvOrder.setText(data.getStringExtra("content"));
            }
            if (requestCode == CHOOSECUSTOMER) {
                LogUtils.e(data);
                customerName = data.getStringExtra("name");
                sex = data.getStringExtra("sex");
                customerId = data.getStringExtra("userId");
                mobile = data.getStringExtra("phone");
                etName.setText(customerName);
                etPhone.setText(mobile);
                etNickName.setText(data.getStringExtra("nickname") == null ? "" : data.getStringExtra("nickname"));
                if ("0".equals(sex)) {
                    rbMan.setChecked(true);
                } else {
                    rbWomen.setChecked(true);
                }
            }
            if (requestCode == 1003) {
                number = data.getStringExtra("number");
                LogUtils.e("number:" + number);
                etCarNumber.setDefaultText(number);
                etName.requestFocus();
                if (number != null) {
                    AppUtil.getCarApiClient(ac).selectCarAndDocumentByCarNo(number, apiCallback);
                }
            }
            if (requestCode == 1004) {
                /*AddCustomProjectBean bean = (AddCustomProjectBean) data.getSerializableExtra("data");
                if(bean!=null){
                    customProjectList.add(bean);
                    adapterAddCustomProject.notifyDataSetChanged();
                }*/
                ServiceCustomResultBean.DataBean bean = (ServiceCustomResultBean.DataBean) data.getSerializableExtra("data");
                if (bean != null) {
                    if (bean.getCode().equalsIgnoreCase("A01")) {
                        if (!ActivityAddCar.goodsProject.toString().contains(bean.getName())) {
                            ActivityAddCar.goodsProject.append(bean.getName() + ",");
                            ActivityAddCar.serviceEditData.add(new ServiceEditBean(bean.getId(), "goodsProject", bean.getName(), bean.getFrequency(), bean.getServicePrice()));

                        } else {
                            UIHelper.t(context, "已添加该项目");
                        }
                    } else if (bean.getCode().equalsIgnoreCase("A02")) {
                        if (!ActivityAddCar.maintainProject.toString().contains(bean.getName())) {
                            ActivityAddCar.maintainProject.append(bean.getName() + ",");
                            ActivityAddCar.serviceEditData.add(new ServiceEditBean(bean.getId(), "maintainProject", bean.getName(), bean.getFrequency(), bean.getServicePrice()));

                        } else {
                            UIHelper.t(context, "已添加该项目");
                        }
                    } else if (bean.getCode().equalsIgnoreCase("A03")) {
                        if (!ActivityAddCar.repairProject.toString().contains(bean.getName())) {
                            ActivityAddCar.repairProject.append(bean.getName() + ",");
                            ActivityAddCar.serviceEditData.add(new ServiceEditBean(bean.getId(), "repairProject", bean.getName(), bean.getFrequency(), bean.getServicePrice()));

                        } else {
                            UIHelper.t(context, "已添加该项目");
                        }
                    } else if (bean.getCode().equalsIgnoreCase("A04")) {
                        if (!ActivityAddCar.refitProject.toString().contains(bean.getName())) {
                            ActivityAddCar.refitProject.append(bean.getName() + ",");
                            ActivityAddCar.serviceEditData.add(new ServiceEditBean(bean.getId(), "refitProject", bean.getName(), bean.getFrequency(), bean.getServicePrice()));

                        } else {
                            UIHelper.t(context, "已添加该项目");
                        }
                    } else {
                        UIHelper.t(context, "数据异常，无法快捷添加");
                    }
                    ServiceEditAdapterRefresh();
                }

            } else if (requestCode == 1005) {
                //派工返回处理
                ids = data.getStringExtra("ids");
                position = data.getIntExtra("position", 0);
                serviceEditData.get(position).setIds(ids);
                LogUtils.e(data.getStringExtra("names"));
                serviceEditData.get(position).setWorkers(data.getStringExtra("names"));
                serviceEditAdapter.notifyDataSetChanged();

                //获取焦点，listview刷新时失去焦点导致页面显示跳到顶部
                innerListViewService.requestFocus();
            }
        }
    }

    private void initTimeDialog() {
        DatePickDialog dialog = new DatePickDialog(_activity);
        dialog.setYearLimt(5);
        dialog.setTitle("选择时间");
        dialog.setType(DateType.TYPE_ALL);
        dialog.setMessageFormat("yyyy-MM-dd");
        dialog.setOnSureLisener(this);
        dialog.show();
    }

    @Override
    public void doNext(int positon, String text) {
        tvOil.setText(text);
        pop.dismiss();
    }

    @Override
    public void onSure(Date date) {
        Date d = new Date();
        if (date.getTime() < d.getTime()) {
            UIHelper.t(_activity, "预计交车时间不能早于当前时间！");
            return;
        }
        String time = simpleDateFormat.format(date);
        tvChooseTime.setText(time);
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
                    if (!TextUtils.isEmpty(etCarNumber.getNumbers())) {
                        AppUtil.getCarApiClient(ac).selectCarAndDocumentByCarNo(etCarNumber.getNumbers(), apiCallback);
                    }
                    LogUtils.d(etCarNumber.getNumbers());
                    LogUtils.d(TextUtils.isEmpty(etCarNumber.getNumbers()));
                }
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
                if (isShow()) {
                    hideKeyboard();
                    return true;
                } else if (dialogIfExit == null) {
                    dialogIfExit = new DialogIfExit(_activity);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
        goodsProject.delete(0, goodsProject.length());
        maintainProject.delete(0, maintainProject.length());
        repairProject.delete(0, repairProject.length());
        refitProject.delete(0, refitProject.length());
        serviceEditData.clear();
        try {
            RectCameraActivity.instant.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        AppManager.getAppManager().finishActivity(RectCameraActivity.class);
    }

//    meditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                yourcalc();
//                return true;
//            }
//            return false;
//        }
//    });


    private void addHotService(List<ServerHotBean.DataBean> list) {
        AdapterHotService adapterHotService = new AdapterHotService(list, _activity);
        gridViewHotService.setAdapter(adapterHotService);
    }

    public static void ServiceEditAdapterRefresh() {
        if (serviceEditAdapter != null) {
            if (serviceEditData.size() > 0) {
                innerListViewService.setAdapter(serviceEditAdapter);
                serviceEditAdapter.notifyDataSetChanged();
            } else {
                innerListViewService.setAdapter(null);
            }
            sumPrice();
        }

    }

    public static void sumPrice() {

        tvCount.setText("共" + serviceEditData.size() + "项");
        Double price = 0.0;
        for (ServiceEditBean bean : serviceEditData) {
            if (bean.getPrice() != null && bean.getCount() != null && bean.getCount().length() > 0 && bean.getPrice().length() > 0) {
                price = price + Integer.parseInt(bean.getCount()) * Double.parseDouble(bean.getPrice());
            }
        }
        tvPrice.setText(price.toString());
        etUpdatePrice.setText(price.toString());
    }


    static class ServiceEditAdapter extends BaseAdapter {

        boolean priceEnable = true;

        public void setPriceEnable(boolean priceEnable) {
            this.priceEnable = priceEnable;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return serviceEditData.size();
        }

        @Override
        public ServiceEditBean getItem(int position) {
            return serviceEditData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_add_car_service, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            viewHolder.name.setText(serviceEditData.get(position).getName());
            viewHolder.count.setNumber(Integer.parseInt((serviceEditData.get(position).getCount() == null ? "1" : serviceEditData.get(position).getCount())));
            viewHolder.count.setTag(serviceEditData.get(position).getId());
            viewHolder.tvDispatching.setText(serviceEditData.get(position).getWorkers());
           /* viewHolder.count.setOnNumberChangeListener(new BaseTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                    if (serviceEditData.get(position).getId().equals(viewHolder.count.getTag())) {
                        serviceEditData.get(position).setCount(s.toString());
                        sumPrice();
                    }
                }
            });*/
            viewHolder.count.setOnAddBtnClicked(new NumberWidgetBig.OnAddBtnClickedListener() {
                @Override
                public boolean onAddBtnClicked(int i) {
                    if (serviceEditData.get(position).getCount() != null && !serviceEditData.get(position).getCount().equals("")) {
                        int count = Integer.parseInt(serviceEditData.get(position).getCount());
                        count++;
                        serviceEditData.get(position).setCount(count + "");
                        notifyDataSetChanged();
                        sumPrice();
                    }
                    return true;

                }
            });


            viewHolder.count.setOnReduceBtnClickedListener(new NumberWidgetBig.OnReduceBtnClickedListener() {
                @Override
                public boolean onReduceBtnClicked(int i) {

                    if (serviceEditData.get(position).getCount() != null && !serviceEditData.get(position).getCount().equals("")) {
                        int count = Integer.parseInt(serviceEditData.get(position).getCount());
                        if (count > 1) {
                            count--;
                            serviceEditData.get(position).setCount(count + "");
                            notifyDataSetChanged();
                            sumPrice();
                        }
                    }
                    return true;
                }
            });
            viewHolder.price.setText(serviceEditData.get(position).getPrice() == null ? "" : serviceEditData.get(position).getPrice());
            /*if (priceEnable == false) {
                viewHolder.price.setEnabled(false);
            }*/
            viewHolder.price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (viewHolder.price.getText().toString().trim() != null && viewHolder.price.getText().toString().trim().length() != 0) {
                            serviceEditData.get(position).setPrice(viewHolder.price.getText().toString());
                        }
                        sumPrice();
                    } else {
                        etUpdatePrice.setEnabled(false);
                    }
                }
            });


            viewHolder.price.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        viewHolder.price.clearFocus();
                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(viewHolder.price.getWindowToken(), 0);
                        }
                    }
                    return false;
                }
            });
            viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String projectType = serviceEditData.get(position).getProject();
                    if (projectType.equals("goodsProject")) {
                        String project = goodsProject.toString().replace(serviceEditData.get(position).getName() + ",", "");
                        goodsProject.delete(0, goodsProject.length());
                        goodsProject.append(project);
                    } else if (projectType.equals("maintainProject")) {
                        String project = maintainProject.toString().replace(serviceEditData.get(position).getName() + ",", "");
                        maintainProject.delete(0, maintainProject.length());
                        maintainProject.append(project);
                    } else if (projectType.equals("repairProject")) {
                        String project = repairProject.toString().replace(serviceEditData.get(position).getName() + ",", "");
                        repairProject.delete(0, repairProject.length());
                        repairProject.append(project);
                    } else if (projectType.equals("refitProject")) {
                        String project = refitProject.toString().replace(serviceEditData.get(position).getName() + ",", "");
                        refitProject.delete(0, refitProject.length());
                        refitProject.append(project);
                    }
                    serviceEditData.remove(position);
                    notifyDataSetChanged();
                    sumPrice();
                }
            });

            viewHolder.tvDispatching.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    UIHelper.jumpForResult(context, ActivityDispatchToChooseWorkerNew.class, bundle, 1005);
                }
            });


            return convertView;
        }


        class ViewHolder {
            @BindView(R.id.name)
            TextView name;
            @BindView(R.id.count)
            NumberWidgetBig count;
            @BindView(R.id.price)
            EditText price;
            @BindView(R.id.ivDel)
            ImageView ivDel;
            @BindView(R.id.tvDispatching)
            TextView tvDispatching;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }


}
