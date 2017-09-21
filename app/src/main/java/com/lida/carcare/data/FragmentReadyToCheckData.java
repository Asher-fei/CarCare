package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.MemberBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;

/**
 * 员工管理-待审核
 * Created by Administrator on 2017/4/5.
 */

public class FragmentReadyToCheckData extends BaseListDataSource
{

    private String type;

    public FragmentReadyToCheckData(Context context, String type)
    {
        super(context);
        this.type = type;
    }

    public FragmentReadyToCheckData(Context context)
    {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception
    {
        this.page = page;
        MemberBean bean = AppUtil.getCarApiClient(ac).auditStatus(type, page + "", 20 + "");
        ArrayList<NetResult> models = new ArrayList<>();
        for (int i = 0; i < bean.getData().size(); i++)
        {
            models.add(bean.getData().get(i));
            if (bean.getData().size() < 20)
            {
                hasMore = false;
            }else {
                hasMore = true;
            }
        }
        return models;
    }
}
