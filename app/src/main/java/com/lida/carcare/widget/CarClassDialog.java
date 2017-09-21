package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.widget.IndexList.CityAdapter;
import com.lida.carcare.widget.IndexList.CityBean;
import com.lida.carcare.widget.IndexList.DividerItemDecoration;
import com.lida.carcare.widget.IndexList.HeaderRecyclerAndFooterWrapperAdapter;
import com.lida.carcare.widget.IndexList.ViewHolder;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class CarClassDialog extends Dialog {

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.indexBar)
    IndexBar mIndexBar;
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.listView)
    ListView listView;

    private CityAdapter mAdapter;
    private Context context;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<CityBean> mDatas;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private TextView textView;

    private List<CarTypeBean.DataBean> data;

    private CAdapter adapter;
    private List<CarTypeBean.DataBean.CarTreesBean> cData = new ArrayList<>();
    private TextView tvHeader;

    public CarClassDialog(Context context, TextView textView, List<CarTypeBean.DataBean> data) {
        super(context, R.style.right_dialog);
        this.textView = textView;
        this.data = data;
        init(context);
    }

    public CarClassDialog(Context context, int themeResId) {
        super(context, R.style.right_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.RIGHT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_carclass, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_header,null);
        tvHeader = (TextView) view.findViewById(R.id.tvCity);
        tvHeader.setTag("");
        listView.addHeaderView(view);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(context));
        adapter = new CAdapter(cData);
        listView.setAdapter(adapter);
        mAdapter = new CityAdapter(context, mDatas, adapter, listView, tvHeader);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(context, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));
        mRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        mIndexBar.setmPressedShowTextView(mTvSideBarHint).setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        initDatas(data);
    }

    private void initDatas(final List<CarTypeBean.DataBean> data) {
        mDatas = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            CityBean cityBean = new CityBean();
            cityBean.setCity(data.get(i).getBrandName());
            cityBean.setId(data.get(i).getId());
            cityBean.setCarTrees(data.get(i).getCarTrees());
            mDatas.add(cityBean);
        }
        mIndexBar.setmSourceDatas(mDatas)//设置数据
                .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                .invalidate();
        mAdapter.setDatas(mDatas);
        mHeaderAdapter.notifyDataSetChanged();
        mDecoration.setmDatas(mDatas);
        mDecoration.setColorTitleFont(Color.parseColor("#F55D12"));
    }

    @OnClick({R.id.btnReset, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnReset:
                textView.setText("");
                listView.setVisibility(View.GONE);
                break;
            case R.id.btnOk:
                textView.setText(tvHeader.getText().toString());
                textView.setTag(tvHeader.getTag());
                dismiss();
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        listView.setVisibility(View.GONE);
    }

    public class CAdapter extends BaseAdapter {

        private List<CarTypeBean.DataBean.CarTreesBean> data;

        public void setData(List<CarTypeBean.DataBean.CarTreesBean> data){
            this.data = data;
        }

        public CAdapter(List<CarTypeBean.DataBean.CarTreesBean> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? 0 : position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item_city, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvCity.setText(data.get(position).getBrandName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(data.get(position).getBrandName());
                    textView.setTag(data.get(position).getId());
                    dismiss();
                }
            });
            return convertView;
        }

        class ViewHolder {

            @BindView(R.id.tvCity)
            TextView tvCity;
            @BindView(R.id.content)
            LinearLayout content;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
