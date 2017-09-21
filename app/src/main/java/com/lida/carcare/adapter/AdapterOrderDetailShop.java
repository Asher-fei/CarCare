package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.OrderPlaceDetailBean;
import com.midian.base.bean.NetResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单详情商品列表
 * Created by xkr on 2017/7/24.
 */

public class AdapterOrderDetailShop extends BaseAdapter {

    List<OrderPlaceDetailBean.DataBean.OrderPlaceGoodsListBean> list;
    Context context;

    public AdapterOrderDetailShop(List<OrderPlaceDetailBean.DataBean.OrderPlaceGoodsListBean> list,
            Context context){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public OrderPlaceDetailBean.DataBean.OrderPlaceGoodsListBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_detail_shop, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OrderPlaceDetailBean.DataBean.OrderPlaceGoodsListBean bean = list.get(position);
        viewHolder.name.setText(bean.getName()==null?"":bean.getName());
        viewHolder.amount.setText(bean.getPrice()==null?"":bean.getPrice()+" x "+bean.getGoodNum());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.amount)
        TextView amount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
