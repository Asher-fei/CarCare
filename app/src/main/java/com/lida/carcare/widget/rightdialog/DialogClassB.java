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
import com.lida.carcare.bean.ServiceEditBean;
import com.lida.carcare.bean.ServiceGoodBean;
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

public class DialogClassB extends Dialog {

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

    private AdapterDialogB mAdapter;
    private Activity context;
    private LinearLayoutManager mManager;
    private SuspensionDecoration mDecoration;
    private List<CityBean> mDatas;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    private List<ServiceGoodBean.DataBean.JfomServiceBean> data;

    private int p;

    public DialogClassB(Activity context, List<ServiceGoodBean.DataBean.JfomServiceBean> data, int p) {
        super(context, R.style.right_dialog);
        this.data = data;
        this.p = p;
        init(context);
    }

    public DialogClassB(Activity context, int themeResId) {
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
        mRv.setLayoutManager(mManager = new LinearLayoutManager(context));
        mAdapter = new AdapterDialogB(context, mDatas, this);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tvCity, (String) o);
            }
        };
        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(context, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        mRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        mIndexBar.setmPressedShowTextView(mTvSideBarHint).setmLayoutManager(mManager);
        initDatas(data);
    }

    private void initDatas(List<ServiceGoodBean.DataBean.JfomServiceBean> data) {
        mDatas = new ArrayList<>();
        if(data.size()>0){
            for (int i = 0; i < data.size(); i++) {
                CityBean cityBean = new CityBean();
                cityBean.setCity(data.get(i).getName());
                cityBean.setId(data.get(i).getId());
                cityBean.setPrice(data.get(i).getServicePrice());
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
                for (int i = 0; i < mDatas.size(); i++) {
                    if(mDatas.get(i).isSelect()){
                        if(p==0){
                            LogUtils.e(mDatas.get(i).getCity());
                            if(!ActivityAddCar.goodsProject.toString().contains(mDatas.get(i).getCity())){
                                ActivityAddCar.goodsProject.append(mDatas.get(i).getCity()+",");
                                ActivityAddCar.serviceEditData.add(new ServiceEditBean(mDatas.get(i).getId(),"goodsProject",mDatas.get(i).getCity(),"1",mDatas.get(i).getPrice()));
                            }else{
                                UIHelper.t(context,"已添加该项目");
                            }
                        }
                        if(p==1){

                            if(!ActivityAddCar.maintainProject.toString().contains(mDatas.get(i).getCity())){
                                ActivityAddCar.maintainProject.append(mDatas.get(i).getCity()+",");
                                ActivityAddCar.serviceEditData.add(new ServiceEditBean(mDatas.get(i).getId(),"maintainProject",mDatas.get(i).getCity(),"1",mDatas.get(i).getPrice()));
                            }else{
                                UIHelper.t(context,"已添加该项目");
                            }
                        }
                        if(p==2){
                            if(!ActivityAddCar.repairProject.toString().contains(mDatas.get(i).getCity())){
                                ActivityAddCar.repairProject.append(mDatas.get(i).getCity()+",");
                                ActivityAddCar.serviceEditData.add(new ServiceEditBean(mDatas.get(i).getId(),"repairProject",mDatas.get(i).getCity(),"1",mDatas.get(i).getPrice()));
                            }else{
                                UIHelper.t(context,"已添加该项目");
                            }
                        }
                        if(p==3){
                            if(!ActivityAddCar.refitProject.toString().contains(mDatas.get(i).getCity())){
                                ActivityAddCar.refitProject.append(mDatas.get(i).getCity()+",");
                                ActivityAddCar.serviceEditData.add(new ServiceEditBean(mDatas.get(i).getId(),"refitProject",mDatas.get(i).getCity(),"1",mDatas.get(i).getPrice()));
                            }else{
                                UIHelper.t(context,"已添加该项目");
                            }
                        }
                    }
                }
                    ActivityAddCar.ServiceEditAdapterRefresh();
                dismiss();
                break;
        }
    }
}
