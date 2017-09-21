package com.lida.carcare.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityStaffDetail2;
import com.lida.carcare.bean.WorkerTreeBean;
import com.lida.carcare.widget.ItemCanClick2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/19.
 */

public class AdapterPositionManage extends BaseExpandableListAdapter
{
    private Context context;
    private ExpandableListView listView;
    private WorkerTreeBean bean;

    public AdapterPositionManage(Context context, ExpandableListView listView, WorkerTreeBean bean)
    {
        this.context = context;
        this.listView = listView;
        this.bean = bean;
    }

    @Override
    public int getGroupCount()
    {
        return bean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return bean.getData().get(groupPosition).getTsBaseUser().size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return bean.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return bean.getData().get(groupPosition).getTsBaseUser().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        PViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            viewHolder = new PViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (PViewHolder) convertView.getTag();
        }
        viewHolder.itemCanClick.setTitle(bean.getData().get(groupPosition).getDepartname());
        viewHolder.itemCanClick.setOnItemClick(new ItemCanClick2.OnItemClick()
        {
            @Override
            public void onItemOpen(ItemCanClick2 itemCanClick)
            {
                listView.expandGroup(groupPosition);
            }

            @Override
            public void onItemClose(ItemCanClick2 itemCanClick)
            {
                listView.collapseGroup(groupPosition);
            }
        });
        viewHolder.itemCanClick.getCountView().setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        final CViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_position_child, null);
            viewHolder = new CViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (CViewHolder) convertView.getTag();
        }
        viewHolder.tvCommission.setText("提成占比:" + bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).getiCode());
        viewHolder.tvName.setText(bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).getUsername());
        viewHolder.llItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).getId();
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                Intent intent = new Intent(context, ActivityStaffDetail2.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return false;
    }

    static class PViewHolder
    {
        @BindView(R.id.itemCanClick)
        ItemCanClick2 itemCanClick;

        PViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

    static class CViewHolder
    {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvCommission)
        TextView tvCommission;
        @BindView(R.id.llItem)
        View llItem;


        CViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
