package com.lida.carcare.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterExpandListView;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceBean;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
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
 * 服务管理
 * Created by WeiQingFeng on 2017/4/17.
 */

public class ActivityServiceManager extends BaseActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.view)
    View view;

    private Map<String, List<ServiceBean.DataBean>> data = new HashMap<>();
    private List<ServiceBean.DataBean> parent = new ArrayList<>();
    private PopupWindow popupWindow;

    private AdapterExpandListView adapterExpandListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityservicemanager);
        ButterKnife.bind(this);
        topbar.setTitle("服务管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, onClickListener);
        exListView.setGroupIndicator(null);
        exListView.setDivider(getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(getResources().getDrawable(R.drawable.divider_line));
        adapterExpandListView = new AdapterExpandListView(_activity, exListView, parent, data);
        exListView.setAdapter(adapterExpandListView);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity,ActivityServiceSearchResult.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.getCarApiClient(ac).getCategory(ac.shopId,this);//获取一级分类
    }

    @Override
    protected void onPause() {
        super.onPause();
        data.clear();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            if("getCategory".equals(tag)){
                ServiceBean bean = (ServiceBean) res;
                if(bean.getData()!=null){
                    parent.clear();
                    for (int i = 0; i < bean.getData().size(); i++) {
                        parent.add(bean.getData().get(i));
                    }
                    adapterExpandListView.notifyDataSetChanged();
                }
            }
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.right_ib:
                    View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_service, null);
                    view.findViewById(R.id.tvAddService).setOnClickListener(onClickListener);
                    view.findViewById(R.id.tvServiceSetting).setOnClickListener(onClickListener);
                    view.findViewById(R.id.tvHotServiceSetting).setOnClickListener(onClickListener);
                    showMenu(topbar.getRight_ib(), view);
                    break;
                case R.id.tvAddService:
                    UIHelper.jump(_activity,ActivityAddService.class);
                    popupWindow.dismiss();
                    break;
                case R.id.tvServiceSetting:
                    Bundle bundle=new Bundle();
                    bundle.putString("type","edit");
                    UIHelper.jump(_activity,ActivityServiceClassSetting.class,bundle);
                    popupWindow.dismiss();
                    break;
                case R.id.tvHotServiceSetting:
                    UIHelper.jump(_activity,ActivityHotServiceSetting.class);
                    break;
            }
        }
    };

    private void showMenu(View parent, View contentView) {
        //popupWindow = new PopupWindow(contentView, Func.Dp2Px(_activity, 115), Func.Dp2Px(_activity, 108));
        popupWindow = new PopupWindow(contentView, Func.Dp2Px(_activity, 115), Func.Dp2Px(_activity, 162));
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 80), Func.Dp2Px(_activity, 5));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.tvSearch)
    public void onViewClicked() {

    }
}
