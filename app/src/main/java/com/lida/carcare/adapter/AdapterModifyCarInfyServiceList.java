package com.lida.carcare.adapter;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityModifyCarInfo;
import com.lida.carcare.bean.BannerBean;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.EditCarNumber;
import com.lida.carcare.widget.NumberWidget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 修改车辆信息-服务项目
 * Created by WeiQingFeng on 2017/4/7.
 */

public class AdapterModifyCarInfyServiceList extends BaseAdapter {

    private Context context;
    private List<ActivityModifyCarInfo.Item> data;
    private onPriceChangeListener listener;

    private String status;//控制删除按钮是否显示  0显示   1隐藏

    public AdapterModifyCarInfyServiceList(Context context, List<ActivityModifyCarInfo.Item> data, String status) {
        this.context = context;
        this.data = data;
        this.status = status;
    }

    @Override
    public int getCount() {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_modifycarinfo_service, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if ("0".equals(status)) {
            viewHolder.tvReduce.setVisibility(View.VISIBLE);
        } else if ("1".equals(status)) {
            viewHolder.tvReduce.setVisibility(View.GONE);
        }
        viewHolder.tvReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                if (listener != null) {
                    listener.updatePrice();
                }
                notifyDataSetChanged();
            }
        });
        viewHolder.tvItemName.setText(data.get(position).getName());
        viewHolder.etPrices.setText(data.get(position).getPrice());
        viewHolder.edtCount.setNumber(data.get(position).getCount());
        viewHolder.edtCount.setOnAddBtnClicked(new NumberWidget.OnAddBtnClickedListener() {
            @Override
            public boolean onAddBtnClicked(int i) {
                int count = data.get(position).getCount();
                count++;
                data.get(position).setCount(count);
                notifyDataSetChanged();
                if(listener!=null){
                       listener.updatePrice();
                   }
                return true;
            }
        });
        viewHolder.edtCount.setOnReduceBtnClickedListener(new NumberWidget.OnReduceBtnClickedListener() {
            @Override
            public boolean onReduceBtnClicked(int i) {
                int count = data.get(position).getCount();
                if(count>1) {
                    count--;
                    data.get(position).setCount(count);
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.updatePrice();
                    }
                }
                return true;
            }
        });
//       viewHolder.edtCount.setOnNumberChangeListener(new BaseTextWatcher(){
//           @Override
//           public void afterTextChanged(Editable s) {
//               super.afterTextChanged(s);
//               int p=(Integer) viewHolder.edtCount.getTag();
//               if(position==p){
//                   int count = Integer.parseInt(s.toString());
//                   LogUtils.e(count);
//                   data.get(position).setCount(count);
//                   LogUtils.e(data);
//                   if(listener!=null){
//                       listener.updatePrice();
//                   }
//               }
//
//           }
//       });


        viewHolder.etPrices.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(data.size()>position) {
                        if (viewHolder.etPrices.getText().toString().trim() != null && viewHolder.etPrices.getText().toString().trim().length() != 0) {
                            data.get(position).setPrice(viewHolder.etPrices.getText().toString());
                        }
                        if (listener != null) {
                            listener.updatePrice();
                        }
                    }
                }
            }
        });
        viewHolder.etPrices.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewHolder.etPrices.clearFocus();
                    InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if(imm.isActive()) {
                        imm.hideSoftInputFromWindow(viewHolder.etPrices.getWindowToken(), 0);
                    }
                }
                return false;
            }
        });



        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvItemName)
        TextView tvItemName;
        @BindView(R.id.etPrices)
        EditText etPrices;
        @BindView(R.id.tvReduce)
        ImageView tvReduce;
        @BindView(R.id.edtCount)
        NumberWidget edtCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setOnPriceChangeListener(onPriceChangeListener listener) {
        this.listener = listener;
    }

    public interface onPriceChangeListener {
        void updatePrice();
    }
}
