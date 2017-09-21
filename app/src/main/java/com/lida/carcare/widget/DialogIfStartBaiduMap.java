package com.lida.carcare.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.lida.carcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 启动百度地图？
 * Created by xkr on 2017/8/22.
 */

public class DialogIfStartBaiduMap extends Dialog {
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv)
    TextView tv;

    LatLng self;
    LatLng target;

    private Context context;
    private Activity activity;

    public DialogIfStartBaiduMap(Context context,LatLng self,
            LatLng target) {
        super(context, R.style.diy_dialog);
        this.self = self;
        this.target = target;
        this.context = context;
        init(context);
    }

    public DialogIfStartBaiduMap(Context context, int themeResId) {
        super(context, R.style.diy_dialog);
        this.context = context;
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.activity = (Activity) context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_ifstartbaidumap, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
    }

    @OnClick({R.id.btn_cancel, R.id.btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_ok:
                dismiss();
                NaviParaOption para = new NaviParaOption()
                        .startPoint(self).endPoint(target)
                        .startName("我的位置").endName("目的地");
                BaiduMapNavigation.openBaiduMapNavi(para, context);
                break;
        }
    }
}
