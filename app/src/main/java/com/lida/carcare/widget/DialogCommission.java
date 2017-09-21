package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 员工提成
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogCommission extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.et)
    EditText et;

    private Context context;
    private onOkButtonClick onOkButtonClick;
    private String title;

    public DialogCommission(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogCommission(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_commission, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
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

    public void setOnOkButtonClick(onOkButtonClick onOkButtonClick) {
        this.onOkButtonClick = onOkButtonClick;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_ok:
                if (onOkButtonClick != null) {
                    onOkButtonClick.onOkButtonClicked();
                }
                dismiss();
                break;
        }
    }

    public interface onOkButtonClick {
        void onOkButtonClicked();
    }
}
