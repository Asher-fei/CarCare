package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lida.carcare.R;
import com.lida.carcare.data.ActivityStorageData;
import com.lida.carcare.tpl.ActivityStorageTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仓库管理-仓库设置
 * Created by Administrator on 2017/6/20.
 */

public class ActivityStorage extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("仓库管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jumpForResult(_activity, ActivityAddStorage.class, 1001);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK==resultCode){
            listViewHelper.refresh();
        }
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityStorageData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityStorageTpl.class;
    }
}
