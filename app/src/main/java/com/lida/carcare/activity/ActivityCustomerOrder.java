package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.widget.DialogAddOrder;
import com.lida.carcare.widget.DialogDeleteOrder;
import com.lida.carcare.widget.SharepreferenceUtils;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车主嘱咐
 * Created by Administrator on 2017/4/5.
 */

public class ActivityCustomerOrder extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.etContent)
    EditText etContent;

    private List<String> orders = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerorder);
        ButterKnife.bind(this);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        //topbar.setTitle("车主嘱咐");
        topbar.setTitle("车辆备注");
        topbar.setRightText("确定", listener);
        topbar.setBackgroundColor(getResources().getColor(R.color.topbar));
        String order = SharepreferenceUtils.getPrefString(_activity, "order", "");
        if(!"".equals(order)){
            String[] split = order.split(";");
            for (int i = 0; i < split.length; i++) {
                orders.add(split[i]);
            }
        }
        myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String trim = etContent.getText().toString().trim();
            Intent intent=new Intent();
            intent.putExtra("content",trim);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < orders.size(); i++) {
                stringBuilder.append(orders.get(i)+";");
            }
            SharepreferenceUtils.setPrefString(_activity,"order",stringBuilder.toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    };


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
                LogUtils.e(orders);
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
                    String oldStr = etContent.getText().toString().trim();
                    etContent.setText(oldStr+orders.get(position));
                    etContent.setSelection(etContent.getText().length());
                }
            });
            return convertView;
        }

    }

    class ViewHolder {
        TextView tv;
        ImageView iv;
    }
}
