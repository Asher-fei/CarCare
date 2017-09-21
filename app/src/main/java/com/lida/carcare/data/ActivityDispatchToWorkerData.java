package com.lida.carcare.data;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GetNameBean;
import com.lida.carcare.bean.ServiceItemBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 派单到员工
 * Created by Administrator on 2017/4/5.
 */

public class ActivityDispatchToWorkerData extends BaseListDataSource {

    private String item;
    private String id;
    private boolean flag;

    public ActivityDispatchToWorkerData(Context context, String item, String id, boolean flag) {
        super(context);
        this.item = item;
        this.id = id;
        this.flag = flag;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models=new ArrayList<>();
        if(flag){
            GetNameBean bean = AppUtil.getCarApiClient(ac).getName(item, id);
            String str = bean.getData().getStr();
            String[] pStr = str.split(";");
            for (int i = 0; i < pStr.length; i++) {
                String[] cStr = pStr[i].split("\\$-");
                ServiceItemBean item = null;
                if(cStr.length==1){
                    item = new ServiceItemBean(cStr[0],"","");
                }else if(cStr.length==3){
                    item = new ServiceItemBean(cStr[0],cStr[1],cStr[2]);
                }
                models.add(item);
            }
        }else{
            String[] pStr = item.split(",");
            for (int i = 0; i < pStr.length; i++) {
                ServiceItemBean item = new ServiceItemBean(pStr[i],"","");
                models.add(item);
            }
        }

//        if(items!=null){
//            for (int i = 0; i < items.length; i++) {
//                ServiceItemBean bean = new ServiceItemBean(items[i],"");
//                models.add(bean);
//            }
//        }
        hasMore = false;
        return models;
    }
}
