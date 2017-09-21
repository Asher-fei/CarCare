package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterAddCustomer;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CustomerBean;
import com.lida.carcare.bean.CustomerDetailBean;
import com.lida.carcare.bean.CustomerSourceBean;
import com.lida.carcare.bean.GradeBean;
import com.lida.carcare.widget.DialogChooseGrade;
import com.lida.carcare.widget.DialogChooseSource;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增客户
 * Created by Administrator on 2017/4/5.
 */

public class ActivityAddCustomer extends BaseActivity implements OnSureLisener {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.rbPersonal)
    RadioButton rbPersonal;
    @BindView(R.id.rbCompany)
    RadioButton rbCompany;
    @BindView(R.id.rgType)
    RadioGroup rgType;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.rbMan)
    RadioButton rbMan;
    @BindView(R.id.rbWomen)
    RadioButton rbWomen;
    @BindView(R.id.rgSex)
    RadioGroup rgSex;
    @BindView(R.id.etNumber)
    EditText etNumber;
    @BindView(R.id.tvGrade)
    TextView tvGrade;
    @BindView(R.id.tvFrom)
    TextView tvFrom;
    @BindView(R.id.tvBirth)
    TextView tvBirth;
    @BindView(R.id.tvCompany)
    EditText tvCompany;
    @BindView(R.id.btnAddContact)
    Button btnAddContact;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.etNickName)
    EditText etNickName;

    private List<CustomerBean> customerBean = new ArrayList<>();
    private AdapterAddCustomer adapter;

    private List<GradeBean.DataBean> gradeData = new ArrayList<>();//用户级别数据
    private List<CustomerSourceBean.DataBean> sourceData = new ArrayList<>();//用户来源数据

    String id = "";
    CustomerDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);
        ButterKnife.bind(this);
        topbar.setTitle("新增客户");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("保存", listener);
        adapter = new AdapterAddCustomer(_activity, customerBean);
        listView.setAdapter(adapter);
        if (getIntent().getStringExtra("updateId") != null) {
            topbar.setTitle("修改客户信息");
            id = getIntent().getStringExtra("updateId");
        }
        AppUtil.getCarApiClient(ac).getCustomerLevel(this);
        AppUtil.getCarApiClient(ac).getCustomerSource(this);
        if (!id.equals("")) {
            AppUtil.getCarApiClient(ac).selectCustomerByIdInfonation(id, this);
        }
    }

    /**
     * 保存按钮
     */
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String customerName = "";
            String customerType = "";
            String sex = "";
            String mobilePhone = "";
            String customerLevelId = "";
            String customerSource = "";
            String birthDate = "";
            String customerCompany = "";
            String remark = "";
            String nickname = "";
            StringBuilder contactName = new StringBuilder();
            StringBuilder contactSex = new StringBuilder();
            StringBuilder contactMobile = new StringBuilder();
            StringBuilder contactRelation = new StringBuilder();
            if (rgType.getCheckedRadioButtonId() == R.id.rbPersonal) {
                customerType = "0";
            } else {
                customerType = "1";
            }
            if (rgSex.getCheckedRadioButtonId() == R.id.rbMan) {
                sex = "0";
            } else {
                sex = "1";
            }
            customerName = etName.getText().toString();
            mobilePhone = etNumber.getText().toString();
            customerLevelId = (String) tvGrade.getTag();
            customerSource = (String) tvFrom.getTag();
            birthDate = tvBirth.getText().toString();
            customerCompany = tvCompany.getText().toString();
            remark = etRemark.getText().toString();
            nickname = etNickName.getText().toString();

                /*if ("".equals(customerName) || "".equals(mobilePhone) || "".equals(customerLevelId) || "".equals(customerSource)
                        || "".equals(birthDate) || "".equals(customerCompany)) {
                    UIHelper.t(_activity, "资料填写不完整！");
                    return;
                }*/
            if ("".equals(customerName) || "".equals(mobilePhone)) {
                UIHelper.t(_activity, "资料填写不完整！");
                return;
            }

            if (!Func.isMobileNO(mobilePhone)) {
                UIHelper.t(_activity, "手机号码格式不正确！");
                return;
            }
            for (int i = 0; i < customerBean.size(); i++) {
                if (customerBean.get(i).isComplete() == false) {
                    LogUtils.e(customerBean.get(i));
                    UIHelper.t(_activity, "其他联系人资料填写不完整！");
                    return;
                }
                if (!customerBean.get(i).isPhoneRight()) {
                    UIHelper.t(_activity, "其他联系人手机号码格式不正确！");
                    return;
                }
                contactName.append(customerBean.get(i).getName());
                if (i < customerBean.size() - 1) {
                    contactName.append(",");
                }
                contactSex.append(customerBean.get(i).getSex());
                if (i < customerBean.size() - 1) {
                    contactSex.append(",");
                }
                contactMobile.append(customerBean.get(i).getPhone());
                if (i < customerBean.size() - 1) {
                    contactMobile.append(",");
                }
                contactRelation.append(customerBean.get(i).getRelation());
                if (i < customerBean.size() - 1) {
                    contactRelation.append(",");
                }
            }
            if (!id.equals("")) {
                AppUtil.getCarApiClient(ac).updateCustomer(id, customerName, customerType, sex, mobilePhone, customerLevelId,
                        customerSource, birthDate, customerCompany, remark, contactName.toString(), contactSex.toString(), contactMobile.toString(), contactRelation.toString(), nickname,ActivityAddCustomer.this);
            } else {
                AppUtil.getCarApiClient(ac).saveCustomer(customerName, customerType, sex, mobilePhone, customerLevelId,
                        customerSource, birthDate, customerCompany, remark, contactName.toString(), contactSex.toString(), contactMobile.toString(), contactRelation.toString(), ac.shopId,nickname, ActivityAddCustomer.this);
            }

        }
    };

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("getCustomerLevel".equals(tag)) {
                GradeBean bean = (GradeBean) res;
                gradeData.addAll(bean.getData());
                checkDetailData();
            } else if ("getCustomerSource".equals(tag)) {
                CustomerSourceBean bean = (CustomerSourceBean) res;
                sourceData.addAll(bean.getData());
                checkDetailData();
            } else if ("saveCustomer".equals(tag)) {
                UIHelper.t(_activity, "保存成功！");
                setResult(RESULT_OK);
                finish();
            } else if ("selectCustomerByIdInfonation".equals(tag)) {
                bean = (CustomerDetailBean) res;
                checkDetailData();
            } else if ("updateCustomer".equals(tag)) {
                UIHelper.t(_activity, "操作成功！");
                setResult(RESULT_OK);
                finish();
            }
        } else {
            if ("saveCustomer".equals(tag)) {
                UIHelper.t(ac, res.getMsg());
            } else {
                UIHelper.t(ac, res.getMsg());
            }
        }
    }

    @OnClick({R.id.tvGrade, R.id.tvFrom, R.id.tvBirth, R.id.btnAddContact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGrade:
                new DialogChooseGrade(_activity, tvGrade, gradeData).show();
                break;
            case R.id.tvFrom:
                new DialogChooseSource(_activity, tvFrom, sourceData).show();
                break;
            case R.id.tvBirth:
                initTimeDialog();
                break;
            case R.id.btnAddContact:
                customerBean.add(new CustomerBean("", "", "", "0"));
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void initTimeDialog() {
        DatePickDialog dialog = new DatePickDialog(_activity);
        dialog.setYearLimt(50);
        dialog.setTitle("选择时间");
        dialog.setType(DateType.TYPE_YMD);
        dialog.setMessageFormat("yyyy-MM-dd");
        dialog.setOnSureLisener(this);
        dialog.show();
    }

    @Override
    public void onSure(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        tvBirth.setText(format);
    }


    private void checkDetailData() {
        if (bean != null && gradeData.size() > 0 && sourceData.size() > 0) {
            if (bean.getData() != null) {
                initData(bean.getData());
            }
        }
    }

    private void initData(CustomerDetailBean.DataBean bean) {
        if (bean != null) {
            if (bean.getCustomerType() != null) {
                if (bean.getCustomerType().equals("0")) {
                    rbPersonal.setChecked(true);
                } else {
                    rbCompany.setChecked(true);
                }
            }
            etName.setText(bean.getCustomerName() == null ? "" : bean.getCustomerName());
            if (bean.getSex() != null) {
                if (bean.getSex().equals("0")) {
                    rbMan.setChecked(true);
                } else {
                    rbWomen.setChecked(true);
                }
            }
            etNumber.setText(bean.getMobilePhoneNo() == null ? "" : bean.getMobilePhoneNo());
            if (gradeData.size() > 0) {
                for (int i = 0; i < gradeData.size(); i++) {
                    if (bean.getCustomerLevel() != null) {
                        if (bean.getCustomerLevel().getId().equals(gradeData.get(i).getId())) {
                            tvGrade.setTag(bean.getCustomerLevel().getId());
                            tvGrade.setText(bean.getCustomerLevel().getCustomerLevelName());
                        }
                    }
                }
            }

            if (sourceData.size() > 0) {
                for (int i = 0; i < sourceData.size(); i++) {
                    if (bean.getPromoter() != null) {
                        if (bean.getPromoter().getId().equals(sourceData.get(i).getId())) {
                            tvFrom.setTag(bean.getPromoter().getId());
                            tvFrom.setText(bean.getPromoter().getPromoterName());
                        }
                    }
                }
            }

            tvBirth.setText(bean.getBirthDates() == null ? "" : bean.getBirthDates());
            etNickName.setText(bean.getNickname()==null?"":bean.getNickname());
            etRemark.setText(bean.getRemark() == null ? "" : bean.getRemark());
            tvCompany.setText(bean.getCustomerCompany() == null ? "" : bean.getCustomerCompany());

            if (bean.getCustomerContactList() != null) {
                if (bean.getCustomerContactList().size() > 0) {
                    customerBean.clear();
                    for (int i = 0; i < bean.getCustomerContactList().size(); i++) {
                        customerBean.add(new CustomerBean(bean.getCustomerContactList().get(i).getContactName(), bean.getCustomerContactList().get(i).getContactMobile(), bean.getCustomerContactList().get(i).getContactRelation()
                                , bean.getCustomerContactList().get(i).getContactSex()));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

        }
    }
}
