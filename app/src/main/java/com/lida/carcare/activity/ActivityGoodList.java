package com.lida.carcare.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityGoodListData;
import com.lida.carcare.tpl.ActivityGoodTpl;
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
 * 商品列表
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityGoodList extends BaseListActivity
{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvSearch)
    TextView tvSearach;
    @BindView(R.id.view)
    View view;

    public static TextView tvCount;

    private PopupWindow popupWindow;
    private String code;//上级页面的商品分类code
    public static String title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvCount = (TextView) findViewById(R.id.tvCount);
        title = mBundle.getString("title");
        ActivityGoodTpl.isSelect = mBundle.getBoolean("isSelect");
        if (ActivityGoodTpl.isSelect)
        {
            ActivityGoodTpl.activity = this;
        }
        topbar.setTitle(title);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.right_ib:
                    View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_good, null);
                    view.findViewById(R.id.tvAddService).setOnClickListener(listener);
                    showMenu(topbar.getRight_ib(), view);
                    break;
                case R.id.tvAddService:
                    UIHelper.jump(_activity, ActivityAddGoods.class);
                    popupWindow.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onResume()
    {
        super.onResume();
        listViewHelper.refresh();
    }

    private void showMenu(View parent, View contentView)
    {
        popupWindow = new PopupWindow(contentView, Func.Dp2Px(_activity, 115), Func.Dp2Px(_activity, 50));
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 80), Func.Dp2Px(_activity, 5));
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

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_goodlist;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource()
    {
        code = mBundle.getString("code");
        return new ActivityGoodListData(_activity, code);
    }

    @Override
    protected Class getTemplateClass()
    {
        return ActivityGoodTpl.class;
    }

    @OnClick(R.id.tvSearch)
    public void onViewClicked()
    {
        UIHelper.jump(_activity, ActivityGoodSearchResult.class);
    }
}
