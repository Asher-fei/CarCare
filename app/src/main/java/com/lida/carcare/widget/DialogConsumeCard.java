package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.ConsumeCardBean;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/2.
 */

public class DialogConsumeCard extends Dialog {
    @BindView(R.id.listView)
    InnerListView listView;

    private Context context;
    private TextView textView;
    private String[] sArr  = null;
    List<ConsumeCardBean.DataBean> list;

    public DialogConsumeCard(Context context, TextView textView, List<ConsumeCardBean.DataBean> list) {
        super(context, R.style.diy_dialog);
        this.textView = textView;
        this.list = list;
        init(context);
    }

    public DialogConsumeCard(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_choosegrade, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        listView.setAdapter(new DialogConsumeCard.Adapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(list.get(position).getConsumeCardNo());
                textView.setTag(list.get(position).getId());
                dismiss();
            }
        });
    }


    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list == null ? 0 : position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_grade, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
           // viewHolder.tv.setText(data.get(position));
            viewHolder.tv.setText(list.get(position).getConsumeCardNo());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tv)
            TextView tv;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
