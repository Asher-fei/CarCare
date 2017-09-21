package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityLocateTheRescue;
import com.lida.carcare.bean.LocateTheRescueBean;
import com.lida.carcare.widget.DialogCall;
import com.lida.carcare.widget.DialogIfStartBaiduMap;
import com.lida.carcare.widget.SharepreferenceUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xkr on 2017/8/11.
 */

public class ActivityLocateTheRescueTpl extends BaseTpl<LocateTheRescueBean.DataBean> {

    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.lx)
    TextView lx;
    @BindView(R.id.ly)
    TextView ly;
    @BindView(R.id.createtime)
    TextView createtime;
    @BindView(R.id.phone)
    TextView phone;

    public ActivityLocateTheRescueTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityLocateTheRescueTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_locate_the_rescus;
    }

    @Override
    public void setBean(final LocateTheRescueBean.DataBean bean, int position) {
        if (bean != null) {
            List<String> pts = new ArrayList<>();
            String s = SharepreferenceUtils.getPrefString(_activity, "ActivityLocateTheRescue", "");
            StringBuffer buffer = new StringBuffer();
            buffer.append(s);
            boolean isSave = true;
            if (!"".equals(s)) {
                String[] split = s.split(";");
                for (int i = 0; i < split.length; i++) {
                    pts.add(split[i]);
                }

                for (int i = 0; i < pts.size(); i++) {
                    if (bean.getId().equals(pts.get(i))) {
                        isSave = false;
                    }
                }
            }
            if (isSave == true) {
                buffer.append(bean.getId() + ";");
                SharepreferenceUtils.setPrefString(_activity, "ActivityLocateTheRescue", buffer.toString());
            }

            address.setText(bean.getL() == null ? "" : bean.getL());
            lx.setText(bean.getLx() == null ? "" : bean.getLx());
            ly.setText(bean.getLy() == null ? "" : bean.getLy());
            createtime.setText(bean.getCreatetime() == null ? "" : bean.getCreatetime());
            phone.setText(bean.getPhone()==null?"":bean.getPhone());
            phone.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.getPhone()!=null&&!bean.getPhone().equals("")) {
                       new DialogCall(_activity, bean.getPhone()).show();
                    }
                }
            });

            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.getLx() == null || bean.getLy() == null || bean.getLx().equals("") || bean.getLy().equals("")) {
                        UIHelper.t(_activity, "救援地址经纬度获取失败...");
                        return;
                    }
                    double lat = Double.parseDouble(bean.getLx());
                    double lon = Double.parseDouble(bean.getLy());
                    if (ActivityLocateTheRescue.latitude == 0.0 || ActivityLocateTheRescue.lontitude == 0.0) {
                        UIHelper.t(_activity, "我的位置获取失败...");
                        return;
                    }
                    // LatLng target = new LatLng(lat, lon);
                    LatLng sourceLatLng = new LatLng(lat, lon);
                    // 将google地图、soso地图、aliyun地图、mapabc地图和amap地图// 所用坐标转换成百度坐标
                    CoordinateConverter converter = new CoordinateConverter();
                    converter.from(CoordinateConverter.CoordType.COMMON);
                    // sourceLatLng待转换坐标
                    converter.coord(sourceLatLng);
                    LatLng target = converter.convert();
                    LogUtils.d(target.latitude+","+target.longitude);

                    LatLng self = new LatLng(ActivityLocateTheRescue.latitude, ActivityLocateTheRescue.lontitude);

                    new DialogIfStartBaiduMap(_activity, self, target).show();
                }
            });


        }

    }
}