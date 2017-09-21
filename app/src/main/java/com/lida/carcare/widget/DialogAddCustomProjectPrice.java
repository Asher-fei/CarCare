package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加新车--自定义项目--价格包含（产品、工时费）
 * Created by xkr on 2017/9/15.
 */

public class DialogAddCustomProjectPrice extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.cb1)
    CheckBox cb1;
    @BindView(R.id.cb2)
    CheckBox cb2;

    private Context context;
    private onOkButtonClick onOkButtonClick;
    private String title;

    public DialogAddCustomProjectPrice(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogAddCustomProjectPrice(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_add_customproject_price, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);

    }

    public View getOkButton() {
        return btnOk;
    }


    public String getContentType() {

        // 0 包含产品  1包含产品和工时费
        String s = "0";
        if(cb2.isChecked()){
           s = "1";
        }else {
            s = "0";
        }
        return s;
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
                    onOkButtonClick.add();
                }
                break;
        }
    }

    public interface onOkButtonClick {
        void add();
    }
}
