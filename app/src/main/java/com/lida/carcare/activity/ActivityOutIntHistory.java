package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.lida.carcare.R;
import com.lida.carcare.data.ActivityOutIntHistoryData;
import com.lida.carcare.tpl.ActivityOutIntHistoryTpl;
import com.lida.carcare.widget.BaseTextWatcher;
import com.lida.carcare.widget.DialogOutIntHistoryType;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 出入库记录界面
 */
public class ActivityOutIntHistory extends BaseListActivity{

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    private TextView show_tv;
    private ActivityOutIntHistoryData dataSource;
    @BindView(R.id.etSearch)
    EditText etSearch;
    private String stutas = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("出入库记录");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        show_tv = (TextView) findViewById(R.id.show_tv);
        show_tv.setOnClickListener(this);
        etSearch.addTextChangedListener(new BaseTextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                dataSource.setParams(stutas,s.toString());
                listViewHelper.refresh();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_out_int_history;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        dataSource = new ActivityOutIntHistoryData(_activity);
        return dataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityOutIntHistoryTpl.class;
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.show_tv:
                showMenu();
                break;
        }
    }

    private void showMenu() {
        DialogOutIntHistoryType dialog = new DialogOutIntHistoryType(_activity, show_tv);
        dialog.setOnItemClickListener(new DialogOutIntHistoryType.onItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (position==4){
                    dataSource.setParams("", "");
                    stutas = "";
                }else {
                    dataSource.setParams(position+"", "");
                    stutas = position+"";
                }
                listViewHelper.refresh();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001&&resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }
}
