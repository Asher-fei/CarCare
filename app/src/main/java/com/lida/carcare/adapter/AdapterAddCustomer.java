package com.lida.carcare.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.CustomerBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/1.
 */

public class AdapterAddCustomer extends BaseAdapter
{

    private Context context;
    private List<CustomerBean> data;
    private boolean isEnable = true;

    public AdapterAddCustomer(Context context, List<CustomerBean> data)
    {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_addcustomer, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.etName.setTag(position);
        viewHolder.etPhone.setTag(position);
        viewHolder.etRelation.setTag(position);
        viewHolder.etName.addTextChangedListener(new MyTextChangeListener(0, viewHolder));
        viewHolder.etPhone.addTextChangedListener(new MyTextChangeListener(1, viewHolder));
        viewHolder.etRelation.addTextChangedListener(new MyTextChangeListener(2, viewHolder));

        viewHolder.rgSex.setTag(String.valueOf(position));
        viewHolder.rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.rbMan){
                    if(String.valueOf(position).equals(viewHolder.rgSex.getTag().toString())){
                        data.get(position).setSex("0");
                    }
                }else if(checkedId==R.id.rbWomen){
                    if(String.valueOf(position).equals(viewHolder.rgSex.getTag().toString())){
                        data.get(position).setSex("1");
                    }
                }
            }
        });

        if(data.get(position).getName()!=null&&!data.get(position).getName().equals("")){
            viewHolder.etName.setText(data.get(position).getName());
            viewHolder.etPhone.setText(data.get(position).getPhone()==null?"":data.get(position).getPhone());
            viewHolder.etRelation.setText(data.get(position).getRelation()==null?"":data.get(position).getRelation());
            if(data.get(position).getSex().equals("0")){
                viewHolder.rbMan.setChecked(true);
            }else {
                viewHolder.rbWomen.setChecked(true);
            }
        }else {
            viewHolder.etName.setText("");
            viewHolder.etPhone.setText("");
            viewHolder.etRelation.setText("");
            viewHolder.rbMan.setChecked(true);
        }

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class MyTextChangeListener implements TextWatcher
    {

        private ViewHolder holder;
        private int flag;

        public MyTextChangeListener(int flag, ViewHolder holder)
        {
            this.holder = holder;
            this.flag = flag;
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            int position = (Integer) holder.etName.getTag();
            CustomerBean bean = data.get(position);
            if (flag == 0)
            {
                bean.setName(s.toString());
            } else if (flag == 1)
            {
                bean.setPhone(s.toString());
            } else if (flag == 2)
            {
                bean.setRelation(s.toString());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
        }
    }

    static class ViewHolder
    {
        @BindView(R.id.ivDelete)
        TextView ivDelete;
        @BindView(R.id.etName)
        EditText etName;
        @BindView(R.id.rbMan)
        RadioButton rbMan;
        @BindView(R.id.rbWomen)
        RadioButton rbWomen;
        @BindView(R.id.rgSex)
        RadioGroup rgSex;
        @BindView(R.id.textView3)
        TextView textView3;
        @BindView(R.id.etPhone)
        EditText etPhone;
        @BindView(R.id.etRelation)
        EditText etRelation;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
