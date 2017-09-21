package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/27.
 */

public class DialogSelectPayType extends Dialog {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;

    private Context context;
    private TextView textView;

    private String[] items;

    private String defaultPay = "";

    public DialogSelectPayType(Context context, TextView textView,String[] items) {
        super(context, R.style.diy_dialog);
        init(context,textView,items);
    }

    public DialogSelectPayType(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context,textView,items);
    }

    private void init(Context context,TextView textView,String[] items) {
        this.context = context;
        this.textView = textView;
        this.items = items;


        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_select_pay_type, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);


        //动态添加RadioButton组件
        for(int i=0; i<items.length; i++)
        {
            RadioButton tempButton = (RadioButton) getLayoutInflater().inflate(R.layout.item_radiobutton, null);
            tempButton.setText(items[i]);
            radioGroup.addView(tempButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            //默认选中第一个选项
            if(i==0){
                radioGroup.check(tempButton.getId());
                defaultPay = tempButton.getText().toString();
            }


            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    RadioButton tempButton = (RadioButton)findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                    defaultPay = tempButton.getText().toString();
                }
            });
        }
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
                textView.setText(defaultPay);
                dismiss();
                break;
        }
    }
}
