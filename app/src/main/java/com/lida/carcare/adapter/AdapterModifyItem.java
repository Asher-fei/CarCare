package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 变更项目
 * Created by WeiQingFeng on 2017/4/7.
 */

public class AdapterModifyItem extends BaseAdapter {

    private Context context;

    public AdapterModifyItem(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activitymodifyitem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvItemName)
        TextView tvItemName;
        @BindView(R.id.tvNumber)
        TextView tvNumber;
        @BindView(R.id.btnChange)
        Button btnChange;
        @BindView(R.id.tvDate)
        TextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
