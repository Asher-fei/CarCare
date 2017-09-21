package com.lida.carcare.fragment;

import com.lida.carcare.R;
import com.lida.carcare.data.FragmentCarBeautyData;
import com.lida.carcare.tpl.FragmentCarBeautyTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 美容
 * Created by WeiQingFeng on 2017/4/12.
 */

public class FragmentCarBeauty extends BaseListFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_pulltorefresh;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentCarBeautyData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentCarBeautyTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }

}
