package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCarDetailInfo;
import com.lida.carcare.bean.ConsumBean;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消费记录
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterRecordOfConsum extends BaseAdapter {

    private Context context;
    private List<ConsumBean.DataBean.DocumentsBean> data;

    public AdapterRecordOfConsum(Context context, List<ConsumBean.DataBean.DocumentsBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recordofconsum, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTime.setText(data.get(position).getDeliveryTime());
        viewHolder.tvItem.setText(data.get(position).getProjectName());
        viewHolder.tvMoney.setText("￥"+data.get(position).getConsumptionAmount());
        viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UIHelper.jump((Activity) context, ActivityCarDetailInfo.class);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvMoney)
        TextView tvMoney;
        @BindView(R.id.tvItem)
        TextView tvItem;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
