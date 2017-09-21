package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCustomerOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogCall extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv)
    TextView tv;

    private Context context;
    private String str;

    public DialogCall(Context context, String str) {
        super(context, R.style.diy_dialog);
        this.str = str;
        init(context);
    }

    public DialogCall(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_call, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tv.setText("确定拨打电话？"+str);
    }

    public View getOkButton() {
        return btnOk;
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_ok:
                this.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri parse = Uri.parse("tel:"+str);
                intent.setData(parse);
                context.startActivity(intent);
                break;
        }
    }
}
