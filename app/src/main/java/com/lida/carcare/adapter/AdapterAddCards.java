package com.lida.carcare.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityAddCards;
import com.lida.carcare.widget.BaseTextWatcher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/1.
 */

public class AdapterAddCards extends BaseAdapter {

    List<ActivityAddCards.Item> list ;
    Context context;

    private onDeleteListener listener;

    public AdapterAddCards(List<ActivityAddCards.Item> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ActivityAddCards.Item getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_add_cards, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(list.get(position).getName());
        viewHolder.edtCount.setText(list.get(position).getCount());
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                if(listener!=null){
                    listener.delete();
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.edtCount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(viewHolder.edtCount.getText().toString()!=null) {
                        list.get(position).setCount(viewHolder.edtCount.getText().toString());
                    }
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.edtCount)
        EditText edtCount;
        @BindView(R.id.delete)
        ImageView delete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnDeleteListener(onDeleteListener listener){
        this.listener = listener;
    }

    public interface onDeleteListener{
        void delete();
    }


}
