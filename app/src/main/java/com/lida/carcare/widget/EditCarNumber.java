package com.lida.carcare.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.midian.base.util.Func;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class EditCarNumber extends LinearLayout
{

    private Context context;
    private List<OneFontEdit> fonts = new ArrayList<>();

    public EditCarNumber(Context context)
    {
        super(context);
        this.context = context;
        init(context);
//        m();
    }

    public EditCarNumber(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        init(context);
//        m();
    }

    public EditCarNumber(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context);
//        m();
    }

    private void init(Context context)
    {
        this.setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < 7; i++)
        {
            OneFontEdit oneFontEdit = new OneFontEdit(context);
            fonts.add(oneFontEdit);
//            oneFontEdit.addTextChangedListener(new TextWatcherCopy(i));
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f);
            layoutParams.setMargins(Func.Dp2Px(context, 0), 0, Func.Dp2Px(context, 5), 0);
            oneFontEdit.setLayoutParams(layoutParams);
            oneFontEdit.setPadding(0, 0, 0, 3);
            oneFontEdit.setMinWidth(Func.Dp2Px(context, 30));
            this.addView(oneFontEdit);
        }
    }

    class TextWatcherCopy implements TextWatcher
    {

        private int id;

        public TextWatcherCopy(int id)
        {
            this.id = id;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s)
        {

            if (!"".equals(s.toString()))
            {
                if (id != 6)
                {
                    fonts.get(id).clearFocus();
                    id++;
                    fonts.get(id).requestFocus();
                    id--;
                }
            } else
            {
                if (id != 0)
                {
                    fonts.get(id).clearFocus();
                    id--;
                    fonts.get(id).requestFocus();
                    if (fonts.get(id).getText().toString().trim().length() == 1)
                    {
                        fonts.get(id).setSelection(1);
                    }
                    id++;
                }
            }
        }
    }

    public void setDefaultText(String str)
    {
        if (!"".equals(str))
        {
            char chars[] = str.toCharArray();
            for (int i = 0; i < chars.length; i++)
            {
                if (i < 7)
                {
                    String s = String.valueOf(chars[i]);
                    fonts.get(i).setText(s);
                }
            }
        }
    }

    public String getNumbers()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fonts.size(); i++)
        {
            if ("".equals(fonts.get(i).getText().toString().trim()))
            {
//                UIHelper.t(context,"车牌号格式不正确");
                return null;
            }
            stringBuilder.append(fonts.get(i).getText());
        }
//        UIHelper.t(context,stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void m()
    {
        for (int i = 0; i < fonts.size(); i++)
        {
            final int finalI = i;
            fonts.get(i).setOnKeyListener(new View.OnKeyListener()
            {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event)
                {
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN)
                    {
                        System.out.println("KEYCODE_DEL");
                        if (finalI != 0)
                        {
                            fonts.get(finalI).clearFocus();
                            fonts.get(finalI - 1).requestFocus();
                        }
                    }
                    return false;
                }
            });
        }
    }

    public List<OneFontEdit> getEts()
    {
        return fonts;
    }
}
