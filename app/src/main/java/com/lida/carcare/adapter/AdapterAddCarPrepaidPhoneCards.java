package com.lida.carcare.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xkr on 2017/9/21.
 */

public class AdapterAddCarPrepaidPhoneCards extends BaseAdapter {

    List<String> list;
    Context context;

    public AdapterAddCarPrepaidPhoneCards(List<String> list, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    class ViewHolder{

    }
}
