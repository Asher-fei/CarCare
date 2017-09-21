package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 未派工结算-提示
 * Created by xkr on 2017/9/12.
 */

public class DialogIfSettleAccounts extends Dialog {
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv)
    TextView tv;

    private Context context;
    private onOkButtonClick onOkButtonClick;
    private String title="";

    public DialogIfSettleAccounts(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }


    public DialogIfSettleAccounts(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public void setOnOkButtonClick(onOkButtonClick onOkButtonClick){
        this.onOkButtonClick = onOkButtonClick;
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
        View v = View.inflate(context, R.layout.dialog_ifsettleaccouints, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tv.setText("温馨提示");
    }

    public View getOkButton() {
        return btnOk;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                if(onOkButtonClick!=null){
                    onOkButtonClick.btnCancel();
                }
                break;
            case R.id.btn_ok:
                if(onOkButtonClick!=null){
                    onOkButtonClick.btnOk();
                }
                break;
        }
    }

    public interface onOkButtonClick{
        void btnOk();
        void btnCancel();
    }
}
