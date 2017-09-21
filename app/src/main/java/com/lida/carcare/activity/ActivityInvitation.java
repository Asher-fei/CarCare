package com.lida.carcare.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.NetworkUtils;
import com.midian.base.util.ShareUtil;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邀请同事
 * Created by WeiQingFeng on 2017/5/2.
 */

public class ActivityInvitation extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvWechat)
    TextView tvWechat;
    @BindView(R.id.tvQQ)
    TextView tvQQ;
    @BindView(R.id.tvMessage)
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        ButterKnife.bind(this);
        topbar.setTitle("邀请同事");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tvCode.setText(ac.shopCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("requestCode:"+requestCode);
        LogUtils.e("resultCode:"+resultCode);
        if(requestCode == 1001){
            if(resultCode == RESULT_OK){
                UIHelper.t(_activity,"分享成功！");
            }else{
                UIHelper.t(_activity,"分享失败！");
            }
            return;
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.tvCode, R.id.tvWechat, R.id.tvQQ, R.id.tvMessage})
    public void onViewClicked(View view) {
        String content = "我正在使用星星车宝，车辆管理So easy!，推荐给你，我的邀请码是:" + tvCode.getText().toString()
        +";下载地址：" + "http://120.25.218.134:8080/redRainInterface/dome.html";
        switch (view.getId()) {
            case R.id.tvCode:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(tvCode.getText().toString().trim());
                UIHelper.t(_activity,"复制成功！");
                break;
            case R.id.tvWechat:
                share(SHARE_MEDIA.WEIXIN, "星星车宝",content, "http://120.25.218.134:8080/redRainInterface/dome.html");
                break;
            case R.id.tvQQ:
                share(SHARE_MEDIA.QQ, "星星车宝",content, "http://120.25.218.134:8080/redRainInterface/dome.html");
                break;
            case R.id.tvMessage:
                ShareUtil.sendSMS(_activity,tvCode.getText().toString(),"http://120.25.218.134:8080/redRainInterface/dome.html");
                break;
        }
    }

    UMShareListener listener=new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtils.d("share_media:"+share_media);
            Toast.makeText(_activity, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            LogUtils.e("share_media:"+share_media+throwable.getMessage());
            Toast.makeText(_activity, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            LogUtils.e("share_media:"+share_media);
            Toast.makeText(_activity, share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    public void share(SHARE_MEDIA platform, String title, String content, String targetUrl){
        UMImage umImage = new UMImage(_activity, R.drawable.icon_logo);
        new ShareAction(_activity).setPlatform(platform)
                .withTitle(title)
                .withText(content)
                .withMedia(umImage)
                .withTargetUrl(targetUrl)
                .setCallback(listener)
                .share();
    }

}
