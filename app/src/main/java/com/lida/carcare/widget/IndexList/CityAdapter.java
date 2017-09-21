package com.lida.carcare.widget.IndexList;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.widget.CarClassDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    protected Context mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    private CarClassDialog.CAdapter adapter;
    private ListView listView;
    private TextView tv;

    public CityAdapter(Context mContext, List<CityBean> mDatas, CarClassDialog.CAdapter adapter,
                       ListView listView, TextView tv) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.adapter = adapter;
        this.listView = listView;
        this.tv = tv;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public CityAdapter setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final CityAdapter.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("onclick");
                LogUtils.e(mDatas.get(position).getCarTrees());
                tv.setText(cityBean.getCity());
                tv.setTag(cityBean.getId());
                listView.setVisibility(View.VISIBLE);
                adapter.setData(mDatas.get(position).getCarTrees());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
//        ImageView avatar;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
//            avatar = (ImageView) itemView.findViewById(R.userId.ivAvatar);
            content = itemView.findViewById(R.id.content);
        }
    }
}
