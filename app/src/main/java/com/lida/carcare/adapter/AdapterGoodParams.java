package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 增加商品-商品参数
 * Created by WeiQingFeng on 2017/4/7.
 */

public class AdapterGoodParams extends BaseAdapter {

    private Context context;
    private List<String> names;
    private List<String> params;

    public AdapterGoodParams(Context context, List<String> names, List<String> params) {
        this.context = context;
        this.names = names;
        this.params = params;
    }

    @Override
    public int getCount() {
        return names.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goodparams, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvTitle.setText(names.get(position));
        viewHolder.tvContent.setText(params.get(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
