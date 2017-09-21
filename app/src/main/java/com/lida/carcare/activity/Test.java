package com.lida.carcare.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.camera.RectCameraActivity;
import com.midian.base.util.UIHelper;

/**
 * Created by WeiQingFeng on 2017/5/10.
 */

public class Test extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(Test.this, RectCameraActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("onResume");
    }
}
