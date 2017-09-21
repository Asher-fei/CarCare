package com.lida.carcare.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.ItemCanClick2;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterThreeExpandListView extends BaseExpandableListAdapter
{

    private Activity context;
    private List<ServiceBean.DataBean> parentData;
    private ExpandableListView listView;
    private Map<String, List<ServiceBean.DataBean>> data = new HashMap<>();//二级分类
    private AppContext ac;
    public boolean refresh = true;
    private AdapterServiceGood.onChildClickListener listener = null;


    public AdapterThreeExpandListView(Activity context, ExpandableListView listView, List<ServiceBean.DataBean> parent)
    {
        this.context = context;
        this.parentData = parent;
        this.listView = listView;
        ac = (AppContext) context.getApplication();
    }

    @Override
    public int getGroupCount()
    {
        return parentData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        if (data.get(parentData.get(groupPosition).getName()) == null)
        {
            return 0;
        } else
        {
            return data.get(parentData.get(groupPosition).getName()).size();
        }
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return data.get(parentData.get(groupPosition).getName());
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        if (data.get(parentData.get(groupPosition).getName()) != null)
        {
            return data.get(parentData.get(groupPosition).getName()).get(childPosition);
        } else
        {
            return null;
        }
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent)
    {
        ParentHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            holder = new ParentHolder(convertView);
            convertView.setTag(holder);
        } else
        {
            holder = (ParentHolder) convertView.getTag();
        }
        holder.itemCanClick.setTitle(parentData.get(groupPosition).getName());
        if (data.get(parentData.get(groupPosition).getName()) != null)
        {
            holder.itemCanClick.setCount(data.get(parentData.get(groupPosition).getName()).size() + "");
        } else
        {
            holder.itemCanClick.setCount("0");
        }
        if (data.get(parentData.get(groupPosition).getName()) == null)
        {
            AppUtil.getCarApiClient(ac).getCategoryByCode(parentData.get(groupPosition).getCode(), ac.shopId, new BaseApiCallback()
            {
                @Override
                public void onApiSuccess(NetResult res, String tag)
                {
                    super.onApiSuccess(res, tag);
                    if (res.isOK())
                    {
                        ServiceBean bean = (ServiceBean) res;
                        if (bean.getData() != null)
                        {
                            List<ServiceBean.DataBean> child = new ArrayList<>();
                            for (int i = 0; i < bean.getData().size(); i++)
                            {
                                child.add(bean.getData().get(i));
                            }
                            data.put(parentData.get(groupPosition).getName(), child);
                            notifyDataSetChanged();
                        }
                    }
                }
            });//获取二级分类
        }
        holder.itemCanClick.setOnItemClick(new ItemCanClick2.OnItemClick()
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
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        final ChildHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        } else
        {
            holder = (ChildHolder) convertView.getTag();
        }
        if (data.get(parentData.get(groupPosition).getName()) != null)
        {
            holder.tvName.setText(data.get(parentData.get(groupPosition).getName()).get(childPosition).getName());
            holder.tvCount.setText(data.get(parentData.get(groupPosition).getName()).get(childPosition).getCount());
        }
        holder.llItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String code = data.get(parentData.get(groupPosition).getName()).get(childPosition).getCode();
                AppUtil.getCarApiClient(ac).getGoodsByCode(ac.shopId, code, new BaseApiCallback()
                {
                    @Override
                    public void onApiSuccess(NetResult res, String tag)
                    {
                        ServiceGoodBean bean = (ServiceGoodBean) res;
                        AdapterServiceGood adapter = new AdapterServiceGood(bean.getData().getJfomService());
                        adapter.setOnChildClickListener(listener);
                        holder.llChild.setAdapter(adapter);
//                        new DialogClassB(context, bean.getData().getJfomService(), 0).show();
                    }
                });
            }
        });
        return convertView;
    }

    public void setOnChildClickListener(AdapterServiceGood.onChildClickListener listener)
    {
        this.listener = listener;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return false;
    }

    static class ParentHolder
    {
        @BindView(R.id.itemCanClick)
        ItemCanClick2 itemCanClick;

        ParentHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildHolder
    {
        @BindView(R.id.llChild)
        InnerListView llChild;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvCount)
        TextView tvCount;
        @BindView(R.id.llItem)
        LinearLayout llItem;

        View layout;

        ChildHolder(View view)
        {
            ButterKnife.bind(this, view);
            layout = view;
        }
    }


}
