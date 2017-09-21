package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.SpendingDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/1.
 */

public class AdapterSpendingDetail extends BaseAdapter {

    List<SpendingDetailBean.DataBean.CarIncomeSpendingBean> list;
    Context context;

    public AdapterSpendingDetail(List<SpendingDetailBean.DataBean.CarIncomeSpendingBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SpendingDetailBean.DataBean.CarIncomeSpendingBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_spending_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.price.setText("¥ "+list.get(position).getPrice());
        viewHolder.remake.setText(list.get(position).getRemake());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.remake)
        TextView remake;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
