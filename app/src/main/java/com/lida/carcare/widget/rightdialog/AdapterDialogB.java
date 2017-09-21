package com.lida.carcare.widget.rightdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.widget.IndexList.CityBean;

import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class AdapterDialogB extends RecyclerView.Adapter<AdapterDialogB.ViewHolder> {
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    private Dialog dialog;

    public AdapterDialogB(Context mContext, List<CityBean> mDatas, Dialog dialog) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.dialog=dialog;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public AdapterDialogB setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public AdapterDialogB.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_cb, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdapterDialogB.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.cb.setText(cityBean.getCity());
        if(mDatas.get(position).isSelect()){
            holder.cb.setChecked(true);
        }else{
            holder.cb.setChecked(false);
        }
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDatas.get(position).setSelect(isChecked);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb;

        public ViewHolder(View itemView) {
            super(itemView);
            cb = (CheckBox) itemView.findViewById(R.id.cb);
        }
    }
}
