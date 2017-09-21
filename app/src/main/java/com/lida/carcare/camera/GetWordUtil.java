package com.lida.carcare.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.activity.ActivityAddCar;
import com.lida.carcare.bean.OCRBean;
import com.lida.carcare.retrofit.HttpMethods;
import com.midian.base.util.UIHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import rx.Subscriber;


/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class GetWordUtil {

    public static void getCarNumber(final Activity context, String imgFilePath, final String flag){
        Subscriber<OCRBean> subscriber=new Subscriber<OCRBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError");
                UIHelper.t(context,"服务器出小差，识别不成功！(╯︵╰)");
                context.finish();
            }

            @Override
            public void onNext(OCRBean resultBean) {
                LogUtils.e(resultBean);
                String number = resultBean.getCardsinfo().get(0).getItems().get(0).getContent();
                if("ActivityAddCar".equals(flag)){
                    Intent intent = new Intent();
                    intent.putExtra("number",number);
                    context.setResult(Activity.RESULT_OK,intent);
                    context.finish();
                }
                if("MainActivity".equals(flag)){
                    Bundle bundle = new Bundle();
                    bundle.putString("number",number);
                    UIHelper.jump(context, ActivityAddCar.class,bundle);
                }
                if("ActivityOpenTimeCard".equals(flag)){
                    Intent intent = new Intent();
                    intent.putExtra("number",number);
                    context.setResult(Activity.RESULT_OK,intent);
                    context.finish();
                }
            }
        };
        HttpMethods.getInstance().getResult(subscriber,getImgStr(imgFilePath));
    }

    public static String getImgStr(String imgFile){
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encode(data));
    }
}
