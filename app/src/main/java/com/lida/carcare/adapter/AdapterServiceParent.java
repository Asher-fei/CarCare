package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityServiceClassSetting;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.AddGoodBean;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogAddClass;
import com.lida.carcare.widget.DialogChooseEditType;
import com.lida.carcare.widget.DialogIfDelete;
import com.midian.base.app.AppContext;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 服务分类大类
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterServiceParent extends BaseAdapter {

    private Activity context;
    private List<ServiceBean.DataBean> data;
    private List<Boolean> status = new ArrayList<>();
    private onItemClick onItemClick;
    private String type;
    private AppContext ac;

    public AdapterServiceParent(Activity context, List<ServiceBean.DataBean> data, String type) {
        this.context = context;
        this.data = data;
        this.type = type;
        this.ac = (AppContext)(context.getApplication());
    }

    @Override
    public int getCount() {
        LogUtils.e(data);
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                status.add(true);
            } else {
                status.add(false);
            }
        }
        return data.size();
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_serviceparent, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (status.get(position)) {
            viewHolder.cb.setChecked(true);
        } else {
            viewHolder.cb.setChecked(false);
        }
        viewHolder.cb.setText(data.get(position).getName());
        viewHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.clear();
                for (int i = 0; i < data.size(); i++) {
                    if (i == position) {
                        status.add(true);
                    } else {
                        status.add(false);
                    }
                }
                notifyDataSetChanged();
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
                }
            }
        });
        /*viewHolder.cb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if ("edit".equals(type)) {
                        final DialogChooseEditType dialog = new DialogChooseEditType(context, data.get(position).getName());
                        dialog.setOnButtonClick(new DialogChooseEditType.onButtonClick() {
                            @Override
                            public void onDelete() {
                                dialog.dismiss();
                                final DialogIfDelete dialogIfDelete = new DialogIfDelete(context, data.get(position).getName());
                                final BaseActivity activity = (BaseActivity)context;
                                final ActivityServiceClassSetting setAc = (ActivityServiceClassSetting)context;
                                dialogIfDelete.setOnOkButtonClick(new DialogIfDelete.onOkButtonClick() {
                                    @Override
                                    public void delete() {//删除服务
                                        LogUtils.e(data);
                                        dialogIfDelete.dismiss();
                                        AppUtil.getCarApiClient(ac).deleteSerive(data.get(position).getId(), new BaseApiCallback(){

                                            @Override
                                            public void onApiStart(String tag) {
                                                super.onApiStart(tag);
                                                activity.showLoadingDlg();
                                            }

                                            @Override
                                            public void onApiSuccess(NetResult res, String tag) {
                                                super.onApiSuccess(res, tag);
                                                activity.hideLoadingDlg();
                                                if(res.isOK()){
                                                    data.remove(position);
                                                    notifyDataSetChanged();
                                                    setAc.defalutItem();
                                                }
                                            }

                                            @Override
                                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                                super.onApiFailure(t, errorNo, strMsg, tag);
                                                activity.hideLoadingDlg();
                                            }
                                        });
                                    }
                                });
                                dialogIfDelete.show();
                            }

                            @Override
                            public void onEdit() {
                                dialog.dismiss();
                                final DialogAddClass dialogAddClass = new DialogAddClass(context, "编辑大类");
                                dialogAddClass.getEt().setText(data.get(position).getName());
                                dialogAddClass.setOnOkButtonClick(new DialogAddClass.onOkButtonClick() {
                                    @Override
                                    public void add() {//修改服务名字
                                        final String content = dialogAddClass.getContent();
                                        if (!"".equals(content)) {
                                            AppUtil.getCarApiClient(ac).updateService(data.get(position).getId(),
                                                    content,new BaseApiCallback(){
                                                        @Override
                                                        public void onApiSuccess(NetResult res, String tag) {
                                                            super.onApiSuccess(res, tag);
                                                            if(res.isOK()){
                                                                data.get(position).setName(content);
                                                                dialogAddClass.dismiss();
                                                                notifyDataSetChanged();
                                                            }
                                                        }
                                                    });
                                        }

                                    }
                                });
                                dialogAddClass.show();
                            }
                        });
                        dialog.show();
                }
                return false;
            }
        });*/
        return convertView;
    }

    public interface onItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    static class ViewHolder {
        @BindView(R.id.cb)
        CheckBox cb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
