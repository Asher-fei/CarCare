package com.midian.base.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UMengStatistticUtil;

/**
 * Fragment基类
 */
public class BaseFragment extends Fragment implements ApiCallback, View.OnClickListener
{
    protected AppContext ac;
    protected BaseFragmentActivity _activity;
    protected Fragment _fragment;
    protected FragmentManager fm;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        _activity = (BaseFragmentActivity) activity;
        ac = (AppContext) _activity.getApplication();
        fm = getChildFragmentManager();
        _fragment = this;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        UMengStatistticUtil.onResumeForFragment(this.getActivity());

    }

    @Override
    public void onPause()
    {
        super.onPause();
        UMengStatistticUtil.onPauseForFragment(this.getActivity());
    }

    @Override
    public void onClick(View v)
    {

    }

    @Override
    public void onApiStart(String tag)
    {

    }

    @Override
    public void onApiLoading(long count, long current, String tag)
    {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag)
    {

    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag)
    {

    }

    @Override
    public void onParseError(String tag)
    {

    }
}
