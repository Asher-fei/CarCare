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
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.AppContext;
import com.midian.base.app.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/6.
 */

public class AdapterInventoryVerificationAdd extends BaseAdapter {

    private Context context;
    private List<SelectOutboundGoodslistBean.DataBean> data;
    private AppContext ac;

    public AdapterInventoryVerificationAdd(Context context, List<SelectOutboundGoodslistBean.DataBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? null:position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_purchase, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.llPrice.setVisibility(View.VISIBLE);
        viewHolder.etCount.setTag(data.get(position).getId());
        viewHolder.etCount.setOnNumberChangeListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if(data.get(position).getId().equals(viewHolder.etCount.getTag())) {
                      data.get(position).setSelectCount(s.toString());
                }
            }
        });
        viewHolder.etPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                   // data.get(position).setPrice(viewHolder.etPrice.getText().toString());
                }
            }
        });
        ac.setImage(viewHolder.iv, Constant.BASE + data.get(position).getProductImg());
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
