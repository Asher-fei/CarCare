package com.lida.carcare.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterCheckCar;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarInspectProjectBean;
import com.lida.carcare.widget.InnerListView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 检车
 * Created by Administrator on 2017/6/15.
 */

public class ActivityCheckCar extends BaseActivity {

    @BindView(R.id.title)
    BaseLibTopbarView title;
    @BindView(R.id.lvCheckCar)
    InnerListView lvCheckCar;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.btnOk)
    Button btnOk;

    private String id;
    private CarInspectProjectBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_car);
        ButterKnife.bind(this);
        id = mBundle.getString("id");
        title.setTitle("检车");
        if(mBundle.getString("conclusion")!=null){
            etDes.setText(mBundle.getString("conclusion"));
        }
        title.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        AppUtil.getCarApiClient(ac).selectCarInspectProjectData(id,this);
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
        if (res.isOK()) {
           if("selectCarInspectProjectData".equals(tag)){
               bean = (CarInspectProjectBean) res;
               List<CarInspectProjectBean.DataBean> data = bean.getData();
               AdapterCheckCar adapterCheckCar = new AdapterCheckCar(_activity, data);
               lvCheckCar.setAdapter(adapterCheckCar);
           }
           if("saveInspectionVehicle".equals(tag)){
               UIHelper.t(_activity,"检车记录提交成功！");
               setResult(RESULT_OK);
               finish();
           }
        }else{
            ac.handleErrorCode(_activity, res.getMsg());
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }


    @OnClick(R.id.btnOk)
    public void onViewClicked() {
        String entryName;
        String checkRemarks;
        String detectionOpinion;
        String conclusion = etDes.getText().toString();
        StringBuilder sEntryName = new StringBuilder();
        StringBuilder sCheckRemarks = new StringBuilder();
        StringBuilder sDetectionOpinion = new StringBuilder();
        for (int i = 0; i < bean.getData().size(); i++) {
            sEntryName.append(bean.getData().get(i).getName()+",");

            //检测备注没有时默认传无
            if(bean.getData().get(i).getRemarks().toString().trim().equals("")){
                bean.getData().get(i).setRemarks("无");
            }

            sCheckRemarks.append(bean.getData().get(i).getRemarks()+",");
            sDetectionOpinion.append(bean.getData().get(i).getState()+",");
        }
        LogUtils.e("sCheckRemarks:"+sCheckRemarks);
        LogUtils.e("sDetectionOpinion:"+sDetectionOpinion);
        entryName = sEntryName.toString().substring(0,sEntryName.length()-1);
        checkRemarks = sCheckRemarks.toString().substring(0,sCheckRemarks.length()-1);
        detectionOpinion = sDetectionOpinion.toString().substring(0,sDetectionOpinion.length()-1);
        AppUtil.getCarApiClient(ac).saveInspectionVehicle(id,entryName,checkRemarks,detectionOpinion,
                conclusion,this);
    }
}
