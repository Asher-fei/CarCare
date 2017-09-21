package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterChooseServerELView;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServerClassABean;
import com.lida.carcare.bean.ServerClassBBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 技师
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityChooseServer extends BaseActivity
{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.btnOK)
    Button btnOK;

    private List<ServerClassABean.DataBean> parent = new ArrayList<>();
    private Map<String, List<ServerClassBBean.DataBean>> data = new HashMap<>();
    private AdapterChooseServerELView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseserver);
        ButterKnife.bind(this);
        topbar.setTitle("技师");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        exListView.setGroupIndicator(null);
        exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        adapter = new AdapterChooseServerELView(_activity, exListView, parent, data);
        exListView.setAdapter(adapter);
        AppUtil.getCarApiClient(ac).getServerClassA(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag)
    {
        super.onApiSuccess(res, tag);
        if (res.isOK())
        {
            if ("getServerClassA".equals(tag))
            {
                ServerClassABean bean = (ServerClassABean) res;
                if (bean.getData() != null)
                {
                    for (int i = 0; i < bean.getData().size(); i++)
                    {
                        parent.add(bean.getData().get(i));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @OnClick(R.id.btnOK)
    public void onViewClicked()
    {
        UIHelper.t(_activity, "派工成功");
        finish();
    }
}
