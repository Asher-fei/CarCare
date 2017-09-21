package com.lida.carcare.fragment;

import com.lida.carcare.R;
import com.lida.carcare.data.FragmentCarBeautyData;
import com.lida.carcare.data.FragmentCarMainTainData;
import com.lida.carcare.tpl.FragmentCarBeautyTpl;
import com.lida.carcare.tpl.FragmentCarMainTainTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 保养
 * Created by WeiQingFeng on 2017/4/12.
 */

public class FragmentCarMainTain extends BaseListFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_pulltorefresh;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentCarMainTainData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentCarMainTainTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
