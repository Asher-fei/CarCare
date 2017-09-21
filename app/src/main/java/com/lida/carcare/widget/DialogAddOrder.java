package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCustomerOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogAddOrder extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.et)
    EditText et;

    private Context context;
    private List<String> str;
    private BaseAdapter adapter;

    public DialogAddOrder(Context context, List<String> str, BaseAdapter adapter) {
        super(context, R.style.diy_dialog);
        this.str = str;
        this.adapter = adapter;
        init(context);
    }

    public DialogAddOrder(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_addorder, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
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
                String trim = et.getText().toString().trim();
                LogUtils.e(str);
                LogUtils.e(trim);
                if (!"".equals(trim)){
                    str.add(trim);
                }
                dismiss();
                adapter.notifyDataSetChanged();
                    break;
        }
    }
}
