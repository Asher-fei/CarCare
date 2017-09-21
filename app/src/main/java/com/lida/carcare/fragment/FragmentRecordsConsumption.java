package com.lida.carcare.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterRecordOfConsum;
import com.lida.carcare.adapter.AdapterRecordsConsumption;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ConsumBean;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 车辆详情--消费记录
 * Created by Administrator on 2017/7/17.
 */

public class FragmentRecordsConsumption extends BaseFragment {


    @BindView(R.id.listView)
    ListView listView;
    Unbinder unbinder;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvAllCount)
    TextView tvAllCount;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvAllMoney)
    TextView tvAllMoney;

    private List<ConsumBean.DataBean.DocumentsBean> documents;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppUtil.getCarApiClient(ac).selectRecordsOfConsumption(getArguments().getString("carNo"), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_records_consumption, null);
        unbinder = ButterKnife.bind(this, v);
        listView.setDividerHeight(20);
        return v;
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            ConsumBean bean = (ConsumBean) res;
            tvCount.setText(bean.getData().getTheNumberOfTimesInThisTear());
            tvMoney.setText(bean.getData().getTheAmountOfConsumptionThisYear());
            tvAllCount.setText(bean.getData().getRecordTntoTheShop());
            tvAllMoney.setText(bean.getData().getHistoricalConsumptionAmount());
            documents = bean.getData().getDocuments();
            if(documents!=null){
                //listView.setAdapter(new AdapterRecordOfConsum(_activity,documents));
                listView.setAdapter(new AdapterRecordsConsumption(_activity,documents));
            }
        }
    }
}
