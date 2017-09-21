package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.rightdialog.DialogClassA;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 添加新车 服务项目
 * Created by WeiQingFeng on 2017/4/7.
 */

public class AdapterProject extends BaseAdapter {

    private Activity context;
    private ServiceBean bean;
    private AppContext ac;

    public AdapterProject(Activity context, ServiceBean bean) {
        this.bean = bean;
        this.context = context;
        ac = (AppContext) context.getApplication();
    }

    @Override
    public int getCount() {
        return bean.getData().size();
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
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_project1, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(bean.getData().get(position).getName());
        viewHolder.tv.setCompoundDrawablePadding(20);

        Drawable drawable1= context.getResources().getDrawable(R.drawable.icon_beautycar);
        Drawable drawable2= context.getResources().getDrawable(R.drawable.icon_maintain);
        Drawable drawable3= context.getResources().getDrawable(R.drawable.icon_repair);
        Drawable drawable4= context.getResources().getDrawable(R.drawable.icon_refit);

        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        if(position==0){
            viewHolder.tv.setCompoundDrawables(drawable1,null,null,null);
        }
        else if(position==1){
            viewHolder.tv.setCompoundDrawables(drawable2,null,null,null);
        }
        else if(position==2){
            viewHolder.tv.setCompoundDrawables(drawable3,null,null,null);
        }
        else if(position==3){
            viewHolder.tv.setCompoundDrawables(drawable4,null,null,null);
        }
        else {
            viewHolder.tv.setCompoundDrawables(drawable1,null,null,null);
        }
        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getCarApiClient(ac).getCategoryByCode(bean.getData().get(position).getCode(),
                        ac.shopId,new BaseApiCallback(){
                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                if(res.isOK()){
                                    ServiceBean bean = (ServiceBean) res;
                                    LogUtils.e(bean);
                                    new DialogClassA(context,bean.getData(),position).show();
                                }
                            }
                        });
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
