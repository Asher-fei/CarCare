package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityServiceList;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServerClassABean;
import com.lida.carcare.bean.ServerClassBBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.ItemCanClick2;
import com.lida.carcare.widget.ItemCanClick3;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择技师
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterChooseServerELView extends BaseExpandableListAdapter {

    private Activity context;
    private List<ServerClassABean.DataBean> parentData;
    private ExpandableListView listView;
    private Map<String, List<ServerClassBBean.DataBean>> data = new HashMap<>();
    private AppContext ac;

    public AdapterChooseServerELView(Activity context, ExpandableListView listView, List<ServerClassABean.DataBean> parent, Map<String, List<ServerClassBBean.DataBean>> data) {
        this.context = context;
        this.parentData = parent;
        this.listView = listView;
        this.data = data;
        ac = (AppContext) context.getApplication();
    }

    @Override
    public int getGroupCount() {
        return parentData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(data.get(parentData.get(groupPosition).getId())!=null){
            return data.get(parentData.get(groupPosition).getId()).size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(parentData.get(groupPosition).getId()).get(childPosition);
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        ParentHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chooseserver_parent, null);
            holder = new ParentHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ParentHolder) convertView.getTag();
        }
        holder.itemCanClick.setTitle(parentData.get(groupPosition).getRolename());
        holder.itemCanClick.setCount(parentData.get(groupPosition).getCount()+"人");
        holder.itemCanClick.setOnItemClick(new ItemCanClick3.OnItemClick() {
            @Override
            public void onItemOpen(ItemCanClick3 itemCanClick) {
                listView.expandGroup(groupPosition);
                if(data.get(parentData.get(groupPosition).getId())==null){
                    AppUtil.getCarApiClient(ac).getServerClassB(parentData.get(groupPosition).getId(), new BaseApiCallback(){
                        @Override
                        public void onApiSuccess(NetResult res, String tag) {
                            super.onApiSuccess(res, tag);
                            if(res.isOK()){
                                ServerClassBBean bean = (ServerClassBBean) res;
                                data.put(parentData.get(groupPosition).getId(),bean.getData());
                                notifyDataSetChanged();
                            }
                        }
                    });
                }
            }

            @Override
            public void onItemClose(ItemCanClick3 itemCanClick) {
                listView.collapseGroup(groupPosition);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chooseserver_child, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.cbName.setText(data.get(parentData.get(groupPosition).getId()).get(childPosition).getRolename());
//        holder.llItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.cbName.isChecked()){
//
//                }
//            }
//        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class ParentHolder {
        @BindView(R.id.itemCanClick)
        ItemCanClick3 itemCanClick;

        ParentHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.cbName)
        CheckBox cbName;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
