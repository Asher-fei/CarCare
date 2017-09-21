package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.CustomerDetailBean;
import com.lida.carcare.widget.DialogCall;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 客户详情联系人列表
 * Created by Administrator on 2017/7/17.
 */

public class AdapterCustomerDetail extends BaseAdapter {

    List<CustomerDetailBean.DataBean.CustomerContactListBean> list;
    Context context;

    public AdapterCustomerDetail( List<CustomerDetailBean.DataBean.CustomerContactListBean> list,
            Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CustomerDetailBean.DataBean.CustomerContactListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_customer_detail_innerlist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.contactName.setText(list.get(position).getContactName());
        viewHolder.contactMobile.setText(list.get(position).getContactMobile());
        viewHolder.contactRelation.setText(list.get(position).getContactRelation());
        viewHolder.contactMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewHolder.contactMobile.getText().toString().equals("")){
                    new DialogCall(context,viewHolder.contactMobile.getText().toString()).show();
                }
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.contactName)
        TextView contactName;
        @BindView(R.id.contactMobile)
        TextView contactMobile;
        @BindView(R.id.contactRelation)
        TextView contactRelation;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
