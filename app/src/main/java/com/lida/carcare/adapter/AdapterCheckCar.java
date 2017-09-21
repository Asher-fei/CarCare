package com.lida.carcare.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.bean.CarInspectProjectBean;
import com.lida.carcare.widget.BaseTextWatcher;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/15.
 */

public class AdapterCheckCar extends BaseAdapter {

    private Context context;
    private List<CarInspectProjectBean.DataBean> mData = null;

    public AdapterCheckCar(Context context, List<CarInspectProjectBean.DataBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.adapter_check_car, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvProject.setText(mData.get(position).getName());
        holder.etRemarks.setText(mData.get(position).getRemarks());
        if("0".equals(mData.get(position).getState())){
            holder.rbBad.setChecked(true);
            holder.rbNormal.setChecked(false);
            holder.etRemarks.setEnabled(true);
        }else if("1".equals(mData.get(position).getState())){
            holder.rbBad.setChecked(false);
            holder.rbNormal.setChecked(true);
            holder.etRemarks.setEnabled(false);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.rbBad:
                        if (holder.rbNormal.isChecked()) {
                            holder.rbBad.setChecked(true);
                            holder.rbNormal.setChecked(false);
                            mData.get(position).setState("0");
                            holder.etRemarks.setEnabled(true);
                        } else {
                            holder.rbBad.setChecked(false);
                            holder.rbNormal.setChecked(true);
                            mData.get(position).setState("1");
                            holder.etRemarks.setEnabled(false);
                        }
                        break;
                    case R.id.rbNormal:
                        if (holder.rbBad.isChecked()) {
                            holder.rbBad.setChecked(false);
                            holder.rbNormal.setChecked(true);
                            mData.get(position).setState("1");
                            holder.etRemarks.setEnabled(false);
                        } else {
                            holder.rbBad.setChecked(true);
                            holder.rbNormal.setChecked(false);
                            mData.get(position).setState("0");
                            holder.etRemarks.setEnabled(true);
                        }
                        break;
                }
            }
        };
        holder.rbBad.setOnClickListener(listener);
        holder.rbNormal.setOnClickListener(listener);

        holder.etRemarks.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((ViewGroup) v.getParent())
                        .setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
                return false;
            }
        });


        holder.etRemarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    mData.get(position).setRemarks(holder.etRemarks.getText().toString());
                }
            }
        });
        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //((ViewGroup) v).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                return false;
            }
        });

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvProject)
        TextView tvProject;
        @BindView(R.id.etRemarks)
        EditText etRemarks;
        @BindView(R.id.rbBad)
        CheckBox rbBad;
        @BindView(R.id.rbNormal)
        CheckBox rbNormal;
        @BindView(R.id.layout)
        LinearLayout layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
