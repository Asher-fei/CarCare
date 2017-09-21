package com.lida.carcare.widget.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class PopupNotice extends PopupWindow {

    private Context context;
    private ListView listView;
    private List<String> data=new ArrayList<>();
    private OnItemClickListener listener;

    public PopupNotice(Context context, View contentView, int width, int height) {
        super(contentView, width, height);
        this.context = context;
        listView = (ListView) contentView.findViewById(R.id.listView);
        listView.setAdapter(new Adapter());
        data.add("创建提醒");
        data.add("已关闭提醒");
        data.add("提醒设置");
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    class Adapter extends BaseAdapter{

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
            final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.spinner_item_member,null);
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
