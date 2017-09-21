package com.lida.carcare.widget.rightdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityAddCar;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.widget.IndexList.CityBean;
import com.lida.carcare.widget.IndexList.DividerItemDecoration;
import com.lida.carcare.widget.IndexList.HeaderRecyclerAndFooterWrapperAdapter;
import com.lida.carcare.widget.IndexList.ViewHolder;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务二级分类
 * Created by Administrator on 2016/10/27 0027.
 */

public class DialogClassA extends Dialog {

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

    private AdapterDialogA mAdapter;
    private Activity context;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<CityBean> mDatas;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    private List<ServiceBean.DataBean> data;
    private int p;


    public DialogClassA(Activity context, List<ServiceBean.DataBean> data,int p) {
        super(context, R.style.right_dialog);
        this.data = data;
        this.p = p;
        init(context);
    }

    public DialogClassA(Activity context, int themeResId) {
        super(context, R.style.right_dialog);
        init(context);
    }

    private void init(Activity context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.RIGHT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_right, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        LogUtils.e(data);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(context));
        mAdapter = new AdapterDialogA(context, mDatas, p, this);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
//        mHeaderAdapter.setHeaderView(R.layout.item_city, "测试头部");
        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(context, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
//        mIndexBar.setNeedRealIndex(true)//设置需要真实的索引
//                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        mIndexBar.setmPressedShowTextView(mTvSideBarHint).setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        initDatas(data);
    }

    private void initDatas(List<ServiceBean.DataBean> data) {
        mDatas = new ArrayList<>();
        if(data.size()>0){
            for (int i = 0; i < data.size(); i++) {
                CityBean cityBean = new CityBean();
                cityBean.setCity(data.get(i).getName());
                cityBean.setId(data.get(i).getId());
                cityBean.setCode(data.get(i).getCode());
                mDatas.add(cityBean);
            }
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
                ActivityAddCar.goodsProject.delete(0, ActivityAddCar.goodsProject.length());
                ActivityAddCar.maintainProject.delete(0, ActivityAddCar.maintainProject.length());
                ActivityAddCar.repairProject.delete(0, ActivityAddCar.repairProject.length());
                ActivityAddCar.refitProject.delete(0, ActivityAddCar.refitProject.length());
                ActivityAddCar.serviceEditData.clear();
                ActivityAddCar.tvPrice.setText("0.0");
                ActivityAddCar.tvCount.setText("共0项");
                UIHelper.t(context,"已重置，请重新选择项目");
                ActivityAddCar.ServiceEditAdapterRefresh();
                break;
            case R.id.btnOk:
                LogUtils.e(ActivityAddCar.goodsProject);
                LogUtils.e(ActivityAddCar.maintainProject);
                LogUtils.e(ActivityAddCar.repairProject);
                LogUtils.e(ActivityAddCar.refitProject);
                ActivityAddCar.sumPrice();
                dismiss();
                break;
        }
    }
}
