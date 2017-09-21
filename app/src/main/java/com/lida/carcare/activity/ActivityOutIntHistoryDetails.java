package com.lida.carcare.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.OutIntHistoryDetailsBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityOutIntHistoryDetails extends BaseActivity {

    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvStatu)
    TextView tvStatu;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.tvTime)
    TextView tvTime;
    InnerListView listView;
    public Adapter adapter;
    private List<OutIntHistoryDetailsBean.DataBean.CarEntryRecordsGoodBean>  data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_int_history_details);
        ButterKnife.bind(this);
        listView = (InnerListView) findView(R.id.listView);

        AppUtil.getCarApiClient(ac).getOupIntHistoryDetails(mBundle.getString("id"),this);
    }

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
        if (res.isOK()){
            if("getOupIntHistoryDetails".equals(tag)){
                OutIntHistoryDetailsBean bean = (OutIntHistoryDetailsBean) res;
                if (bean != null){
                    tvNum.setText(bean.getData().getId());
                    switch (bean.getData().getState()){
                        //状态 0：采购入库，1：其他入库，2：其他出库
                        case "0":
                            tvStatu.setText("采购入库");
                            break;
                        case "1":
                            tvStatu.setText("其他入库");
                            break;
                        case "2":
                            tvStatu.setText("其他出库");
                            break;

                    }
                    tvMoney.setText(bean.getData().getPrice());
                    tvTime.setText(bean.getData().getCreatedate());
                    data = bean.getData().getCarEntryRecordsGood();
                    adapter = new Adapter(this);
                    listView.setAdapter(adapter);

                }
            }
        }
    }

    class Adapter extends BaseAdapter {

        private Context context;
        public Adapter(Context context) {
            super();
            this.context = context;
        }

        @Override
        public int getCount() {
            return data == null ?0:data.size();
        }

        @Override
        public Object getItem(int position) {
            return  data == null ? null : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_out_int_history_details, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvName.setText(data.get(position).getName());
            viewHolder.tvNameNum.setText(data.get(position).getGoodNum());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tvName)
            TextView tvName;
            @BindView(R.id.tvNameNum)
            TextView tvNameNum;


            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
