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
 * Created by xkr on 2017/7/31.
 */

public class DialogMyOrderDelete extends Dialog {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private Context context;
    private onOperation onOperation;

    public DialogMyOrderDelete(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogMyOrderDelete(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_myorder_delete, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(R.id.tvAlreadyDo == checkedId){
                    if(onOperation!=null){
                        onOperation.onDelClick();
                    }
                }
                dismiss();
            }
        });
    }

    public void setOnSelectOperation(onOperation onOperation){
        this.onOperation = onOperation;
    }

    public interface onOperation{
        void onDelClick();
    }
}
