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
 * 修改密码
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogChangePass extends Dialog {

    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvNotice)
    TextView tvNotice;

    private Context context;

    public DialogChangePass(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogChangePass(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_changepass, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
    }

    public EditText getEt() {
        return etCode;
    }

    public String getCode() {
        return etCode.getText().toString().trim();
    }

    @OnTextChanged(R.id.etCode)
    void onTextChanged(CharSequence text){
        if(text.length()<6){
            btnNext.setEnabled(false);
        }else{
            btnNext.setEnabled(true);
        }
    }

    @OnClick({R.id.btnNext, R.id.ivClose})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.btnNext:
                UIHelper.t(context,"密码修改成功！");
                break;
            case R.id.ivClose:
                break;
        }
    }
}
