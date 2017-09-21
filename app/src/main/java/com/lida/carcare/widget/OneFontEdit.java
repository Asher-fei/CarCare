package com.lida.carcare.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import com.lida.carcare.R;

/**
 * Created by Administrator on 2017/3/21.
 */

@SuppressLint("AppCompatCustomView")
public class OneFontEdit extends EditText {

    public OneFontEdit(Context context) {
        super(context);
        init();
    }

    public OneFontEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OneFontEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        this.setBackgroundResource(R.drawable.onefont);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
//        this.setMaxWidth(80);
//        this.setMinWidth(80);
        this.setGravity(Gravity.CENTER);
        this.setCursorVisible(false);
        this.setLines(1);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }
}
