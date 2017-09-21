package com.lida.carcare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivitySupplierListEdit;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.SupplierBean;
import com.lida.carcare.widget.DialogIfDelete;
import com.lida.carcare.widget.DialogStorageEdit;
import com.midian.base.app.AppContext;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zdf on 2017/6/28.
 */

public class InventoryVerificationAdapter extends BaseAdapter{
    List<SupplierBean.DataBean> mData = null;
    private BaseActivity context = null;
    private AdapterSupplier.OnItemClickListener listener = null;

    public InventoryVerificationAdapter(BaseActivity context)
    {
        this.context = context;
    }

    public void setData(List<SupplierBean.DataBean> data)
    {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        AdapterSupplier.ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_supplier, null);
            viewHolder = new AdapterSupplier.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (AdapterSupplier.ViewHolder) convertView.getTag();
        }
        SupplierBean.DataBean bean = mData.get(position);
        viewHolder.tvName.setText(bean.getSupplierName());
        viewHolder.tvShop.setText(bean.getSupplierCompany());
        viewHolder.tvPhone.setText(bean.getMobilephoneNo());
        viewHolder.layout.setTag(bean);
        viewHolder.layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SupplierBean.DataBean bean = (SupplierBean.DataBean) v.getTag();
                if (listener != null)
                {
                    listener.onClick(bean);
                } else
                {
                    AppUtil.getCarApiClient((AppContext) context.getApplication()).getSupplierListDetails(bean.getId(), context);
                }
            }
        });
        viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(final View v)
            {
                final SupplierBean.DataBean bean = (SupplierBean.DataBean) v.getTag();
                final DialogStorageEdit dialog = new DialogStorageEdit(v.getContext(), bean.getSupplierCompany());
                dialog.setOnEditTypeChoosed(new DialogStorageEdit.onEditTypeChoosed()
                {
                    @Override
                    public void onEditClick()
                    {
                        dialog.dismiss();
                        UIHelper.jump(v.getContext(), ActivitySupplierListEdit.class);
                    }

                    @Override
                    public void onDelClick()
                    {
                        dialog.dismiss();
                        final DialogIfDelete delete = new DialogIfDelete(v.getContext());
                        delete.setOnOkButtonClick(new DialogIfDelete.onOkButtonClick()
                        {
                            @Override
                            public void delete()
                            {
                                delete.dismiss();
                                AppUtil.getCarApiClient((AppContext) context.getApplication()).deletesupplier(bean.getId(), context);
                            }
                        });
                        delete.show();
                    }
                });
                dialog.show();
                return false;
            }
        });
        return convertView;
    }

    static class ViewHolder
    {
        @BindView(R.id.layout)
        View layout;
        @BindView(R.id.tvShop)
        TextView tvShop;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPhone)
        TextView tvPhone;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnItemClickListener(AdapterSupplier.OnItemClickListener listener)
    {
        this.listener = listener;
    }

    public interface OnItemClickListener
    {
        void onClick(NetResult result);
    }
}
