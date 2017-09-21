package com.lida.carcare.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.data.FragmentOfferData;
import com.lida.carcare.tpl.FragmentOfferTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * 报价
 * Created by WeiQingFeng on 2017/4/17.
 */
@SuppressLint("ValidFragment")
public class FragmentOffer extends BaseListFragment {

    private String id;
    public static TextView tvMoney;

    public FragmentOffer() {
    }

    public FragmentOffer(String id) {
        this.id = id;
        LogUtils.e("id:"+id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        tvMoney = (TextView) view.findViewById(R.id.tvMoney);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragmentoffer;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentOfferData(_activity,id);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentOfferTpl.class;
    }
}
