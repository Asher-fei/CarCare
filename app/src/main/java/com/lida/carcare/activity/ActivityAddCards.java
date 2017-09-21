package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterAddCards;
import com.lida.carcare.adapter.AdapterModifyCarInfyServiceList;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.bean.ServiceGoodBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加卡种
 * Created by Administrator on 2017/6/30.
 */

public class ActivityAddCards extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.lvService)
    InnerListView lvService;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.limit)
    CheckBox limit;
    @BindView(R.id.remark)
    EditText remark;


    Map<String, ServiceBean.DataBean> saveService = new LinkedHashMap<>();

    private List<Item> items = new ArrayList<>();

    private AdapterAddCards adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cards);
        ButterKnife.bind(this);
        topbar.setTitle("添加卡种");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new AdapterAddCards(items, ac);
        lvService.setAdapter(adapter);
    }

    @OnClick({R.id.addServiceItems,R.id.btnOK})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addServiceItems:
                /*Bundle bundle = new Bundle();
                bundle.putString("flag", "ActivityAddCards");
                UIHelper.jumpForResult(_activity, ActivityServiceManager.class, bundle, 1001);*/
                UIHelper.jumpForResult(this, ActivitySelectServer.class, ActivitySelectServer.REQUEST_CODE);
                break;
            case R.id.btnOK :
                save();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivitySelectServer.REQUEST_CODE && resultCode == ActivitySelectServer.REQUEST_CODE) {
            ServiceGoodBean.DataBean.JfomServiceBean bean = (ServiceGoodBean.DataBean.JfomServiceBean) data.getSerializableExtra("bean");
            String price = bean.getServicePrice();
            Item item = new Item();
            item.setId(bean.getId());
            item.setName(bean.getName());
            item.setPrice(price);
            item.setCount(bean.getCount());
            if (!items.toString().contains(bean.getName())) {
                items.add(item);
                adapter.notifyDataSetChanged();
            } else {
                UIHelper.t(_activity, "已添加该项目！");
            }
        }
    }


    public class Item {
        private String id="";
        private String name="";
        private String price="";
        private String count="";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", count='" + count + '\'' +
                    '}';
        }
    }

    AdapterAddCards.onDeleteListener onDeleteListener = new AdapterAddCards.onDeleteListener() {
        @Override
        public void delete() {

        }
    };


    private void save() {


        if (name.getText().toString().trim().length() == 0) {
            UIHelper.t(_activity, "请输入卡名称");
            return;
        }
        if (price.getText().toString().trim().length() == 0) {
            UIHelper.t(_activity, "请输入卡售价");
            return;
        }
        if (items.size() == 0) {
            UIHelper.t(_activity, "请添加服务项目");
            return;
        }

        int check = 0;

        if(limit.isChecked()){
            check = 1;
        }else {
            check = 0;
        }
        if(check==1) {
            for (Item item : items) {
                if (item.getCount() != null) {
                    if (item.getCount().equals("")) {
                        item.setCount("无限次");
                    }
                } else {
                    item.setCount("无限次");
                }
            }
        }else {
            for (Item item : items) {
                if (item.getCount() != null) {
                    if (item.getCount().equals("")) {
                        UIHelper.t(ac,"请填写服务次数");
                        return;
                    }
                } else {
                    UIHelper.t(ac,"请填写服务次数");
                    return;
                }
            }
        }



        StringBuilder carItems = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            carItems.append(items.get(i).getName() + "%-"+items.get(i).getCount()+"%-"+items.get(i).getId()+",");
        }



       AppUtil.getCarApiClient(ac).saveOnceCardType(name.getText().toString().trim(),price.getText().toString().trim(),check+"",carItems.toString(),remark.getText().toString(),this);

    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if(res.isOK()){
            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            UIHelper.t(ac,"操作成功");
            finish();
        }else {
            UIHelper.t(ac,res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }
}
