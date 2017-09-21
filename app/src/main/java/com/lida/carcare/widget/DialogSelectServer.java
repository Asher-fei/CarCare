package com.lida.carcare.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterServiceGood;
import com.lida.carcare.adapter.AdapterThreeExpandListView;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择服务
 * Created by Administrator on 2017/7/5.
 */

public class DialogSelectServer extends Dialog implements AdapterServiceGood.onChildClickListener{

    private List<ServiceBean.DataBean> data;
    private Context context;
    @BindView(R.id.exListView)
    ExpandableListView exListView;

    private AdapterThreeExpandListView adapterExpandListView;

    public DialogSelectServer(@NonNull Context context, List<ServiceBean.DataBean> data) {
        super(context,R.style.right_dialog);
        this.context = context;
        this.data = data;
        init(context);
    }

    public DialogSelectServer(@NonNull Context context, @StyleRes int themeResId) {
        super(context,R.style.right_dialog);
        init(context);
    }


    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.RIGHT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_select_server, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        exListView.setGroupIndicator(null);
        exListView.setDivider(context.getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(context.getResources().getDrawable(R.drawable.divider_line));
        adapterExpandListView = new AdapterThreeExpandListView((Activity) context, exListView, data);
        adapterExpandListView.setOnChildClickListener(this);
        exListView.setAdapter(adapterExpandListView);
    }


    @Override
    public void onClick(ServiceGoodBean.DataBean.JfomServiceBean bean) {
        UIHelper.t(context,bean.getServiceType());
    }
}
