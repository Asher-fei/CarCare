package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/28.
 */

public class DialogCancellationsRemark extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.et)
    EditText et;

    private Context context;
    private OnCancellationsRemarkClick onCancellationsRemarkClick;
    private String title;

    public DialogCancellationsRemark(@NonNull Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogCancellationsRemark(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    protected DialogCancellationsRemark(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context,  R.style.diy_dialog);
        init(context);
    }

    public interface OnCancellationsRemarkClick {
        void onCancellationsRemarkClick();

    }

    public View getOkButton() {
        return btnOk;
    }

    public EditText getEt() {
        return et;
    }

    public String getContent() {
        return et.getText().toString().trim();
    }

    public void setOnCancellationsRemarkClick(OnCancellationsRemarkClick onCancellationsRemarkClick) {
        this.onCancellationsRemarkClick = onCancellationsRemarkClick;
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
        View v = View.inflate(context, R.layout.dialog_cancellations_remark, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_ok:
                if (onCancellationsRemarkClick != null) {
                    onCancellationsRemarkClick.onCancellationsRemarkClick();
                }
                dismiss();
                break;
        }
    }

}
