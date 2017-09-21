package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterModifyCarInfyServiceList;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarDetailBean;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.bean.GetPriceBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.CarClassDialog;
import com.lida.carcare.widget.DialogQuickSetting;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupOilType;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
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
 * 车辆服务-修改车辆信息
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityModifyCarInfo extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvCarClass)
    TextView tvCarClass;
    @BindView(R.id.llCarClass)
    LinearLayout llCarClass;
    @BindView(R.id.tvCarNum)
    TextView tvCarNum;
    @BindView(R.id.tvCarPrice)
    TextView tvCarPrice;
    @BindView(R.id.tvRegDate)
    TextView tvRegDate;
    @BindView(R.id.tvSYXDate)
    TextView tvSYXDate;
    @BindView(R.id.tvQXDate)
    TextView tvQXDate;
    @BindView(R.id.tvCarMachineNume)
    TextView tvCarMachineNume;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvCustomerPhone)
    TextView tvCustomerPhone;
    @BindView(R.id.lvService)
    InnerListView lvService;
    @BindView(R.id.tvAddItem)
    TextView tvAddItem;
    @BindView(R.id.tvAllPrice)
    TextView tvAllPrice;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.etCarId)
    EditText etCarId;
    @BindView(R.id.tvChooseCar)
    TextView tvChooseCar;
    @BindView(R.id.tvChooseTime)
    TextView tvChooseTime;
    @BindView(R.id.tvQuickSetting)
    TextView tvQuickSetting;
    @BindView(R.id.tvOil)
    TextView tvOil;
    @BindView(R.id.cbWait)
    CheckBox cbWait;

    private String id;
    private String carNo;//车牌号

    private List<Item> items = new ArrayList<>();
    private String[] itemNames;
    private String[] itemCounts;
    private String[] itemPrices;
    private AdapterModifyCarInfyServiceList adapter;
    private String status;//判断上个页面车辆状态是待派工还是服务中


    private CarClassDialog carClassDialog;//车型选择；
    private PopupOilType pop;//油箱剩余数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifycarinfo);
        ButterKnife.bind(this);
        id = mBundle.getString("userId");
        status = mBundle.getString("status");
        topbar.setTitle("车辆服务");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).getCarDetail(id, ac.shopId, this);
        initPop();
    }

    private void initPop() {
        View view = LayoutInflater.from(_activity).inflate(R.layout.layout_spinner, null);
        pop = new PopupOilType(_activity, view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void doNext(int positon, String text) {
                tvOil.setText(text);
                pop.dismiss();
            }
        });
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
            if ("getCarDetail".equals(tag)) {
                CarDetailBean bean = (CarDetailBean) res;
                if (bean.getData() != null) {
                    tvCarClass.setText(bean.getData().getBrand().getBrandName());
                    tvCarNum.setText(bean.getData().getCar().getCarFrameNo());
                    tvCarMachineNume.setText(bean.getData().getCar().getEngineNo());
                    tvCarPrice.setText(bean.getData().getCar().getCarPrice());
                    tvRegDate.setText(bean.getData().getCar().getCreateDate());
                    tvSYXDate.setText(bean.getData().getCar().getCompulsoryDate());
                    tvQXDate.setText(bean.getData().getCar().getEndDate());
                    tvCustomerName.setText(bean.getData().getCustomer().getCustomerName());
                    tvCustomerPhone.setText(bean.getData().getCustomer().getMobilePhoneNo());
                    carNo = bean.getData().getCarNo();
                    StringBuilder temp = new StringBuilder();
                    temp.append(bean.getData().getGoodsProject()).append(bean.getData().getMaintainProject())
                            .append(bean.getData().getRepairProject()).append(bean.getData().getRefitProject());
                    itemNames = temp.toString().split(",");
                    StringBuilder tempCount = new StringBuilder();
                    tempCount.append(bean.getData().getGoodNumber()).append(bean.getData().getMaintainNumber())
                            .append(bean.getData().getRepairNumber()).append(bean.getData().getRefitNumber());
                    itemCounts = tempCount.toString().split(",");
                    StringBuilder tempPrice = new StringBuilder();
                    tempPrice.append(bean.getData().getGoodPrice()).append(bean.getData().getMaintainPrice())
                            .append(bean.getData().getRepairPrice()).append(bean.getData().getRefitPrice());
                    itemPrices = tempPrice.toString().split(",");
                    if (itemPrices.length > 0 && itemPrices[0].equals("")) {
                        AppUtil.getCarApiClient(ac).getPrice(temp.toString(), ac.shopId, this);
                    } else {

                        if (itemCounts != null) {
                            for (int i = 0; i < itemCounts.length; i++) {
                                Item item = new Item();
                                item.setName(itemNames[i]);
                                if (itemPrices != null && i < itemPrices.length) {
                                    if (itemPrices[i] != null && !itemPrices[i].contains("null")) {
                                        item.setPrice(itemPrices[i]);
                                    }
                                }
                                if (itemCounts != null && i < itemCounts.length) {
                                    if (itemCounts[i] != null && !itemCounts[i].equals("") && !itemCounts[i].contains("null")) {
                                        item.setCount(Integer.parseInt(itemCounts[i].trim()));
                                    }
                                }
                                items.add(item);
                            }
                            double price = 0.0;
                            for (int i = 0; i < items.size(); i++) {
                                String str = items.get(i).getPrice();
                                if (str != null && !"".equals(str) && str.length() > 0) {
                                    price += Double.valueOf(items.get(i).getPrice()) * items.get(i).getCount();
                                }
                            }
                            tvAllPrice.setText(String.valueOf(price));
                            LogUtils.d(items);
                            adapter = new AdapterModifyCarInfyServiceList(_activity, items, status);
                            adapter.setOnPriceChangeListener(onPriceChangeListener);
                            lvService.setAdapter(adapter);
                        }

                    }
                }
            } else if ("getPrice".equals(tag)) {
                GetPriceBean bean = (GetPriceBean) res;
                String[] prices = bean.getData().getSb().split(",");
                for (int i = 0; i < prices.length; i++) {
                    Item item = new Item();
                    item.setName(itemNames[i]);
                    item.setPrice(prices[i]);
                    if (itemCounts != null && i < itemCounts.length) {
                        if (itemCounts[i] != null && !itemCounts[i].equals("") && !itemCounts[i].contains("null")) {
                            item.setCount(Integer.parseInt(itemCounts[i].trim()));
                        }
                    }
                    items.add(item);
                }
                double price = 0.0;
                for (int i = 0; i < items.size(); i++) {
                    String str = items.get(i).getPrice();
                    if (str != null && !"".equals(str) && str.length() > 0) {
                        price += Double.valueOf(items.get(i).getPrice()) * items.get(i).getCount();
                    }
                }
                tvAllPrice.setText(String.valueOf(price));
                LogUtils.d(items);
                adapter = new AdapterModifyCarInfyServiceList(_activity, items, status);
                adapter.setOnPriceChangeListener(onPriceChangeListener);
                lvService.setAdapter(adapter);
            } else if ("updateCarDetailAndService".equals(tag)) {
                UIHelper.t(_activity, "修改成功！");
                finish();
            }
        } else {
            if ("updateCarDetailAndService".equals(tag)) {
                UIHelper.t(_activity, res.getMsg());
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    AdapterModifyCarInfyServiceList.onPriceChangeListener onPriceChangeListener = new AdapterModifyCarInfyServiceList.onPriceChangeListener() {
        @Override
        public void updatePrice() {
            LogUtils.e(items);
            double price = 0.0;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getPrice() != null && !items.get(i).getPrice().equals("")) {
                    price += Double.valueOf(items.get(i).getPrice()) * items.get(i).getCount();
                }
            }
            tvAllPrice.setText(String.valueOf(price));
        }
    };


   /* @OnClick({R.id.btnOk})
    public void onViewClicke(View view) {
        switch (view.getId()) {
            case R.id.btnOk:
                btnOk.setFocusable(true);
                btnOk.setFocusableInTouchMode(true);
                btnOk.requestFocus();
                StringBuilder carItems = new StringBuilder();
                StringBuilder carItemsCounts = new StringBuilder();
                StringBuilder carItemsPrice = new StringBuilder();
                LogUtils.e(items);
                for (int i = 0; i < items.size(); i++) {
                    carItems.append(items.get(i).getName() + ",");
                    carItemsCounts.append(items.get(i).getCount() + ",");
                    carItemsPrice.append(items.get(i).getPrice() + ",");
                }

                // TODO: 2017/6/13 完善
                AppUtil.getCarApiClient(ac).updateCarDetailAndService(carNo, "", tvCarNum.getText().toString(),
                        tvCarPrice.getText().toString(), tvRegDate.getText().toString(), "", "", carItems.toString(), tvAllPrice.getText().toString(),
                        ac.shopId, id, carItemsCounts.toString(), carItemsPrice.toString(), this);
                break;
        }
    }*/

    public class Item {
        private String name;
        private String price;
        private int count = 1;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", count=" + count +
                    '}';
        }
    }

    @OnClick({R.id.llCarClass, R.id.tvAddItem,R.id.tvChooseCar, R.id.tvChooseTime, R.id.tvQuickSetting, R.id.tvOil,R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llCarClass:
                break;
            case R.id.tvAddItem:
                UIHelper.jumpForResult(this, ActivitySelectServer.class, ActivitySelectServer.REQUEST_CODE);
                break;
            case R.id.tvChooseCar:
                if (carClassDialog == null) {
                    AppUtil.getCarApiClient(ac).getCarType(apiCallback);
                } else {
                    carClassDialog.show();
                }
                break;
            case R.id.tvChooseTime:
                initTimeDialog();
                break;
            case R.id.tvQuickSetting:
                new DialogQuickSetting(_activity, tvChooseTime).show();
                break;
            case R.id.tvOil:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    pop.showAsDropDown(tvOil);
                }
                break;
            case R.id.btnOk:
                btnOk.setFocusable(true);
                btnOk.setFocusableInTouchMode(true);
                btnOk.requestFocus();
                StringBuilder carItems = new StringBuilder();
                StringBuilder carItemsCounts = new StringBuilder();
                StringBuilder carItemsPrice = new StringBuilder();
                LogUtils.e(items);
                for (int i = 0; i < items.size(); i++) {
                    carItems.append(items.get(i).getName() + ",");
                    carItemsCounts.append(items.get(i).getCount() + ",");
                    carItemsPrice.append(items.get(i).getPrice() + ",");
                }

                // TODO: 2017/6/13 完善
                AppUtil.getCarApiClient(ac).updateCarDetailAndService(carNo, "", tvCarNum.getText().toString(),
                        tvCarPrice.getText().toString(), tvRegDate.getText().toString(), "", "", carItems.toString(), tvAllPrice.getText().toString(),
                        ac.shopId, id, carItemsCounts.toString(), carItemsPrice.toString(), this);
                break;
        }
    }

    private void initTimeDialog() {
        DatePickDialog dialog = new DatePickDialog(_activity);
        dialog.setYearLimt(5);
        dialog.setTitle("选择时间");
        dialog.setType(DateType.TYPE_ALL);
        dialog.setMessageFormat("yyyy-MM-dd");

        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                Date d = new Date();
                if (date.getTime() < d.getTime()) {
                    UIHelper.t(_activity, "预计交车时间不能早于当前时间！");
                    return;
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String time = simpleDateFormat.format(date);
                tvChooseTime.setText(time);
            }
        });
        dialog.show();
    }

    BaseApiCallback apiCallback = new BaseApiCallback(){
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
                if ("getCarType".equals(tag)) {
                    CarTypeBean bean = (CarTypeBean) res;
                    if (bean.getData() != null) {
                        carClassDialog = new CarClassDialog(_activity, tvChooseCar, bean.getData());
                        carClassDialog.show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivitySelectServer.REQUEST_CODE && resultCode == ActivitySelectServer.REQUEST_CODE) {
            ServiceGoodBean.DataBean.JfomServiceBean bean = (ServiceGoodBean.DataBean.JfomServiceBean) data.getSerializableExtra("bean");
            String price = bean.getServicePrice();
            Item item = new Item();
            item.setName(bean.getName());
            item.setPrice(price);
            item.setCount(1);
            if (!items.toString().contains(bean.getName())) {
                items.add(item);
                String tmp = tvAllPrice.getText().toString();
                double mun = Double.parseDouble(tmp) + Double.parseDouble(price);
                tvAllPrice.setText(String.valueOf(mun));
                adapter.notifyDataSetChanged();
            } else {
                UIHelper.t(_activity, "已添加该项目！");
            }
        }
    }
}
