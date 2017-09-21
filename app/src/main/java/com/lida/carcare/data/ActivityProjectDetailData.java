package com.lida.carcare.data;

import android.content.Context;

import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 项目详情
 * Created by Administrator on 2017/4/5.
 */

public class ActivityProjectDetailData extends BaseListDataSource {

    public ActivityProjectDetailData(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            models.add(new NetResult());
        }
        hasMore=false;
        return models;
    }
}
