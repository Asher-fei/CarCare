package com.lida.carcare.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class ShareDialog extends Dialog {

    @BindView(R.id.iv_WeChat)
    ImageView ivWeChat;
    @BindView(R.id.iv_Circle)
    ImageView ivCircle;
    @BindView(R.id.iv_Sina)
    ImageView ivSina;
    private Context context;
    private String title;
    private String content;
    private String imageUrl;
    private String targetUrl;
    //imageurl不能为空

    public ShareDialog(Context context) {
        super(context,R.style.bottom_dialog);
        init(context);
    }

    public ShareDialog(Context context, int themeResId) {
        super(context, R.style.bottom_dialog);
        init(context);
    }

    public ShareDialog(Context context, String title, String content, String imageUrl, String targetUrl) {
        super(context,R.style.bottom_dialog);
        init(context);
        this.title=title;
        this.content=content;
        this.imageUrl=imageUrl;
        this.targetUrl=targetUrl;
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_share, null);
        this.setContentView(v);
        ButterKnife.bind(this,v);
    }

    @OnClick({R.id.iv_WeChat, R.id.iv_Circle, R.id.iv_Sina})
    public void onClick(View view) {
        LogUtils.e(view.getId());
        switch (view.getId()) {
            case R.id.iv_WeChat:
                share(SHARE_MEDIA.WEIXIN,title,content,imageUrl,targetUrl);
                break;
            case R.id.iv_Circle:
                share(SHARE_MEDIA.QQ,title,content,imageUrl,targetUrl);
                break;
            case R.id.iv_Sina:
                share(SHARE_MEDIA.SINA,title,content,imageUrl,targetUrl);
                break;
        }
    }

    UMShareListener listener=new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtils.d("share_media:"+share_media);
            Toast.makeText(context, share_media + " 分享成功", Toast.LENGTH_SHORT).show();
            dismiss();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            LogUtils.e("share_media:"+share_media+throwable.getMessage());
            Toast.makeText(context, share_media + " 分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            LogUtils.e("share_media:"+share_media);
            Toast.makeText(context, share_media + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    public void share(SHARE_MEDIA platform, String title, String content, String imageUrl, String targetUrl){
        new ShareAction((Activity) context).setPlatform(platform)
                .withTitle(title)
                .withText(content)
                .withMedia(new UMImage(context,imageUrl))
                .withTargetUrl(targetUrl)
                .setCallback(listener)
                .share();
    }
}
