package com.lida.carcare.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class DialogQuickSetting extends Dialog {

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;

    private Context context;
    private TextView textView;

    public DialogQuickSetting(Context context, TextView textView) {
        super(context, R.style.diy_dialog);
        init(context,textView);
    }

    public DialogQuickSetting(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        init(context,textView);
    }

    private void init(Context context,TextView textView) {
        this.context = context;
        this.textView = textView;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_quicksetting, null);
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Calendar calendar=Calendar.getInstance();
                LogUtils.e(calendar.getTime());
                Date date = null;
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb1:
                        long curren = System.currentTimeMillis();
                        curren += 30 * 60 * 1000;
                        date = new Date(curren);
                        break;
                    case R.id.rb2:
                        calendar.set(calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+1);
                        date = calendar.getTime();
                        break;
                    case R.id.rb3:
                        calendar.set(calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+2);
                        date = calendar.getTime();
                        break;
                    case R.id.rb4:
                        calendar.set(calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+3);
                        date = calendar.getTime();
                        break;
                    case R.id.rb5:
                        calendar.set(calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+4);
                        date = calendar.getTime();
                        break;
                }
                String time = simpleDateFormat.format(date);
                LogUtils.e(time);
                textView.setText(time);
                dismiss();
                break;
        }
    }
}
