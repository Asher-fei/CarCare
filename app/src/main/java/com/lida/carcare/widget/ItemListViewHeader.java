package com.lida.carcare.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.midian.base.util.Func;

/**
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ItemListViewHeader extends AppCompatTextView {

    private Context context;
    private String title;

    public ItemListViewHeader(Context context,String title) {
        super(context);
        this.context = context;
        this.title=title;
        setTitle();
    }

    public ItemListViewHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemListViewHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitle(){
        this.setText(title);
        this.setTextColor(Color.parseColor("#1C1C1C"));
        this.setTextSize(14);
        this.setBackgroundColor(Color.WHITE);
        this.setPadding(Func.Dp2Px(context,10),Func.Dp2Px(context,15),Func.Dp2Px(context,10),Func.Dp2Px(context,15));
    }
}
