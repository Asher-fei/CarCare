package com.lida.carcare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterMemCard;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GetCarnewDetailBean;
import com.lida.carcare.widget.InnerGridView;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车辆信息
 * Created by WeiQingFeng on 2017/4/14.
 */

public class FragmentCarInfo extends BaseFragment {
    @BindView(R.id.tvCarClass)
    TextView tvCarClass;
    @BindView(R.id.tvCarNum)
    TextView tvCarNum;
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
    @BindView(R.id.tvCustomerLevel)
    TextView tvCustomerLevel;
    @BindView(R.id.tvPromoterName)
    TextView tvPromoterName;
    @BindView(R.id.gvCard)
    InnerGridView gvCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cardetail, null);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("carId");
        AppUtil.getCarApiClient(ac).getCarnewDetail(id, this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if (res.isOK()) {
            if ("getCarnewDetail".equals(tag)) {
                GetCarnewDetailBean bean = (GetCarnewDetailBean) res;
                tvCarClass.setText(bean.getData().getBrand().getBrandName());
                tvCarNum.setText(bean.getData().getCarFrameNo());
                tvRegDate.setText(bean.getData().getCreateDate());
                tvCarMachineNume.setText(bean.getData().getEngineNo());
                tvSYXDate.setText(bean.getData().getEndDate());
                tvQXDate.setText(bean.getData().getCompulsoryDate());
                tvCustomerName.setText(bean.getData().getCustomer().getCustomerName());
                tvCustomerPhone.setText(bean.getData().getCustomer().getMobilePhoneNo());
                tvCustomerLevel.setText(bean.getData().getCustomerLevel().getCustomerLevelName());
                tvPromoterName.setText(bean.getData().getPromoter().getPromoterName());
                List<GetCarnewDetailBean.DataBean.ConsumeCardBean> gvData = bean.getData().getConsumeCard();
                gvCard.setAdapter(new AdapterMemCard(_activity, gvData));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
