package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
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
 * Created by xkr on 2017/8/16.
 */

public class DialogDeleteCustomer extends Dialog {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private OnDialogOperation onDialogOperation;
    private Context context;
    private String title;

    public DialogDeleteCustomer(@NonNull Context context,String title) {
        super(context,R.style.diy_dialog);
        this.title = title;
        init(context);
    }

    public DialogDeleteCustomer(@NonNull Context context, @StyleRes int themeResId) {
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
                    if(onDialogOperation!=null){
                        onDialogOperation.onDelClick();
                    }
                }
                dismiss();
            }
        });
    }

    public void setOnSelectOperation(OnDialogOperation onDialogOperation){
        this.onDialogOperation = onDialogOperation;
    }

    public interface OnDialogOperation{
        void onDelClick();
    }
}
