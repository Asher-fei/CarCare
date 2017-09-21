package com.lida.carcare.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.midian.base.util.Func;

/**
 * Created by WeiQingFeng on 2017/4/10.
 */

public class ItemCanClick extends LinearLayout {

    private Context context;
    private ImageView iv1;
    private TextView tv;
    private ImageView iv2;
    private int resourceId;
    private String title;
    private boolean isOpen=false;
    private OnItemClick onItemClickListener;
    private ItemCanClick itemCanClick;

    public ItemCanClick(Context context) {
        super(context);
    }

    public ItemCanClick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attr){
        this.context=context;
        this.itemCanClick = this;
        TypedArray typedArray = context.obtainStyledAttributes(attr,R.styleable.ItemCanClick);
        resourceId = typedArray.getResourceId(R.styleable.ItemCanClick_ivTitle, R.drawable.ic_launcher);
        title=typedArray.getString(R.styleable.ItemCanClick_tvTitle);
        typedArray.recycle();
        System.out.println(title);
        this.setPadding(Func.Dp2Px(context,10),Func.Dp2Px(context,18),Func.Dp2Px(context,10),Func.Dp2Px(context,18));
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setBackgroundColor(Color.WHITE);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        LayoutParams layoutParams1 = new LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f);
        layoutParams1.setMargins(Func.Dp2Px(context,10),0,0,0);
        iv1=new ImageView(context);
        tv=new TextView(context);
        iv2=new ImageView(context);
        iv1.setLayoutParams(layoutParams);
        tv.setLayoutParams(layoutParams1);
        iv2.setLayoutParams(layoutParams);
        iv1.setImageResource(resourceId);
        iv2.setImageResource(R.drawable.icon_arrow_down);
        tv.setText(title);
        this.addView(iv1);
        this.addView(tv);
        this.addView(iv2);
        this.setOnClickListener(listener);
    }

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClickListener = onItemClick;
    }

    OnClickListener listener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isOpen){
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_180);
                iv2.startAnimation(animation);
                isOpen=false;
                System.out.println("close");
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClose(itemCanClick);
                }
            }else{
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_360);
                iv2.startAnimation(animation);
                isOpen=true;
                System.out.println("true");
                if(onItemClickListener!=null){
                    onItemClickListener.onItemOpen(itemCanClick);
                }
            }
        }
    };

    public interface OnItemClick{
        void onItemOpen(ItemCanClick itemCanClick);
        void onItemClose(ItemCanClick itemCanClick);
    }
}
