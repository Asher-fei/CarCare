package com.lida.carcare.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.ServiceGoodBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/13.
 */

public class AdapterServiceGood extends BaseAdapter
{
    private List<ServiceGoodBean.DataBean.JfomServiceBean> data = null;

    private onChildClickListener listener = null;

    public AdapterServiceGood()
    {
    }

    public AdapterServiceGood(List<ServiceGoodBean.DataBean.JfomServiceBean> data)
    {
        this.data = data;
    }

    public void setData(List<ServiceGoodBean.DataBean.JfomServiceBean> data)
    {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return data == null ? null : data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ChildHolder holder;
        ServiceGoodBean.DataBean.JfomServiceBean bean = data.get(position);
        if (convertView == null)
        {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_three_expand, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else
        {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.tvName.setText(bean.getName());
        holder.tvName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ServiceGoodBean.DataBean.JfomServiceBean bean = data.get(position);
                if (listener != null)
                {
                    listener.onClick(bean);
                }
//                Log.d("AdapterServiceGood", "onClick bean = " + bean.getName());
            }
        });
        return convertView;
    }

    class ChildHolder
    {
        @BindView(R.id.tvContent)
        TextView tvName;

        ChildHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

    public interface onChildClickListener
    {
        void onClick(ServiceGoodBean.DataBean.JfomServiceBean bean);
    }

    public void setOnChildClickListener(onChildClickListener listener)
    {
        this.listener = listener;
    }
}
