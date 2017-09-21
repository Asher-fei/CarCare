package com.lida.carcare.fragment;

import com.lida.carcare.data.FragmentReadyToCheckData;
import com.lida.carcare.tpl.FragmentReadyToCheckTpl;
import com.lida.carcare.tpl.FragmentRefuseTpl;
import com.midian.base.base.BaseListFragment;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 * 员工管理-已拒绝
 * Created by WeiQingFeng on 2017/5/3.
 */

public class FragmentRefused extends BaseListFragment {

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FragmentReadyToCheckData(_activity,"2");
    }

    @Override
    protected Class getTemplateClass() {
        return FragmentRefuseTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
