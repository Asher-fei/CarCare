package com.lida.carcare.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityServiceListData;
import com.lida.carcare.tpl.ActivityServiceTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityServiceList extends BaseListActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearch;
    @BindView(R.id.view)
    View view;

    public static TextView tvCount;

    private PopupWindow popupWindow;
    private String code;//上级页面的服务项目code

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvCount = (TextView) findViewById(R.id.tvCount);
        view = findViewById(R.id.view);
        title = mBundle.getString("title");
        topbar.setTitle(title);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_ib:
                    View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_service2, null);
                    view.findViewById(R.id.tvAddService).setOnClickListener(listener);
                    showMenu(topbar.getRight_ib(), view);
                    break;
                case R.id.tvAddService:
                    UIHelper.jumpForResult(_activity, ActivityAddService.class, 1001);
                    popupWindow.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            listViewHelper.refresh();
        }
    }

    private void showMenu(View parent, View contentView) {
        popupWindow = new PopupWindow(contentView, Func.Dp2Px(_activity, 115), Func.Dp2Px(_activity, 50));
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_servicelist;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        code = mBundle.getString("code");
        return new ActivityServiceListData(_activity, code);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityServiceTpl.class;
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked() {
        UIHelper.jump(_activity, ActivityServiceSearchResult.class);
    }
}
