package com.lida.carcare.adapter;

import android.content.Context;
import android.media.tv.TvRecordingClient;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.StaffratioBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置员工提成
 * Created by xkr on 2017/8/16.
 */

public class AdapterStaffratio extends BaseAdapter {

    List<StaffratioBean> list;
    Context context;

    public AdapterStaffratio(List<StaffratioBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_staffration, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.count.setText(list.get(position).getProportion());
        viewHolder.count.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(list.size()>position) {
                        if (!viewHolder.count.getText().toString().equals("")) {
                            list.get(position).setProportion(viewHolder.count.getText().toString());
                        }
                    }
                }
            }
        });
        viewHolder.tvReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.count)
        EditText count;
        @BindView(R.id.tvReduce)
        ImageView tvReduce;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
