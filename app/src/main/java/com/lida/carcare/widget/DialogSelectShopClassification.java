package com.lida.carcare.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterThreeExpandListView;
import com.lida.carcare.adapter.AdapterTwoShopClassification;
import com.lida.carcare.bean.GoodClassABean;
import com.lida.carcare.bean.GoodListBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/6.
 */

public class DialogSelectShopClassification extends Dialog {

    private List<GoodClassABean.DataBean> data;
    private Map<String, List<GoodClassABean.DataBean>> map = new HashMap<>();
    private Context context;
    @BindView(R.id.exListView)
    ExpandableListView exListView;

    private AdapterTwoShopClassification adapterTwoShopClassification;

    public DialogSelectShopClassification(@NonNull Context context,List<GoodClassABean.DataBean> data,Map<String, List<GoodClassABean.DataBean>> map) {
        super(context,R.style.right_dialog);
        this.context = context;
        this.data = data;
        this.map = map;
        init(context);
    }

    public DialogSelectShopClassification(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.right_dialog);
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
        View v = View.inflate(context, R.layout.dialog_select_shop_classification, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        exListView.setGroupIndicator(null);
        exListView.setDivider(context.getResources().getDrawable(R.drawable.divider_line));
        exListView.setChildDivider(context.getResources().getDrawable(R.drawable.divider_line));
        adapterTwoShopClassification = new AdapterTwoShopClassification((Activity) context, exListView, data,map);
        exListView.setAdapter(adapterTwoShopClassification);
    }
}
