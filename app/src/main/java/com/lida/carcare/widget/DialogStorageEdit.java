package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仓库编辑/删除
 * Created by WeiQingFeng on 2016/10/28 0028.
 */

public class DialogStorageEdit extends Dialog {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private Context context;
    private String title;
    private onEditTypeChoosed onEditTypeChoosed;

    public DialogStorageEdit(Context context, String title) {
        super(context, R.style.diy_dialog);
        this.title = title;
        init(context);
    }

    public DialogStorageEdit(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_storageedit, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tvTitle.setText(title);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(R.id.tvReadyDo == checkedId){
                    if(onEditTypeChoosed!=null){
                        onEditTypeChoosed.onEditClick();
                    }
                }else{
                    if(onEditTypeChoosed!=null){
                        onEditTypeChoosed.onDelClick();
                    }
                }
                dismiss();
            }
        });
    }

    public void setOnEditTypeChoosed(onEditTypeChoosed onEditTypeChoosed){
        this.onEditTypeChoosed = onEditTypeChoosed;
    }

    public interface onEditTypeChoosed{
        void onEditClick();
        void onDelClick();
    }
}
