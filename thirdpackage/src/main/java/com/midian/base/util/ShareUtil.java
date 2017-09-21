package com.midian.base.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class ShareUtil {

    public static String weiboAppId = "902141203";
    public static String weiboAppSecret = "347df4167afd79fa1c822860a15aae14";
    public static String qqAppId = "1106152667";
    public static String qqAppKEY = "NJTHYNkjLoBAglDH";
    public static String weixinAppId = "wx633190b9e554d9e9";
    public static String weixinAppSecret = "a75847af465a604e28fb8c594e72364b";

    public static void init(){
        PlatformConfig.setWeixin(weixinAppId, weixinAppSecret);
        PlatformConfig.setSinaWeibo(weiboAppId, weiboAppSecret);
        PlatformConfig.setQQZone(qqAppId,qqAppKEY);
    }

    /**
     * 发短信
     */
    public static void sendSMS(Activity activity, String code, String webUrl){
        String smsBody = "我正在使用星星车宝，车辆管理So easy!，推荐给你，我的邀请码是:" + code
                +";下载地址：" + webUrl;
        Uri smsToUri = Uri.parse( "smsto:" );
        Intent sendIntent =  new  Intent(Intent.ACTION_VIEW, smsToUri);
        sendIntent.putExtra( "sms_body", smsBody);
        sendIntent.setType( "vnd.android-dir/mms-sms" );
        activity.startActivityForResult(sendIntent, 1001 );
    }
}
