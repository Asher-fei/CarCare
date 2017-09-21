package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityDirectInStorage;
import com.lida.carcare.activity.ActivityGoodsManager;
import com.lida.carcare.activity.ActivityInventoryVerification;
import com.lida.carcare.activity.ActivityOtherOutBound;
import com.lida.carcare.activity.ActivityOutIntHistory;
import com.lida.carcare.activity.ActivityPurchase;
import com.lida.carcare.activity.ActivityPurchaseHistory;
import com.lida.carcare.activity.ActivityPurchaseReturn;
import com.lida.carcare.activity.ActivityReceiveGoods;
import com.lida.carcare.activity.ActivityStorage;
import com.lida.carcare.activity.ActivitySupplierManage;
import com.lida.carcare.activity.ActivityWaittingWareHouse;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存管理
 * Created by WeiQingFeng on 2017/4/7.
 */

public class AdapterRepositories extends BaseAdapter
{

    private Context context;
    private List<Bean> mData = null;
    private AppContext ac;

    public AdapterRepositories(Context context)
    {
        this.context = context;
        ac = (AppContext) context.getApplicationContext();
        initData();
    }

    private void initData()
    {
        mData = new ArrayList<>(12);
        for (int count = 0; count < 12; count++)
        {
            Bean bean = new Bean();
            switch (count)
            {
                case 0:
                    bean.activityCls = ActivityPurchase.class;
                    bean.rid = R.drawable.icon_storage1;
                    bean.name = "采购";
                    break;
                case 1:
                    bean.activityCls = ActivityWaittingWareHouse.class;
                    bean.rid = R.drawable.icon_storage2;
                    bean.name = "待入库";
                    break;
                case 2:
                    bean.activityCls = ActivityDirectInStorage.class;
                    bean.rid = R.drawable.icon_storage3;
                    bean.name = "其它入库";
                    break;
                case 3:
                    bean.activityCls = ActivityOtherOutBound.class;
                    bean.rid = R.drawable.icon_other_outbound;
                    bean.name = "其它出库";
                    break;
                case 4:
                    bean.activityCls = ActivityReceiveGoods.class;
                    bean.rid = R.drawable.icon_storage4;
                    bean.name = "领料出库";
                    break;
                case 5:
                    bean.activityCls = ActivityPurchaseHistory.class;
                    bean.rid = R.drawable.icon_storage5;
                    bean.name = "采购记录";
                    break;
                case 6:
                    bean.activityCls = ActivityOutIntHistory.class;
                    bean.rid = R.drawable.icon_storage6;
                    bean.name = "出入库记录";
                    break;
                case 7:
                    bean.activityCls = ActivityPurchaseReturn.class;
                    bean.rid = R.drawable.icon_storage7;
                    bean.name = "采购退货";
                    break;
                case 8:
                    bean.activityCls = ActivitySupplierManage.class;
                    bean.rid = R.drawable.icon_storage8;
                    bean.name = "供应商设置";
                    break;
                case 9:
                    bean.activityCls = ActivityStorage.class;
                    bean.rid = R.drawable.icon_storage9;
                    bean.name = "仓库设置";
                    break;
                case 10:
//                    AppContext ac = (AppContext) ((Activity) context).getApplication();
//                    bean.isNoPower = "1".equals(ac.user_type);
                    bean.activityCls = ActivityGoodsManager.class;
                    bean.rid = R.drawable.icon_storage10;
                    bean.name = "商品管理";
                   /* if("0".equals(ac.user_type)||"4".equals(ac.user_type)){
                        bean.isNoPower = false;
                    }else{
                        bean.isNoPower = true;
                    }*/
                    break;
                case 11:
                    bean.activityCls = ActivityInventoryVerification.class;
                    bean.rid = R.drawable.icon_storage11;
                    bean.name = "库存盘点";
                    break;
            }
            mData.add(bean);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activityrepositories, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Bean bean = mData.get(position);
        viewHolder.button.setText(bean.name);
        Drawable drawable = context.getResources().getDrawable(bean.rid);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        viewHolder.button.setCompoundDrawables(null,drawable,null,null);
        viewHolder.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (bean.isNoPower)
                {
                    UIHelper.t(context, "管理权限不够，不能进入");
                    return;
                }
                if(bean.activityCls!=null){
                  /*  if(bean.activityCls ==ActivityOtherOutBound.class){
                        UIHelper.t(ac,"正在建设中...");
                        return;
                    }*/
                    UIHelper.jump((Activity) context, bean.activityCls);
                }
//                Bundle bundle = new Bundle();
//                bundle.putString("title", viewHolder.button.getText().toString());
//                UIHelper.jump((Activity) context, ActivityWarehouseDetail.class, bundle);
            }
        });
        return convertView;
    }

    static class ViewHolder
    {
        @BindView(R.id.button)
        TextView button;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }

    private class Bean
    {
        Class activityCls;
        int rid;
        String name;
        boolean isNoPower = false;
    }
}
