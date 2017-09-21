package com.lida.carcare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterListItem;
import com.lida.carcare.adapter.AdapterListRecord;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarRecordBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 检车记录
 * Created by WeiQingFeng on 2017/4/14.
 */

public class FragmentCheckCarHistory extends BaseFragment {
    @BindView(R.id.listViewItem)
    InnerListView listViewItem;
    @BindView(R.id.listViewRecord)
    InnerListView listViewRecord;
    Unbinder unbinder;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDaysRemaining)
    TextView tvDaysRemaining;

    private List<CarRecordBean.DataBean.CarInspectsBean> carInspects;
    private List<CarRecordBean.DataBean.CarMaintainRecordBean> carMaintainRecord;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppUtil.getCarApiClient(ac).selectPickUpTheCarRecord(getArguments().getString("documentId"), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_checkcarhistory, null);
        unbinder = ButterKnife.bind(this, v);
        listViewItem.setDivider(null);
        return v;
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            CarRecordBean bean = (CarRecordBean) res;
            tvTime.setText(bean.getData().getMaturityDate());
            String daysRemaining = bean.getData().getDaysRemaining();
           /* if("".equals(daysRemaining)||daysRemaining==null){
                tvDaysRemaining.setText("剩余0天");
            }else {
                tvDaysRemaining.setText(""+ daysRemaining +"天");
            }*/
           tvDaysRemaining.setText(daysRemaining);
            carInspects = bean.getData().getCarInspects();
            carMaintainRecord = bean.getData().getCarMaintainRecord();
            if(carInspects!=null){
                listViewItem.setAdapter(new AdapterListItem(_activity, carInspects));
            }
            if(carMaintainRecord!=null){
                listViewRecord.setAdapter(new AdapterListRecord(_activity, carMaintainRecord));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
