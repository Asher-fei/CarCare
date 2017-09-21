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
 * Created by xkr on 2017/8/2.
 */

public class DialogIfDeleteResult extends Dialog {
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tvContent)
    TextView tvContent;

    private Context context;
    private onOkButtonClick onOkButtonClick;
    private String title="";
    private String content="";

    public DialogIfDeleteResult(Context context) {
        super(context, R.style.diy_dialog);
        init(context);
    }

    public DialogIfDeleteResult(Context context,String title,String content) {
        super(context, R.style.diy_dialog);
        this.title=title;
        this.content = content;
        init(context);
    }


    public DialogIfDeleteResult(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_if_delete_result, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tv.setText("确认删除"+title+"?");
        tvContent.setText(content);
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
                if(onOkButtonClick!=null){
                    onOkButtonClick.delete();
                }
                break;
        }
    }

    public interface onOkButtonClick{
        void delete();
    }
}
