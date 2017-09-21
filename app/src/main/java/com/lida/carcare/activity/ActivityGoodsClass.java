package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterGoodsClass;
import com.lida.carcare.adapter.AdapterGoodsManager;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodClassABean;
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

/**
 * 商品分类
 * Created by Administrator on 2017/6/29.
 */

public class ActivityGoodsClass extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;

    private AdapterGoodsClass adapterGoodsManager;
    private Map<String, List<GoodClassABean.DataBean>> data = new HashMap<>();
    private List<GoodClassABean.DataBean> parent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsclass);
        ButterKnife.bind(this);
        topbar.setTitle("商品管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        exListView.setGroupIndicator(null);
        exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        adapterGoodsManager = new AdapterGoodsClass(_activity, exListView, parent, data);
        exListView.setAdapter(adapterGoodsManager);
        AppUtil.getCarApiClient(ac).getProductCategory(ac.shopId, this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("getProductCategory".equals(tag)) {
                parent.clear();
                GoodClassABean bean = (GoodClassABean) res;
                if (bean.getData() != null) {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        parent.add(bean.getData().get(i));
                    }
                    adapterGoodsManager.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
