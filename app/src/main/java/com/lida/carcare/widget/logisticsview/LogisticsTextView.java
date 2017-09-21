package com.lida.carcare.widget.logisticsview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xkr on 2017/8/16.
 */

public class LogisticsTextView extends TextView{
    /**
     * TextView的文本高度
     */
    private float txtHeight;

    public LogisticsTextView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public LogisticsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public LogisticsTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @SuppressLint("FloatMath")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        txtHeight = getMeasuredHeight();

    }

    /**
     * 获取TextView的文本高度
     * @return
     */
    public float getTxtHeight() {
        return txtHeight;
    }

    /**
     * 获取父控件的顶部内边距
     * @return
     */
    public int getParentPaddingTop(){
        return ((View)this.getParent()).getPaddingTop();
    }

    /**
     * 获取父控件的底部内边距
     * @return
     */
    public int getParentPaddingBotton(){
        return ((View)this.getParent()).getPaddingBottom();
    }
}
