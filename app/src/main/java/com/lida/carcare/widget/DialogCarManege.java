package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xkr on 2017/7/19.
 */

public class DialogCarManege extends Dialog {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private Context context;
    private String title;
    private onCarManegeOperation onCarmanegeOperation;

    public DialogCarManege(Context context, String title) {
        super(context, R.style.diy_dialog);
        this.title = title;
        init(context);
    }

    public DialogCarManege(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_car_manege, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tvTitle.setText(title);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(R.id.tvAlreadyDo == checkedId){
                    if(onCarmanegeOperation!=null){
                        onCarmanegeOperation.onDelClick();
                    }
                }
                dismiss();
            }
        });
    }

    public void setOnSelectOperation(onCarManegeOperation onCarmanegeOperation){
        this.onCarmanegeOperation = onCarmanegeOperation;
    }

    public interface onCarManegeOperation{
        void onDelClick();
    }
}
