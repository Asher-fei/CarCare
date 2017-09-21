package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCarDetailInfo;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 报价
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterOffer extends BaseAdapter {

    private Context context;

    public AdapterOffer(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 1;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragmentoffer, null);
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
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvDiscount)
        TextView tvDiscount;
        @BindView(R.id.tvCount)
        TextView tvCount;
//        @BindView(R.userId.tvAllMoney)
//        TextView tvAllMoney;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
