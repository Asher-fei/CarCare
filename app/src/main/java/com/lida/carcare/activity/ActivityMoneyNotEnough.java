package com.lida.carcare.activity;

import android.os.Bundle;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityMoneyNotEnoughData;
import com.lida.carcare.tpl.ActivityMoneyNotEnoughTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

/**
 *余额不足
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityMoneyNotEnough extends BaseListActivity {

    private BaseLibTopbarView topbar;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from=mBundle.getString("from");
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        if("会员管理".equals(from)){
            topbar.setTitle("会员管理");
        }else if("余额不足".equals(from)){
            topbar.setTitle("余额不足");
        }else if("余次不足".equals(from)){
            topbar.setTitle("余次不足");
        }else if("即将过期".equals(from)){
            topbar.setTitle("即将过期");
        }else if("已过期".equals(from)){
            topbar.setTitle("已过期");
        }
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_moneynotenough;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityMoneyNotEnoughData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityMoneyNotEnoughTpl.class;
    }
}
