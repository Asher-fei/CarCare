package com.lida.carcare.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityRemindData;
import com.lida.carcare.tpl.ActivityRemindTpl;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupNotice;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 智能提醒
 * Created by WeiQingFeng on 2017/4/13.
 */

public class ActivityRemind extends BaseListActivity implements OnItemClickListener {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.view)
    View view;

    private PopupNotice popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("智能提醒");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
       // topbar.setRight2ImageButton(R.drawable.icon_search_small, listener);
        listView.setDivider(null);
        TextView textView = new TextView(_activity);
        textView.setPadding(Func.Dp2Px(_activity,10),Func.Dp2Px(_activity,17),Func.Dp2Px(_activity,10),Func.Dp2Px(_activity,17));
        textView.setTextSize(11);
        textView.setTextColor(Color.parseColor("#9F9F9F"));
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setText("提醒时间早于当前时间的提醒事项");
        listView.addHeaderView(textView);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_ib:
                    showMenu(v);
                    break;
            }
        }
    };

    private void showMenu(View parent) {
        View contentView = LayoutInflater.from(_activity).inflate(R.layout.spinner_notice, null);
        popupWindow = new PopupNotice(_activity, contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOnItemClickListener(this);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 90), Func.Dp2Px(_activity, 5));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void doNext(int positon, String text) {
        popupWindow.dismiss();
        if(positon==0){
            UIHelper.jumpForResult(_activity,ActivityCreateNotice.class,1001);
        }else if(positon==1){
            UIHelper.jump(_activity,ActivityRemindShutDown.class);
        }else if(positon==2){
            UIHelper.jumpForResult(_activity,ActivityNoticeSetting.class,1002);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remind;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityRemindData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityRemindTpl.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listViewHelper.refresh();
    }
}
