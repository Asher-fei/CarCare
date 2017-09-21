package com.lida.carcare.tpl;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivitySelectOutboundGoods;
import com.lida.carcare.bean.SelectOutboundGoodslistBean;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.NumberWidget;
import com.midian.base.app.Constant;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/29.
 */

public class ActivitySelectOutboundGoodsTpl extends BaseTpl<SelectOutboundGoodslistBean.DataBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvWarehourse)
    TextView tvWarehourse;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.etCount)
    NumberWidget etCount;
    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivitySelectOutboundGoodsTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivitySelectOutboundGoodsTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activityselecyoutboundgoods;
    }

    @Override
    public void setBean(final SelectOutboundGoodslistBean.DataBean bean, final int position) {
        if (bean != null) {
            LogUtils.e(bean.toString());
            tvName.setText(bean.getProductName());
            ac.setImage(iv, Constant.BASE + bean.getProductImg());
            tvWarehourse.setText("库位：" + bean.getRepertoryName());
            tvCount.setText("库存：" + bean.getStock());
            tvCode.setText("编码：" + bean.getProductInternationalCode());
            final int count = Integer.valueOf(bean.getStock());
            cb.setClickable(false);
            if (bean.isSelect()) {
                cb.setChecked(true);
            } else {
                cb.setChecked(false);
            }
            etCount.setOnAddBtnClicked(new NumberWidget.OnAddBtnClickedListener() {
                @Override
                public boolean onAddBtnClicked(int i) {
                    if(count==i){
                        UIHelper.t(_activity,"已选择全部库存数量");
                        return true;
                    }
                    return false;
                }
            });
            etCount.setOnNumberChangeListener(new BaseTextWatcher(){
                @Override
                public void afterTextChanged(Editable s) {
                    super.afterTextChanged(s);
                        bean.setSelectCount(s.toString());
                }
            });
            llItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp = ActivitySelectOutboundGoods.tvRight.getText().toString();
                    String s = temp.replaceAll("\\D", "");
                    int i = Integer.valueOf(s);
                    if (bean.isSelect()) {
                        i--;
                        bean.setSelect(false);
                        cb.setChecked(false);
                    } else {
                        i++;
                        bean.setSelect(true);
                        cb.setChecked(true);
                    }
                    ActivitySelectOutboundGoods.tvRight.setText("完成("+i+")");
                    Log.e("wqf",i+"");
                }
            });
        }
    }
}
