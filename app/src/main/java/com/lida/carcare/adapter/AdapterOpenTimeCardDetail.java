package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.OpenTimeCardDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/1.
 */

public class AdapterOpenTimeCardDetail extends BaseAdapter {

    List<OpenTimeCardDetailBean.DataBean.OnceCardProjectListBean> list;
    Context context;

    public AdapterOpenTimeCardDetail(List<OpenTimeCardDetailBean.DataBean.OnceCardProjectListBean> list,
            Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public OpenTimeCardDetailBean.DataBean.OnceCardProjectListBean getItem(int position) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_open_time_card_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.projectCount.setText(list.get(position).getProjectCount());
        viewHolder.projectName.setText(list.get(position).getProjectName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.projectName)
        TextView projectName;
        @BindView(R.id.projectCount)
        TextView projectCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
