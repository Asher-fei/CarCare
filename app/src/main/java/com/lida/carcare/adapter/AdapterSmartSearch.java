package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.GetCarMainTainBean;
import com.lida.carcare.bean.GoodBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加新车根据公里数智能匹配
 * Created by WeiQingFeng on 2017/4/10.
 */

public class AdapterSmartSearch extends BaseAdapter {

    private GetCarMainTainBean data;
    private Context context;

    public AdapterSmartSearch(Context context, GetCarMainTainBean data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.getData().getProjects().size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? 0 : position;
    }

    @Override
    public long getItemId(int position) {
        return data == null ? 0 : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_addcargridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setClickable(false);
        viewHolder.tv.setText(data.getData().getProjects().get(position).getCreateName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
