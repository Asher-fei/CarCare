package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterServiceChild;
import com.lida.carcare.adapter.AdapterServiceParent;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.AddGoodBean;
import com.lida.carcare.bean.ServiceBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogAddClass;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 服务分类设置
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityServiceClassSetting extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvAddChild)
    TextView tvAddChild;
    @BindView(R.id.parentList)
    ListView parentList;
    @BindView(R.id.childList)
    ListView childList;
    @BindView(R.id.btnAddParentClass)
    Button btnAddParentClass;

    private AdapterServiceParent pAdapter;
    private List<ServiceBean.DataBean> pData = new ArrayList<>();

    private AdapterServiceChild cAdapter;
    private Map<String, List<ServiceBean.DataBean>> cData = new HashMap<>();
    private String type;

    private int p = 0;//记录当前一级分类的位置；
    private DialogAddClass dialogAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceclasssetting);
        ButterKnife.bind(this);
        type = mBundle.getString("type");
        init();
        AppUtil.getCarApiClient(ac).getCategory(ac.shopId, callback);
    }

    private void init() {
        topbar.setTitle("服务分类设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        pAdapter = new AdapterServiceParent(_activity, pData, type);
        pAdapter.setOnItemClick(onItemClick);
        parentList.setAdapter(pAdapter);
        cAdapter = new AdapterServiceChild(_activity, cData, type);
        childList.setAdapter(cAdapter);
    }

    AdapterServiceParent.onItemClick onItemClick = new AdapterServiceParent.onItemClick() {
        @Override
        public void onItemClick(int position) {
            p = position;
            AppUtil.getCarApiClient(ac).getCategoryByCode(pData.get(position).getCode(), ac.shopId, callback);//获取二级分类
            cAdapter.setFlag(pData.get(position).getName());
            cAdapter.notifyDataSetChanged();
        }
    };

    BaseApiCallback callback = new BaseApiCallback() {

        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            if (res.isOK()) {
                if ("getCategory".equals(tag)) {
                    ServiceBean bean = (ServiceBean) res;
                    //清除之前的分类数据
                    pData.clear();
                    cData.clear();
                    for (int i = 0; i < bean.getData().size(); i++) {
                        pData.add(bean.getData().get(i));
                    }
                    if (pData.size() > 0) {
                        cAdapter.setFlag(pData.get(0).getName());
                        AppUtil.getCarApiClient(ac).getCategoryByCode(pData.get(0).getCode(), ac.shopId, callback);
                    }
                    pAdapter.notifyDataSetChanged();
                }
                if ("getCategoryByCode".equals(tag)) {
                    ServiceBean bean = (ServiceBean) res;
                    if (bean.getData() != null) {
                        List<ServiceBean.DataBean> child = new ArrayList<>();
                        child.addAll(bean.getData());
                        cData.put(pData.get(p).getName(), child);
                        cAdapter.notifyDataSetChanged();
                    }
                }
                if ("saveServiceCode".equals(tag)) {
                    AddGoodBean bean = (AddGoodBean) res;
                    ServiceBean.DataBean item = new ServiceBean.DataBean();
                    item.setId(bean.getData().getId());
                    item.setCode(bean.getData().getCode());
                    item.setName(bean.getData().getName());
                    cData.get(pData.get(p).getName()).add(item);
                    cAdapter.notifyDataSetChanged();
                    dialogAddClass.dismiss();
                }
            }
        }
    };


    @OnClick({R.id.tvAddChild, R.id.btnAddParentClass})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tvAddChild:
                if (pData.size() == 0) {
                    UIHelper.t(_activity, "请添加大类！");
                    return;
                }
                dialogAddClass = new DialogAddClass(_activity, "新增细类");
                dialogAddClass.setOnOkButtonClick(new DialogAddClass.onOkButtonClick() {
                    @Override
                    public void add() {
                        if (!"".equals(dialogAddClass.getContent())) {
                            dialogAddClass.dismiss();
                            AppUtil.getCarApiClient(ac).saveServiceCode(ac.shopId, pData.get(p).getCode(), dialogAddClass.getContent(), callback);
                        }
                    }
                });
                dialogAddClass.show();
                break;
            case R.id.btnAddParentClass:
                final DialogAddClass dialogAddClass = new DialogAddClass(_activity, "新增大类");
                dialogAddClass.setOnOkButtonClick(new DialogAddClass.onOkButtonClick() {
                    @Override
                    public void add() {
                        if ("".equals(dialogAddClass.getContent())) {
                            UIHelper.t(_activity,"请填写类别名称");
                            return;
                        }
                        dialogAddClass.dismiss();
                        AppUtil.getCarApiClient(ac).saveServiceCode(ac.shopId, "",dialogAddClass.getContent(),new BaseApiCallback(){

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
                                    AddGoodBean result = (AddGoodBean) res;
                                    ServiceBean.DataBean bean = new ServiceBean.DataBean();
                                    bean.setId(result.getData().getId());
                                    bean.setName(result.getData().getName());
                                    bean.setCode(result.getData().getCode());
                                    if(pData.size()>1){
                                        pData.add(pData.size()-1,bean);
                                    }else{
                                        pData.add(bean);
                                    }
                                   // AppUtil.getCarApiClient(ac).getCategory(ac.shopId, callback);
                                    pAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                super.onApiFailure(t, errorNo, strMsg, tag);
                                hideLoadingDlg();
                            }
                        });
                    }
                });
                dialogAddClass.show();
                break;
        }
    }

    public void defalutItem(){
        AppUtil.getCarApiClient(ac).getCategory(ac.shopId, callback);
    }
}
