package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.bean.WorkerTreeBean;
import com.lida.carcare.widget.ItemCanClick2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  员工树形列表
 * Created by Administrator on 2017/6/12.
 */

public class AdapterToChooseWorker extends BaseExpandableListAdapter {

    private Context context;
    private ExpandableListView listView;
    private WorkerTreeBean bean;

    public AdapterToChooseWorker(Context context,ExpandableListView listView, WorkerTreeBean bean) {
        this.context = context;
        this.listView = listView;
        this.bean = bean;
    }

    @Override
    public int getGroupCount() {
        return bean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return bean.getData().get(groupPosition).getTsBaseUser().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return bean.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bean.getData().get(groupPosition).getTsBaseUser().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        PViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            viewHolder = new PViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PViewHolder) convertView.getTag();
        }
        viewHolder.itemCanClick.setTitle(bean.getData().get(groupPosition).getDepartname());
        viewHolder.itemCanClick.setOnItemClick(new ItemCanClick2.OnItemClick() {
            @Override
            public void onItemOpen(ItemCanClick2 itemCanClick) {
                listView.expandGroup(groupPosition);
            }

            @Override
            public void onItemClose(ItemCanClick2 itemCanClick) {
                listView.collapseGroup(groupPosition);
            }
        });
        viewHolder.itemCanClick.getCountView().setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final CViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chooseserver_child, null);
            viewHolder = new CViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (CViewHolder) convertView.getTag();
        }
        viewHolder.cbName.setText(bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).getUsername());
        if(bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).isSelect()){
            viewHolder.cbName.setChecked(true);
        }else{
            viewHolder.cbName.setChecked(false);
        }
        viewHolder.cbName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).isSelect()){
                    bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).setSelect(false);
                }else{
                    bean.getData().get(groupPosition).getTsBaseUser().get(childPosition).setSelect(true);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class PViewHolder {
        @BindView(R.id.itemCanClick)
        ItemCanClick2 itemCanClick;

        PViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class CViewHolder {
        @BindView(R.id.cbName)
        CheckBox cbName;

        CViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
