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

public class ItemCanClick3 extends LinearLayout {

    private Context context;
    private ImageView iv;
    private TextView tvTitle;
    private TextView tvCount;
    private int resourceId;
    private String title;
    private boolean isOpen=false;
    private OnItemClick onItemClickListener;
    private ItemCanClick3 itemCanClick;

    public ItemCanClick3(Context context) {
        super(context);
    }

    public ItemCanClick3(Context context, @Nullable AttributeSet attrs) {
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
        this.setPadding(Func.Dp2Px(context,10),Func.Dp2Px(context,15),Func.Dp2Px(context,10),Func.Dp2Px(context,15));
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setBackgroundColor(Color.WHITE);
        LayoutParams ivPa = new LayoutParams(Func.Dp2Px(context,16), Func.Dp2Px(context,16));
        iv=new ImageView(context);
        iv.setLayoutParams(ivPa);
        iv.setImageResource(resourceId);
//        iv.setBackgroundResource(R.drawable.bg_8radiu_orange);
        iv.setImageResource(R.drawable.icon_rr);

        LayoutParams tvTitlePa = new LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f);
        tvTitlePa.setMargins(Func.Dp2Px(context,10),0,0,0);
        tvTitle=new TextView(context);
        tvTitle.setLayoutParams(tvTitlePa);
        tvTitle.setText(title);
        tvTitle.setTextColor(Color.parseColor("#444444"));
        tvTitle.setTextSize(15);

        LayoutParams tvCountPa = new LayoutParams(Func.Dp2Px(context,34), Func.Dp2Px(context,19));
        tvCount=new TextView(context);
        tvCount.setLayoutParams(tvCountPa);
        tvCount.setTextColor(Color.parseColor("#626363"));
        tvCount.setTextSize(15);
        tvCount.setGravity(Gravity.CENTER);
//        tvCount.setBackgroundResource(R.drawable.bg_lightkgray_solid);

        this.addView(iv);
        this.addView(tvTitle);
        this.addView(tvCount);
        this.setOnClickListener(listener);
    }

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClickListener = onItemClick;
    }

    OnClickListener listener=new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isOpen){
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_270);
                iv.startAnimation(animation);
                isOpen=false;
                System.out.println("close");
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClose(itemCanClick);
                }
            }else{
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_90);
                iv.startAnimation(animation);
                isOpen=true;
                System.out.println("true");
                if(onItemClickListener!=null){
                    onItemClickListener.onItemOpen(itemCanClick);
                }
            }
        }
    };

    public void setTitle(String s){
        tvTitle.setText(s);
    }
    public void setCount(String s){
        tvCount.setText(s);
    }


    public interface OnItemClick{
        void onItemOpen(ItemCanClick3 itemCanClick);
        void onItemClose(ItemCanClick3 itemCanClick);
    }
}
