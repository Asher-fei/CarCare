package com.lida.carcare.data;

import android.content.Context;

import com.lida.carcare.bean.ChooseOrderGoodBean;
import com.midian.base.base.BaseListDataSource;
import com.midian.base.bean.NetResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 已选择订货商品列表
 * Created by Administrator on 2017/7/24.
 */

public class ActivityOrderGoodChoosedData extends BaseListDataSource {

    private ArrayList<ChooseOrderGoodBean.DataBean> data;

    public ActivityOrderGoodChoosedData(Context context) {
        super(context);
    }

    public ActivityOrderGoodChoosedData(Context context, ArrayList<ChooseOrderGoodBean.DataBean> data) {
        super(context);
        this.data = data;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        hasMore = false;
        return data;
    }
}
