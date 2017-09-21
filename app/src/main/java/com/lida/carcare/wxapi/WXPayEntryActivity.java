package com.lida.carcare.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.activity.ActivityOrderedList;
import com.midian.base.util.UIHelper;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        LogUtils.i(req);
    }

    @Override
    public void onResp(BaseResp resp) {
        LogUtils.i(resp);
        if(resp.errCode==0){
            UIHelper.t(this,"微信支付成功");
            UIHelper.jump(this, ActivityOrderedList.class);
        }
        else if(resp.errCode==-1){
            UIHelper.t(this,"微信支付失败");
        }
        else if(resp.errCode==-2){
            UIHelper.t(this,"取消微信支付");
        }
        finish();
    }


}