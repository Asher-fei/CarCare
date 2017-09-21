package com.lida.carcare.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.AddCustomProjectBean;
import com.lida.carcare.bean.BannerBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加新车--自定义项目
 * Created by xkr on 2017/9/15.
 */

public class AdapterAddCustomProject extends BaseAdapter {
    private Context context;
    private List<AddCustomProjectBean> data;

    public AdapterAddCustomProject(Context context, List<AddCustomProjectBean> data)
    {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_add_custom_project, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(data.get(position).getName());
        viewHolder.count.setText("x"+data.get(position).getCount());
        viewHolder.price.setText("¥"+data.get(position).getPrice());
        viewHolder.tvReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }



    static class ViewHolder
    {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.tvReduce)
        ImageView tvReduce;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
