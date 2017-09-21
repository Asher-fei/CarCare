package com.lida.carcare.widget.rightdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.IndexList.CityBean;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;

import java.util.List;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class AdapterDialogA extends RecyclerView.Adapter<AdapterDialogA.ViewHolder> {
    protected Activity mContext;
    protected List<CityBean> mDatas;
    protected LayoutInflater mInflater;
    private Dialog dialog;

    private AppContext ac;
    private int p;

    public AdapterDialogA(Activity mContext, List<CityBean> mDatas,int p, Dialog dialog) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.dialog=dialog;
        this.p = p;
        ac = (AppContext) mContext.getApplication();
        mInflater = LayoutInflater.from(mContext);
    }

    public List<CityBean> getDatas() {
        return mDatas;
    }

    public AdapterDialogA setDatas(List<CityBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public AdapterDialogA.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(final AdapterDialogA.ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getCarApiClient(ac).getGoodsByCode(ac.shopId,mDatas.get(position).getCode(),new BaseApiCallback(){
                    @Override
                    public void onApiSuccess(NetResult res, String tag) {
                        super.onApiSuccess(res, tag);
                        if(res.isOK()){
                            ServiceGoodBean bean = (ServiceGoodBean) res;
                            new DialogClassB(mContext, bean.getData().getJfomService(), p).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            content = itemView.findViewById(R.id.content);
        }
    }
}
