package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCarDetail;
import com.lida.carcare.activity.ActivityCheckDetail;
import com.lida.carcare.bean.CarRecordBean;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 检测记录-记录清单
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterListRecord extends BaseAdapter {

    private Activity context;
    private List<CarRecordBean.DataBean.CarMaintainRecordBean> data;

    public AdapterListRecord(Activity context, List<CarRecordBean.DataBean.CarMaintainRecordBean> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recordlist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvMile.setText("进店里程："+data.get(position).getMileage());
//        viewHolder.tvDes.setText(data.get(position).ge);
        viewHolder.tvTime.setText(data.get(position).getMaintainDate());
        viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("Onclick");
                UIHelper.jump(context, ActivityCheckDetail.class);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvMile)
        TextView tvMile;
        @BindView(R.id.tvDes)
        TextView tvDes;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
