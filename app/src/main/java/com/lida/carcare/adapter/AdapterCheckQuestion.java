package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.CarInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 检车问题
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterCheckQuestion extends BaseAdapter {

    private Context context;
    private List<CarInfoBean.DataBean.CarInspectBean> data;

    public AdapterCheckQuestion(Context context, List<CarInfoBean.DataBean.CarInspectBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_checkquestion, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(data.get(position).getEntryName());
        viewHolder.tvMark.setText(data.get(position).getCheckRemarks());
        String sratus = data.get(position).getDetectionOpinion();
        if("0".equals(sratus)){
            viewHolder.tvStatu.setText("不良");
        }else{
            viewHolder.tvStatu.setText("正常");
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvMark)
        TextView tvMark;
        @BindView(R.id.tvStatu)
        TextView tvStatu;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
