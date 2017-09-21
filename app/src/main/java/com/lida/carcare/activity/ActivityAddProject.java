package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.lida.carcare.R;
import com.lida.carcare.widget.DialogAddOrder;
import com.lida.carcare.widget.DialogDeleteOrder;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加项目
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityAddProject extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.tvGoods)
    TextView tvGoods;
    @BindView(R.id.tvServices)
    TextView tvServices;

    private List<String> orders = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);
        ButterKnife.bind(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("添加项目");
        myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);
    }

    @OnClick({R.id.tvSearch, R.id.tvGoods, R.id.tvServices})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSearch:
                break;
            case R.id.tvGoods:
                break;
            case R.id.tvServices:
                break;
        }
    }

    public class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 6;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_activityorder, null);
                viewHolder = new ViewHolder();
                viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (orders.size() > position) {
                viewHolder.iv.setVisibility(View.GONE);
                viewHolder.tv.setVisibility(View.VISIBLE);
                viewHolder.tv.setText(orders.get(position));
            } else {
                viewHolder.tv.setVisibility(View.GONE);
                viewHolder.iv.setVisibility(View.VISIBLE);
            }
            viewHolder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DialogAddOrder(_activity, orders, myAdapter).show();
                }
            });
            viewHolder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    new DialogDeleteOrder(_activity, orders, position, myAdapter).show();
                    return true;
                }
            });
            viewHolder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return convertView;
        }
        class ViewHolder {
            TextView tv;
            ImageView iv;
        }
    }
}
