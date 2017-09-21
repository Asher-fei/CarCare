package com.lida.carcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.bean.CarInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提醒事项
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterNotice extends BaseAdapter {

    private Context context;
    private List<CarInfoBean.DataBean.CarRemindBean> data;

    public AdapterNotice(Context context, List<CarInfoBean.DataBean.CarRemindBean> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_notice, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(data.get(position).getReminderDetails());
        viewHolder.tvRemainTime.setText(data.get(position).getMaturityDate()+"到期");
        viewHolder.tvTime.setText(data.get(position).getDaysRemaining());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvRemainTime)
        TextView tvRemainTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
