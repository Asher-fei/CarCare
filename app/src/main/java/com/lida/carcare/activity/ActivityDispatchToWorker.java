package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.ServiceItemBean;
import com.lida.carcare.data.ActivityDispatchToWorkerData;
import com.lida.carcare.tpl.ActivityDispatchToWorkerTpl;
import com.midian.base.base.BaseListActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.pulltorefresh.listviewhelper.IDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 派单到员工
 * Created by Administrator on 2017/4/6.
 */

public class ActivityDispatchToWorker extends BaseListActivity {

    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.btnOk)
    Button btnOk;
    private BaseLibTopbarView topbar;

    private String id;//接收上个页面的施工单id；
    private String item;
    private String carNo;
    private String name;

    private String ids = "";
    private int position;
    private StringBuilder goodsProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        carNo = mBundle.getString("carNo");
        name = mBundle.getString("name");
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setTitle("项目派工");
        tvNumber.setText(carNo);
        tvName.setText(name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dispatchtoworker;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        item = mBundle.getString("item");
        id = mBundle.getString("userId");
        LogUtils.e("userId:" + id);
        return new ActivityDispatchToWorkerData(_activity, item, id, mBundle.getBoolean("flag"));
    }

    @Override
    protected Class getTemplateClass() {
        return ActivityDispatchToWorkerTpl.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            ids = data.getStringExtra("ids");
            position = data.getIntExtra("position", 0);
            List<ServiceItemBean> bean = (List<ServiceItemBean>) dataSource.getResultList();
            bean.get(position).setIds(ids);
            LogUtils.e(data.getStringExtra("names"));
            bean.get(position).setWorkers(data.getStringExtra("names"));
            listViewHelper.getAdapter().notifyDataSetChanged();
        }
    }

    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        goodsProject = new StringBuilder();
        List<ServiceItemBean> bean = (List<ServiceItemBean>) dataSource.getResultList();
        Map<Integer, String> temp = new HashMap<>();
        for (int i = 0; i < bean.size(); i++) {
            if (!"".equals(bean.get(i).getWorkers())) {
                String ids = bean.get(i).getIds();
                if (!"".equals(ids)) {
                    temp.put(i, ids);
                }
            }
        }
        if (bean.size() > 0) {
            if (bean.size() != temp.size()) {//判断所有项目是否都已经选择技师
                UIHelper.t(_activity, "请为项目选择服务的技师!");
                return;
            }
            for (int i = 0; i < bean.size(); i++) {
                goodsProject.append(bean.get(i).getItemName() + ":" + temp.get(i) + ";");
            }
            LogUtils.e(goodsProject);
            AppUtil.getCarApiClient(ac).saveOrUpdateCarDispatch(id, goodsProject.toString(), carNo, ac.shopId, this);
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
        btnOk.setClickable(false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        btnOk.setClickable(true);
        if (res.isOK()) {
            UIHelper.t(_activity, "派工成功！");
            setResult(RESULT_OK);
            finish();
        }else{
            UIHelper.t(_activity,res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        btnOk.setClickable(true);
    }
}
