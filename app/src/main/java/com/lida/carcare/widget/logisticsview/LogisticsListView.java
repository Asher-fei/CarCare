package com.lida.carcare.widget.logisticsview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by xkr on 2017/8/16.
 */

public class LogisticsListView extends ListView {

    public LogisticsListView(Context context) {
        super(context);
    }

    public LogisticsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogisticsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
