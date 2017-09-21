package com.lida.carcare.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterGoodChild;
import com.lida.carcare.adapter.AdapterGoodParent;
import com.lida.carcare.adapter.AdapterServiceChild;
import com.lida.carcare.adapter.AdapterServiceParent;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.AddGoodBean;
import com.lida.carcare.bean.GoodClassABean;
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
 * 商品分类设置
 * Created by WeiQingFeng on 2017/4/18.
 */

public class ActivityGoodsClassSetting extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvAddChild)
    TextView tvAddChild;
    @BindView(R.id.parentList)
    ListView parentList;
    @BindView(R.id.childList)
    ListView childList;

    private AdapterGoodParent pAdapter;
    private List<GoodClassABean.DataBean> pData=new ArrayList<>();

    private AdapterGoodChild cAdapter;
    private Map<String, List<GoodClassABean.DataBean>> cData = new HashMap<>();
    private String type;

    private int p = 0;//记录当前一级分类的位置；
    private DialogAddClass dialogAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsclasssetting);
        ButterKnife.bind(this);
        init();
        AppUtil.getCarApiClient(ac).getProductCategory(ac.shopId,callback);
    }

    private void init() {
        type=mBundle.getString("type");
        topbar.setTitle("商品分类设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        pAdapter=new AdapterGoodParent(_activity,pData,type);
        pAdapter.setOnItemClick(onItemClick);
        parentList.setAdapter(pAdapter);
        cAdapter=new AdapterGoodChild(_activity,cData,type);
        childList.setAdapter(cAdapter);
    }

    AdapterGoodParent.onItemClick onItemClick=new AdapterGoodParent.onItemClick() {
        @Override
        public void onItemClick(int position) {//// TODO: 2017/6/6 网络优化，防止多次请求
            p = position;
            AppUtil.getCarApiClient(ac).getProductCategoryByCode(pData.get(position).getCode(),ac.shopId,callback);//获取二级分类
            cAdapter.setFlag(pData.get(position).getName());
            cAdapter.notifyDataSetChanged();
        }
    };

    BaseApiCallback callback = new BaseApiCallback(){


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
            if(res.isOK()){
                if("getProductCategory".equals(tag)){
                    GoodClassABean bean = (GoodClassABean) res;

                    //清除之前分类数据
                    pData.clear();
                    cData.clear();

                    if(bean.getData()!=null){
                        for (int i = 0; i < bean.getData().size(); i++) {
                            pData.add(bean.getData().get(i));
                        }
                        if(pData.size()>0){
                            cAdapter.setFlag(pData.get(0).getName());
                            AppUtil.getCarApiClient(ac).getProductCategoryByCode(pData.get(0).getCode(),ac.shopId,callback);
                        }
                        pAdapter.notifyDataSetChanged();
                    }
                }
                if("getProductCategoryByCode".equals(tag)){
                    GoodClassABean bean = (GoodClassABean) res;
                    if(bean.getData()!=null){
                        List<GoodClassABean.DataBean> child = new ArrayList<>();
                        child.addAll(bean.getData());
                        if(p<pData.size()) {
                            cData.put(pData.get(p).getName(), child);
                        }
                        cAdapter.notifyDataSetChanged();
                    }
                }
                if("saveProductParentCode".equals(tag)){
                    AddGoodBean bean = (AddGoodBean) res;
                    GoodClassABean.DataBean item = new GoodClassABean.DataBean();
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
                dialogAddClass = new DialogAddClass(_activity,"新增细类");
                dialogAddClass.setOnOkButtonClick(new DialogAddClass.onOkButtonClick() {
                    @Override
                    public void add() {
                        if(!"".equals(dialogAddClass.getContent())){
                            dialogAddClass.dismiss();
                            AppUtil.getCarApiClient(ac).saveProductParentCode(pData.get(p).getCode(),dialogAddClass.getContent(),ac.shopId,callback);
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
                        AppUtil.getCarApiClient(ac).saveProductParentCode("",dialogAddClass.getContent(),ac.shopId, new BaseApiCallback(){

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
                                if(res.isOK()){
                                    AddGoodBean result = (AddGoodBean) res;
                                    GoodClassABean.DataBean bean = new GoodClassABean.DataBean();
                                    bean.setName(result.getData().getName());
                                    bean.setCode(result.getData().getCode());
                                    bean.setId(result.getData().getId());
                                    if(pData.size()>1){
                                        pData.add(pData.size()-1,bean);
                                    }else{
                                        pData.add(bean);
                                    }
                                    p = pData.size()-1;
                                    pAdapter.notifyDataSetChanged();

                                }
                            }
                        });
                    }
                });
                dialogAddClass.show();
                break;
        }
    }

    public void defaultRefresh(){
        AppUtil.getCarApiClient(ac).getProductCategory(ac.shopId,callback);
    }
}
