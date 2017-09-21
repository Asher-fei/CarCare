package com.lida.carcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.InventoryVerificationDetailBean;
import com.midian.base.bean.NetResult;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 盘点详情
 * Created by Administrator on 2017/7/6.
 */

public class AdapterInventoryVerificationDetail extends BaseAdapter {

    List<InventoryVerificationDetailBean.DataBean.CarRepertoryBean> carRepertoryBeen;
    List<InventoryVerificationDetailBean.DataBean.CarWarehouseBean> carWarehouseBeen;
    List<InventoryVerificationDetailBean.DataBean.ShoutProductRecordBean> shoutProductRecordBeen;
    Context context;

    public  AdapterInventoryVerificationDetail( List<InventoryVerificationDetailBean.DataBean.CarRepertoryBean> carRepertoryBeen, List<InventoryVerificationDetailBean.DataBean.CarWarehouseBean> carWarehouseBeen,
                                                List<InventoryVerificationDetailBean.DataBean.ShoutProductRecordBean> shoutProductRecordBeen,Context context){
        this.carRepertoryBeen = carRepertoryBeen;
        this.carWarehouseBeen = carWarehouseBeen;
        this.shoutProductRecordBeen = shoutProductRecordBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return carRepertoryBeen.size();
    }

    @Override
    public NetResult getItem(int position) {
        return carRepertoryBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_inventory_verification_details, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.productName.setText(carWarehouseBeen.get(position).getProductName());
        viewHolder.productInternationalCode.setText(carWarehouseBeen.get(position).getProductInternationalCode());
       // viewHolder.stock.setText(carWarehouseBeen.get(position).getStock()==null?"0":carWarehouseBeen.get(position).getStock());

        int SumAcountNo=0;
        int SumAcountOk=0;
        if(carRepertoryBeen.get(position).getSumAcountNo()==null||carRepertoryBeen.get(position).getSumAcountNo().equals("")){
            SumAcountNo=0;
        }else {
            SumAcountNo = Integer.parseInt(carRepertoryBeen.get(position).getSumAcountNo());
        }
        if(carRepertoryBeen.get(position).getSumAcountOk()==null||carRepertoryBeen.get(position).getSumAcountOk().equals("")){
            SumAcountOk=0;
        }else {
            SumAcountOk = Integer.parseInt(carRepertoryBeen.get(position).getSumAcountOk());
        }
        if(SumAcountOk>SumAcountNo){
            viewHolder.stock.setText(SumAcountOk+"");
        }else if(SumAcountOk==0&&SumAcountNo==0){
            //原储==盘点数
            viewHolder.stock.setText(shoutProductRecordBeen.get(position).getStock()==null?"0":shoutProductRecordBeen.get(position).getStock());
        }
        else {
            viewHolder.stock.setText(SumAcountNo+"");
        }


        viewHolder.repertoryName.setText(carRepertoryBeen.get(position).getRepertoryName());

        viewHolder.shoutStock.setText(shoutProductRecordBeen.get(position).getStock()==null?"0":shoutProductRecordBeen.get(position).getStock());
        viewHolder.shoutOk.setText(shoutProductRecordBeen.get(position).getRecordShoutOk()==null?"0":shoutProductRecordBeen.get(position).getRecordShoutOk());
        viewHolder.shoutNO.setText(shoutProductRecordBeen.get(position).getRecordShoutNo()==null?"0":shoutProductRecordBeen.get(position).getRecordShoutNo().replace("-",""));
        if(shoutProductRecordBeen.get(position).getRecordShoutOk()==null){
            viewHolder.layout_shoutOK.setVisibility(View.GONE);
        }else {
            viewHolder.layout_shoutOK.setVisibility(View.VISIBLE);
        }

        if(shoutProductRecordBeen.get(position).getRecordShoutNo()==null){
            viewHolder.layout_shoutNo.setVisibility(View.GONE);
        }else {
            viewHolder.layout_shoutNo.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.productInternationalCode)
        TextView productInternationalCode;
        @BindView(R.id.repertoryName)
        TextView repertoryName;
        @BindView(R.id.stock)
        TextView stock;
        @BindView(R.id.shoutOk)
        TextView shoutOk;
        @BindView(R.id.shoutStock)
        TextView shoutStock;
        @BindView(R.id.shoutNO)
        TextView shoutNO;
        @BindView(R.id.layout_shoutOK)
        LinearLayout layout_shoutOK;
        @BindView(R.id.layout_shoutNo)
        LinearLayout layout_shoutNo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
