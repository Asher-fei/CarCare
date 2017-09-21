package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.GetCarnewDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员卡
 * Created by WeiQingFeng on 2017/4/7.
 */

public class AdapterMemCard extends BaseAdapter {

    private Context context;
    private List<GetCarnewDetailBean.DataBean.ConsumeCardBean> data;

    public AdapterMemCard(Context context, List<GetCarnewDetailBean.DataBean.ConsumeCardBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_memcard, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCardType.setText(data.get(position).getConsumeCardName());
        viewHolder.tvBalance.setText(data.get(position).getResidualAmount());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvCardType)
        TextView tvCardType;
        @BindView(R.id.tvBalance)
        TextView tvBalance;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
