package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCustomerOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class DialogChooseType extends Dialog {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private Context context;
    private TextView textView;

    public DialogChooseType(Context context, TextView textView) {
        super(context, R.style.diy_dialog);
        this.textView = textView;
        init(context);
    }

    public DialogChooseType(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_choosetype, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(R.id.tvReadyDo == checkedId){
                    textView.setText("待完工");
                    textView.setTextColor(Color.parseColor("#007CC4"));
                }else{
                    textView.setText("已完工");
                    textView.setTextColor(Color.parseColor("#00B935"));
                }
                dismiss();
            }
        });
    }
}
