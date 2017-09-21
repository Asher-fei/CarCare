package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityCardSettingData;
import com.lida.carcare.data.CardRestrictCarnoData;
import com.lida.carcare.tpl.ActivityCardSettingTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 卡种设置
 * Created by Administrator on 2017/6/30.
 */

public class ActivityCardSetting extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("卡种设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_setting;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new CardRestrictCarnoData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityCardSettingTpl.class;
    }


    View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            UIHelper.jumpForResult(ActivityCardSetting.this,ActivityAddCards.class,1001);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001&&resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }
}
