package com.lida.carcare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterMemCard;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.bean.GetCarnewDetailBean;
import com.lida.carcare.widget.CarClassDialog;
import com.lida.carcare.widget.DialogCall;
import com.lida.carcare.widget.InnerGridView;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 车辆详情-车辆信息
 * Created by Administrator on 2017/7/17.
 */

public class FragmentCartDetail extends BaseFragment {

    @BindView(R.id.tvCarClass)
    TextView tvCarClass;
    @BindView(R.id.tvCarNum)
    EditText tvCarNum;
    @BindView(R.id.tvRegDate)
    TextView tvRegDate;
    @BindView(R.id.tvSYXDate)
    TextView tvSYXDate;
    @BindView(R.id.tvQXDate)
    TextView tvQXDate;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvCustomerPhone)
    TextView tvCustomerPhone;
    @BindView(R.id.tvCustomerLevel)
    TextView tvCustomerLevel;
    @BindView(R.id.tvPromoterName)
    TextView tvPromoterName;
    @BindView(R.id.gvCard)
    InnerGridView gvCard;
    @BindView(R.id.etCarMachineNume)
    EditText etCarMachineNume;

    private String carId;//车辆id
    private CarClassDialog dialog = null;
    private CarTypeBean bean = null;

    private DatePickDialog dialogDate;
    private SimpleDateFormat simpleDateFormat;

    private Bundle mBundle;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_detail, null);
        ButterKnife.bind(this, view);
        mBundle = getActivity().getIntent().getExtras();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        carId = _activity.getIntent().getStringExtra("id");
        AppUtil.getCarApiClient(ac).getCarnewDetail(carId, this);
        return view;
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        _activity.showLoadingDlg();
    }

    private String getEditText(TextView tv) {
        String text = null;
        if (tv != null) {
            text = tv.getText().toString().trim();
        }
        return text;
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            if ("getCarnewDetail".equals(tag)) {
                GetCarnewDetailBean bean = (GetCarnewDetailBean) res;
                tvCarClass.setText(bean.getData().getBrand().getBrandName());
                tvCarNum.setText(bean.getData().getCarFrameNo());
                tvRegDate.setText(bean.getData().getCreateDate());
                etCarMachineNume.setText(bean.getData().getEngineNo());
                tvSYXDate.setText(bean.getData().getEndDate());
                tvQXDate.setText(bean.getData().getCompulsoryDate());
                tvCustomerName.setText(bean.getData().getCustomer().getCustomerName());
                tvCustomerPhone.setText(bean.getData().getCustomer().getMobilePhoneNo());
                tvCustomerLevel.setText(bean.getData().getCustomerLevel().getCustomerLevelName());
                tvPromoterName.setText(bean.getData().getPromoter().getPromoterName());
                List<GetCarnewDetailBean.DataBean.ConsumeCardBean> gvData = bean.getData().getConsumeCard();
                gvCard.setAdapter(new AdapterMemCard(_activity, gvData));
            } else if ("getCarType".equals(tag)) {
                bean = (CarTypeBean) res;
                if (bean.getData() != null) {
                    dialog = new CarClassDialog(getActivity(), tvCarClass, bean.getData());
                    dialog.show();
                }
            } else if ("updatecar".equals(tag)) {
                UIHelper.t(_activity, "保存成功!");
                _activity.finish();
            }

        } else
        {
            ac.handleErrorCode(_activity, res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        _activity.hideLoadingDlg();
    }

    private void initTimeDialog() {
        dialogDate = new DatePickDialog(_activity);
        dialogDate.setYearLimt(5);
        dialogDate.setTitle("选择时间");
        dialogDate.setType(DateType.TYPE_ALL);
        dialogDate.setMessageFormat("yyyy-MM-dd HH:mm");
        dialogDate.show();
    }

    @OnClick({R.id.tvRegDate, R.id.tvSYXDate, R.id.tvQXDate})
    public void onViewClicked(View view) {
        initTimeDialog();
        switch (view.getId()) {
            case R.id.tvRegDate:
                dialogDate.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        Date d = new Date();
                        if (date.getTime() > d.getTime()) {
                            UIHelper.t(_activity, "注册日期不能晚于当前时间！");
                            return;
                        }
                        tvRegDate.setText(simpleDateFormat.format(date));
                    }
                });
                break;
            case R.id.tvSYXDate:
                dialogDate.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        Date d = new Date();
                       /* if(date.getTime()<d.getTime()){
                            UIHelper.t(_activity, "商业险到期日期不能早于当前时间！");
                            return;
                        }*/
                        tvSYXDate.setText(simpleDateFormat.format(date));
                    }
                });
                break;
            case R.id.tvQXDate:
                dialogDate.setOnSureLisener(new OnSureLisener() {
                    @Override
                    public void onSure(Date date) {
                        Date d = new Date();
                       /* if(date.getTime()<d.getTime()){
                            UIHelper.t(_activity, "交强险日期不能早于当前时间！");
                            return;
                        }*/
                        tvQXDate.setText(simpleDateFormat.format(date));
                    }
                });
                break;
        }
    }

    @OnClick(R.id.tvCarClass)
    public void showCartType(){
        if(bean==null) {
            AppUtil.getCarApiClient(ac).getCarType(this);
        }else {
            if(bean.getData()!=null){
                dialog = new CarClassDialog(_activity, tvCarClass, bean.getData());
                dialog.show();
            }
        }
    }

    @OnClick(R.id.tvCustomerPhone)
    public void callPhone(){
        if(!tvCustomerPhone.getText().toString().equals("")){
            new DialogCall(_activity,tvCustomerPhone.getText().toString()).show();
        }
    }


    public void save(){
        AppUtil.getCarApiClient(ac).updatecar(mBundle.getString("carNo"), (String) tvCarClass.getTag(),
                getEditText(tvCarNum), null, getEditText(tvRegDate), getEditText(tvQXDate),
                getEditText(tvSYXDate),etCarMachineNume.getText().toString(), this);
    }

}
