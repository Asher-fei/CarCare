package com.lida.carcare.widget;

import android.app.Activity;
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
import com.midian.base.app.AppContext;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 更换手机号
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogChangePhone extends Dialog {

    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.ivClose)
    ImageView ivClose;

    private Context context;
    private AppContext ac;

    public DialogChangePhone(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogChangePhone(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        ac = (AppContext) ((Activity) context).getApplication();
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_changephone, null);
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
        if(Func.isMobileNO(text.toString())){
            btnNext.setEnabled(true);
        }else{
            btnNext.setEnabled(false);
        }
    }

    @OnClick({R.id.btnNext, R.id.ivClose})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.btnNext:
                new DialogCheckPhoneCode(context,"修改手机号码需要验证您的新手机号"+ac.phone).show();
                break;
            case R.id.ivClose:
                break;
        }
    }
}
