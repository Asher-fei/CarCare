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

import com.lida.carcare.R;
import com.midian.base.util.UIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加规格
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogAddGoodParams extends Dialog {

    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etContent)
    EditText etContent;

    private Context context;
    private List<String> names;
    private List<String> params;
    private BaseAdapter adapter;

    public DialogAddGoodParams(Context context, List<String> str, List<String> params, BaseAdapter adapter) {
        super(context, R.style.diy_dialog);
        this.names = str;
        this.params = params;
        this.adapter = adapter;
        init(context);
    }

    public DialogAddGoodParams(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_addgoodparams, null);
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
                String name = etName.getText().toString().trim();
                String param = etContent.getText().toString().trim();
                if("".equals(name)||"".equals(param)){
                    UIHelper.t(context,"请输入内容");
                    return;
                }else{
                    names.add(name);
                    params.add(param);
                }
                dismiss();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
