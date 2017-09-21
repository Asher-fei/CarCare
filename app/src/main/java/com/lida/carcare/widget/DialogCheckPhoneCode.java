package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 验证新手机号验证码
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogCheckPhoneCode extends Dialog {

    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnCode)
    Button btnCode;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvNotice)
    TextView tvNotice;

    private Context context;
    private String notice;

    public DialogCheckPhoneCode(Context context, String notice) {
        super(context, R.style.diy_dialog);
        this.notice = notice;
        init(context);
    }

    public DialogCheckPhoneCode(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_checkcode, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tvNotice.setText(notice);
        btnNext.setText("确定");
    }

    public EditText getEt() {
        return etCode;
    }

    public String getCode() {
        return etCode.getText().toString().trim();
    }

    @OnTextChanged(R.id.etCode)
    void onTextChanged(CharSequence text){
        if(text.length()==0){
            btnNext.setEnabled(false);
        }else{
            btnNext.setEnabled(true);
        }
    }

    @OnClick({R.id.btnCode, R.id.btnNext, R.id.ivClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnCode:
                break;
            case R.id.btnNext:
                dismiss();
                UIHelper.t(context,"手机号码修改成功！");
                break;
            case R.id.ivClose:
                dismiss();
                break;
        }
    }
}
