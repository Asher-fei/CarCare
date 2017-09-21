package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lida.carcare.R;

/**
 *充值 添加项目
 * Created by WeiQingFeng on 2017/4/12.
 */

public class AdapterAddProject extends BaseAdapter {

    private Context context;

    public AdapterAddProject(Context context) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_addproject,null);
        return convertView;
    }
}
