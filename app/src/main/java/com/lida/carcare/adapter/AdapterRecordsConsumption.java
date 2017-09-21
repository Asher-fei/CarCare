package com.lida.carcare.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.ConsumBean;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xkr on 2017/7/18.
 */

public class AdapterRecordsConsumption extends BaseAdapter {
    private Context context;
    private List<ConsumBean.DataBean.DocumentsBean> data;

    public AdapterRecordsConsumption(Context context, List<ConsumBean.DataBean.DocumentsBean> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragment_records_consumption, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.deliveryTime.setText(data.get(position).getDeliveryTime());
        viewHolder.consumptionAmount.setText("￥"+data.get(position).getConsumptionAmount());
        viewHolder.layout_content_service.setTag(data.get(position).getId());

            if (!data.get(position).getProjectName().equals("")) {

                if(viewHolder.layout_content_service.getTag().equals(data.get(position).getId())) {
                    String pattern = ",";
                    Pattern pat = Pattern.compile(pattern);
                    final String[] rs = pat.split(data.get(position).getProjectName());
                    viewHolder.layout_content_service.removeAllViews();
                    for (int i = 0; i < rs.length; i++) {
                        View layout = LayoutInflater.from(context).inflate(R.layout.item_tv_service, null);
                        TextView tv = (TextView) layout.findViewById(R.id.tv);
                        tv.setText(rs[i]);
                        viewHolder.layout_content_service.addView(layout);
                    }
                }
            }


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.deliveryTime)
        TextView deliveryTime;
        @BindView(R.id.consumptionAmount)
        TextView consumptionAmount;
        @BindView(R.id.layout_content_service)
        TableLayout layout_content_service;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
