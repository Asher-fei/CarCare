package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityLookDetail;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 待入库-查看详情
 * Created by Administrator on 2017/6/29.
 */

public class AdapterActivityLookDetail extends BaseAdapter {

    private Context context;
    private List<ActivityLookDetail.TempBean> data;
    private AppContext ac;

    public AdapterActivityLookDetail(Context context, List<ActivityLookDetail.TempBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activitylookdetail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ac.setImage(viewHolder.iv, Constant.BASE+data.get(position).getProductImg());
        viewHolder.tvName.setText(data.get(position).getName()==null?"":data.get(position).getName());
        viewHolder.tvCount.setText("X"+data.get(position).getStock()==null?"":data.get(position).getStock());
        viewHolder.tvPrice.setText("￥"+data.get(position).getPriceStandardBid()==null?"":data.get(position).getPriceStandardBid());
        viewHolder.tvCode.setText("编码："+data.get(position).getInternationalCode()==null?"":data.get(position).getInternationalCode());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvCode)
        TextView tvCode;
        @BindView(R.id.tvPrice)
        TextView tvPrice;
        @BindView(R.id.tvCount)
        TextView tvCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
