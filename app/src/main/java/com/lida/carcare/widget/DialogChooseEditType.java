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
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class DialogChooseEditType extends Dialog {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private Context context;
    private String title;
    private onButtonClick onButtonClick;

    public DialogChooseEditType(Context context, String title) {
        super(context, R.style.diy_dialog);
        this.title = title;
        init(context);
    }

    public DialogChooseEditType(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_chooseedittype, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (R.id.tvReadyDo == checkedId) {
                    onButtonClick.onEdit();
                } else {
                    onButtonClick.onDelete();
                }
                dismiss();
            }
        });
        tvTitle.setText(title);
    }

    public interface onButtonClick {
        void onDelete();

        void onEdit();
    }

    public void setOnButtonClick(onButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
}
