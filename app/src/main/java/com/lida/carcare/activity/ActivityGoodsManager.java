package com.lida.carcare.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterGoodsManager;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodClassABean;
import com.lida.carcare.bean.GoodListBean;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupCommen;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品管理
 * Created by WeiQingFeng on 2017/4/17.
 */

public class ActivityGoodsManager extends BaseActivity implements OnItemClickListener
{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.view)
    View view;

    public static final int REQUEST_CODE = 0x123;

    private Map<String, List<GoodClassABean.DataBean>> data = new HashMap<>();
    private List<GoodClassABean.DataBean> parent = new ArrayList<>();

    private PopupCommen popupWindow;
    private String[] itemsArr = {"新增商品", "商品分类设置"};
    private List<String> items = Arrays.asList(itemsArr);
    private AdapterGoodsManager adapterGoodsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityservicemanager);
        ButterKnife.bind(this);
        topbar.setTitle("商品管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, onClickListener);
        tvSearch.setHint("请输入商品名关键词");
        exListView.setGroupIndicator(null);
        exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        adapterGoodsManager = new AdapterGoodsManager(_activity, exListView, parent, data);
        exListView.setAdapter(adapterGoodsManager);
        if(getIntent().getBooleanExtra("ActivityScreenInventoryShop",false)==true){
            adapterGoodsManager.setIsActivityScreenInventoryShop(true);
        }

        if (mBundle != null) {
            adapterGoodsManager.setIsSelect(true);
            adapterGoodsManager.setOnItemClickListener(new AdapterGoodsManager.OnItemClickListener() {
                @Override
                public void onClick(GoodListBean.DataBean.JfomGoodsBean bean) {
                    Intent data = new Intent();
                    data.putExtra("bean", bean);
                    setResult(REQUEST_CODE, data);
                    finish();
                }
            });
        }
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity,ActivityGoodSearchResult.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.getCarApiClient(ac).getProductCategory(ac.shopId, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        data.clear();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
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

    View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId()) {
                case R.id.right_ib:
                    View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_member, null);
                    showMenu(v, view);
                    break;
            }
        }
    };

    private void showMenu(View parent, View contentView) {
        popupWindow = new PopupCommen(_activity, contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, items);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 110), Func.Dp2Px(_activity, 5));
        popupWindow.setOnItemClickListener(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
            @Override
            public void onDismiss()
            {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.tvSearch)
    public void onViewClicked() {

    }

    @Override
    public void doNext(int positon, String text) {
        switch (positon) {
            case 0:
                UIHelper.jump(_activity, ActivityAddGoods.class);
                break;
            case 1:
                Bundle bundle = new Bundle();
                bundle.putString("type", "edit");
                UIHelper.jump(_activity, ActivityGoodsClassSetting.class, bundle);
                break;
        }
        popupWindow.dismiss();
    }
}
