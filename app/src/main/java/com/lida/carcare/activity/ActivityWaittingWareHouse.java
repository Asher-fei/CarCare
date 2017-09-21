package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.bean.WaitWareHouseBean;
import com.lida.carcare.data.ActivityWaitingWareHourseData;
import com.lida.carcare.tpl.ActivityWaitingWareHouseTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/26.
 * 待入库
 */
public class ActivityWaittingWareHouse extends BaseListActivity {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.count)
    TextView count;

    ArrayList<WaitWareHouseBean.DataBean> waitWareHouseBeanArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        topbar.setTitle("待入库");
        listView.setDividerHeight(30);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_waitting_ware_house;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ActivityWaitingWareHourseData(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityWaitingWareHouseTpl.class;
    }


    @OnClick(R.id.tvPuInStorage)
    public void  toPutInStorage(){
        List<WaitWareHouseBean.DataBean> data = (List<WaitWareHouseBean.DataBean>) dataSource.getResultList();
        LogUtils.e(data);

        //先清空之前保存的数据
        waitWareHouseBeanArrayList.clear();

        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).isCheck()){
                waitWareHouseBeanArrayList.add(data.get(i));
            }
        }
        if(waitWareHouseBeanArrayList.size()>0) {
            StringBuilder sIds = new StringBuilder();
            for (int i = 0; i < waitWareHouseBeanArrayList.size(); i++) {
                sIds.append(waitWareHouseBeanArrayList.get(i).getId()+",");
            }
            Bundle bundle = new Bundle();
            bundle.putString("ids",sIds.toString().substring(0,sIds.length()-1));
            UIHelper.jumpForResult(_activity,ActivityPutInStorage.class,bundle,1001);
        }else {
            UIHelper.t(this,"请先选择入库产品");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }
}





