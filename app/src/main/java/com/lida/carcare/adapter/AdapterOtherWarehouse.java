package com.lida.carcare.adapter;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.QueryAllGoodsBean;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.DialogChooseStorage;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/7.
 */

public class AdapterOtherWarehouse extends BaseAdapter {

    List<QueryAllGoodsBean.DataBean> list;
    Context context;
    private AppContext ac;
    StorageListBean bean;
    public  AdapterOtherWarehouse(Context context,List<QueryAllGoodsBean.DataBean> list,
            StorageListBean bean){
        this.list = list;
        this.context = context;
        ac = (AppContext) context.getApplicationContext();
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public QueryAllGoodsBean.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_other_warehouse, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.llPrice.setVisibility(View.VISIBLE);
        viewHolder.tvName.setText(list.get(position).getName());
        viewHolder.tvCode.setText("编码："+list.get(position).getInternationalCode());
        viewHolder.etPrice.setText(list.get(position).getPrice());
        viewHolder.etCount.setTag(list.get(position).getId());
        viewHolder.etCount.setOnNumberChangeListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if(list.get(position).getId().equals(viewHolder.etCount.getTag())) {
                    list.get(position).setCount(s.toString());
                }
            }
        });
        viewHolder.etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    list.get(position).setPrice(viewHolder.etPrice.getText().toString());
                }
            }
        });
        ac.setImage(viewHolder.iv, Constant.BASE + list.get(position).getProductImg());
        viewHolder.tvStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean!=null){
                    DialogChooseStorage dialogChooseStorage = new DialogChooseStorage(context, viewHolder.tvStorage, bean.getData());
                    dialogChooseStorage.setOnItemClickListener(new DialogChooseStorage.onItemClickListener() {
                        @Override
                        public void onItemClicked(int p) {
                            list.get(position).setRepertoryId(bean.getData().get(p).getId());
                        }
                    });
                    dialogChooseStorage.show();
                }
            }
        });


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvCode)
        TextView tvCode;
        @BindView(R.id.etPrice)
        EditText etPrice;
        @BindView(R.id.etCount)
        NumberWidget etCount;
        @BindView(R.id.llPrice)
        LinearLayout llPrice;
        @BindView(R.id.tvStorage)
        TextView tvStorage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
