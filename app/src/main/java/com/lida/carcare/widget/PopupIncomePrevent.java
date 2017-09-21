package com.lida.carcare.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 收入明细录入-支付收入方式
 * Created by Administrator on 2017/7/1.
 */

public class PopupIncomePrevent extends PopupWindow {
    private Context context;
    private ListView listView;
    private List<String> data=new ArrayList<>();
    private PopupIncomePrevent.Adapter adapter;
    private OnItemClickListener listener;

    public PopupIncomePrevent(Context context, View contentView, int width, int height) {
        super(contentView, width, height);
        this.context = context;
        listView = (ListView) contentView.findViewById(R.id.listView);
        adapter = new PopupIncomePrevent.Adapter();
        listView.setAdapter(new PopupIncomePrevent.Adapter());
        data.add("现金");
        data.add("刷卡");
        data.add("微信");
        data.add("支付宝");
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_spinner,null);
            tv.setText(data.get(position));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.doNext(position,tv.getText().toString().trim());
                    }
                }
            });
            return tv;
        }
    }
}
