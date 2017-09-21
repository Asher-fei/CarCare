package com.lida.carcare.widget;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;

/**
 * Created by Administrator on 2017/3/11 0011.
 */

public class NumberWidgetBig extends LinearLayout implements View.OnClickListener {

    private TextView tvPlus;
    private TextView tvReduce;
    private EditText etNumber;
    private LinearLayout llReduce;
    private OnAddBtnClickedListener onAddBtnClickedListener;
    private OnReduceBtnClickedListener onReduceBtnClickedListener;
    private OnFocusChangedListener onFocusChangeListener;

    public NumberWidgetBig(Context context) {
        super(context);
        init();
    }

    public NumberWidgetBig(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NumberWidgetBig(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = View.inflate(getContext(), R.layout.numberwidget_big,this);
        etNumber = (EditText) view.findViewById(R.id.et_number);
        tvPlus = (TextView) view.findViewById(R.id.tv_plus);
        tvReduce = (TextView) view.findViewById(R.id.tv_reduce);
        tvPlus.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
        etNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(onFocusChangeListener!=null){
                        onFocusChangeListener.onLostFocus();
                    }
                }
            }
        });
    }

    public void setOnNumberChangeListener(TextWatcher watcher){
        etNumber.addTextChangedListener(watcher);
    }

    public String getNumber(){
        return etNumber.getText().toString().trim();
    }

    public void setNumber(int i){
        etNumber.setText(i+"");
    }

    @Override
    public void onClick(View v) {
        String number = etNumber.getText().toString().trim();
        int i = Integer.valueOf(number);
        if(R.id.tv_plus == v.getId()){
            if(onAddBtnClickedListener!=null){
                if(onAddBtnClickedListener.onAddBtnClicked(i)){
                    return;
                }
            }
            i++;
        }else{
            if(i==1){
                return;
            }else{
                if(onReduceBtnClickedListener!=null){
                    if(onReduceBtnClickedListener.onReduceBtnClicked(i)){
                        return;
                    }
                }
                i--;
            }
        }
        etNumber.setText(i+"");
    }
    public interface OnAddBtnClickedListener{
        boolean onAddBtnClicked(int i);
    }
    public void setOnAddBtnClicked(OnAddBtnClickedListener listener){
        this.onAddBtnClickedListener = listener;
    }

    public interface OnReduceBtnClickedListener{
        boolean onReduceBtnClicked(int i);
    }
    public void setOnReduceBtnClickedListener(OnReduceBtnClickedListener listener){
        this.onReduceBtnClickedListener = listener;
    }

    public interface OnFocusChangedListener{
        void onLostFocus();
    }
    public void setOnFocusChangedListener(OnFocusChangedListener listener){
        this.onFocusChangeListener = listener;
    }

}
