package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityAddCar;
import com.lida.carcare.bean.ServerHotBean;
import com.lida.carcare.bean.ServiceEditBean;
import com.lida.carcare.bean.ServiceSearchResultBean;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加新车-搜索服务项目
 * Created by xkr on 2017/9/13.
 */

public class AdapterAddcarSearchService extends BaseAdapter {

    List<ServiceSearchResultBean.DataBean> list;
    Context context;

    public AdapterAddcarSearchService(List<ServiceSearchResultBean.DataBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ServiceSearchResultBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_service2, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(list.get(position).getName());
        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getCode().equalsIgnoreCase("A01")) {
                    if (!ActivityAddCar.goodsProject.toString().contains(list.get(position).getName())) {
                        ActivityAddCar.goodsProject.append(list.get(position).getName() + ",");
                        ActivityAddCar.serviceEditData.add(new ServiceEditBean(list.get(position).getId(), "goodsProject", list.get(position).getName(), "1", list.get(position).getServicePrice()));
                        UIHelper.t(context, list.get(position).getName());
                    } else {
                        UIHelper.t(context, "已添加该项目");
                    }
                }else if(list.get(position).getCode().equalsIgnoreCase("A02")){
                    if (!ActivityAddCar.maintainProject.toString().contains(list.get(position).getName())) {
                        ActivityAddCar.maintainProject.append(list.get(position).getName() + ",");
                        ActivityAddCar.serviceEditData.add(new ServiceEditBean(list.get(position).getId(), "maintainProject", list.get(position).getName(), "1", list.get(position).getServicePrice()));
                        UIHelper.t(context, list.get(position).getName());
                    } else {
                        UIHelper.t(context, "已添加该项目");
                    }
                }
                else if(list.get(position).getCode().equalsIgnoreCase("A03")){
                    if (!ActivityAddCar.repairProject.toString().contains(list.get(position).getName())) {
                        ActivityAddCar.repairProject.append(list.get(position).getName() + ",");
                        ActivityAddCar.serviceEditData.add(new ServiceEditBean(list.get(position).getId(), "repairProject", list.get(position).getName(), "1", list.get(position).getServicePrice()));
                        UIHelper.t(context, list.get(position).getName());
                    } else {
                        UIHelper.t(context, "已添加该项目");
                    }
                }
                else if(list.get(position).getCode().equalsIgnoreCase("A04")){
                    if (!ActivityAddCar.refitProject.toString().contains(list.get(position).getName())) {
                        ActivityAddCar.refitProject.append(list.get(position).getName() + ",");
                        ActivityAddCar.serviceEditData.add(new ServiceEditBean(list.get(position).getId(), "refitProject", list.get(position).getName(), "1", list.get(position).getServicePrice()));
                        UIHelper.t(context, list.get(position).getName());
                    } else {
                        UIHelper.t(context, "已添加该项目");
                    }
                }else {
                    UIHelper.t(context, "数据异常，无法快捷添加");
                }
                ActivityAddCar.ServiceEditAdapterRefresh();
            }
        });
        return convertView;
    }

    static class ViewHolder
    {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
