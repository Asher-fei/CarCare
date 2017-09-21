package com.lida.carcare.fragment;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityBusinessStatistics;
import com.lida.carcare.activity.ActivityCarManege;
import com.lida.carcare.activity.ActivityCars;
import com.lida.carcare.activity.ActivityChooseCustomer;
import com.lida.carcare.activity.ActivityEnterprises;
import com.lida.carcare.activity.ActivityLocateTheRescue;
import com.lida.carcare.activity.ActivityMemberManager;
import com.lida.carcare.activity.ActivityPlaceAnOrder;
import com.lida.carcare.activity.ActivityPublicAppointment;
import com.lida.carcare.activity.ActivityRemind;
import com.lida.carcare.activity.ActivityServiceManager;
import com.lida.carcare.activity.ActivityWarehouseManege;
import com.lida.carcare.activity.ActivityWorkOrder;
import com.lida.carcare.activity.LineWebView;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.BannerBean;
import com.lida.carcare.bean.BannerDetailBean;
import com.lida.carcare.bean.LineBean;
import com.lida.carcare.bean.LineDetailBean;
import com.lida.carcare.bean.LocateTheRescueBean;
import com.lida.carcare.bean.PublicAppointmentBean;
import com.lida.carcare.camera.RectCameraActivity;
import com.lida.carcare.widget.MarqueeTextView;
import com.lida.carcare.widget.MarqueeTextView.OnItemClickListener;
import com.lida.carcare.widget.SharepreferenceUtils;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 首页
 * Created by Administrator on 2017/4/5.
 */

public class FragmentHome extends BaseFragment implements OnPermissionCallback {
    @BindView(R.id.banner)
    Banner bannerView;
    Unbinder unbinder;
    @BindView(R.id.tvTab1)
    TextView tvTab1;
    @BindView(R.id.tvTab3)
    TextView tvTab3;
    @BindView(R.id.tvTab4)
    TextView tvTab4;
    @BindView(R.id.tvTab5)
    TextView tvTab5;
    @BindView(R.id.tvTab6)
    TextView tvTab6;
    @BindView(R.id.tvTab7)
    TextView tvTab7;
    @BindView(R.id.tvTab8)
    TextView tvTab8;
    @BindView(R.id.tvTab9)
    TextView tvTab9;
    @BindView(R.id.tvTab10)
    TextView tvTab10;
    @BindView(R.id.tvTab11)
    TextView tvTab11;
    @BindView(R.id.tvTab12)
    TextView tvTab12;
    @BindView(R.id.marqueeTextView)
    MarqueeTextView marqueeTextView;


    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.left_ib)
    ImageButton leftIb;
    @BindView(R.id.right_ib)
    ImageButton rightIb;
    @BindView(R.id.tvTab13)
    TextView tvTab13;
    @BindView(R.id.messageCount)
    TextView messageCount;
    @BindView(R.id.rescueCount)
    TextView rescueCount;
    /*@BindView(R.id.topbar)
    BaseLibTopbarView topbar;*/

    private List<BannerBean.Data> bannerBeanData = null;
    private PermissionHelper permissionHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, v);
        initView();
        initTopbar();
        return v;
    }

    private void initView() {
        permissionHelper = PermissionHelper.getInstance(_activity);
       /* topbar.setTitle(ac.shopName);
        topbar.setLeftImageButton(R.drawable.icon_camera, listener);
        topbar.setRightImageButton(R.drawable.icon_message, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity, ActivityPublicAppointment.class);
            }
        });
        topbar.setBackgroundColor(Color.parseColor("#FF5C33"));*/

//        for (int i = 0; i < 3; i++)
//        {
//            images.add(R.drawable.icon_banner);
//        }
//        bannerView.setBannerStyle(Banner.CIRCLE_INDICATOR);//设置圆形指示器
//        bannerView.setIndicatorGravity(Banner.CENTER);
//        bannerView.isAutoPlay(true);
//        bannerView.setDelayTime(5000);//设置轮播间隔时间
//        bannerView.setImages(images.toArray());
//        initMarqueeView();
        AppUtil.getCarApiClient(ac).getBanner(this);
        AppUtil.getCarApiClient(ac).getLine(this);
    }

    private void initTopbar() {
        titleTv.setText(ac.shopName);
        leftIb.setImageResource(R.drawable.icon_camera);
        leftIb.setOnClickListener(listener);
        rightIb.setImageResource(R.drawable.icon_message);
        rightIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.jump(_activity, ActivityPublicAppointment.class);
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (permissionHelper.isPermissionGranted(Manifest.permission.CAMERA) &&
                    permissionHelper.isPermissionGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Bundle bundle = new Bundle();
                bundle.putString("flag", "MainActivity");
                UIHelper.jump(_activity, RectCameraActivity.class, bundle);
            } else {
                permissionHelper = PermissionHelper.getInstance(_activity);
                permissionHelper.requestAfterExplanation(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE});
            }
        }
    };

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if (res.isOK()) {
            if ("getLine".equals(tag)) {
                LineBean bean = (LineBean) res;
                initMarqueeView(bean);
            } else if ("getLineDetail".equals(tag)) {
                LineDetailBean bean = (LineDetailBean) res;
                Bundle bundle = new Bundle();
                bundle.putString("data", bean.getData().getLineDetail());
                UIHelper.jump(_activity, LineWebView.class, bundle);
            } else if ("getBanner".equals(tag)) {
                BannerBean bean = (BannerBean) res;
                initBannerView(bean);
            } else if ("getBannerDetail".equals(tag)) {
                BannerDetailBean bean = (BannerDetailBean) res;
                Bundle bundle = new Bundle();
                bundle.putString("data", bean.getData().getBannerDetail());
                UIHelper.jump(_activity, LineWebView.class, bundle);
            } else if ("selectWeiXinAppList".equals(tag)) {
                PublicAppointmentBean bean = (PublicAppointmentBean) res;
                if (bean != null && bean.getData() != null) {
                    if (bean.getData().size() > 0) {
                        LogUtils.i(bean.getData());
                        List<String> pts = new ArrayList<>();
                        String s = SharepreferenceUtils.getPrefString(_activity, "ActivityPublicAppointment", "");
                        LogUtils.i(s);
                        int count = bean.getData().size();
                        if (!"".equals(s)) {
                            String[] split = s.split(";");
                            for (int i = 0; i < split.length; i++) {
                                pts.add(split[i]);
                            }
                            for (int i = 0; i < bean.getData().size(); i++) {
                                for (String t : pts) {
                                    if (t.equals(bean.getData().get(i).getId())) {
                                        count = count - 1;
                                    }
                                }
                            }
                        }
                        if (count > 0) {
                            messageCount.setVisibility(View.VISIBLE);
                            messageCount.setText(count + "");
                        } else {
                            messageCount.setVisibility(View.INVISIBLE);
                        }

                    }
                }
            } else if ("selectPositioningTheRescue".equals(tag)) {
                LocateTheRescueBean bean = (LocateTheRescueBean) res;
                if (bean != null && bean.getData() != null) {
                    if (bean.getData().size() > 0) {
                        LogUtils.i(bean.getData());
                        List<String> pts = new ArrayList<>();
                        String s = SharepreferenceUtils.getPrefString(_activity, "ActivityLocateTheRescue", "");
                        LogUtils.i(s);
                        int count = bean.getData().size();
                        if (!"".equals(s)) {
                            String[] split = s.split(";");
                            for (int i = 0; i < split.length; i++) {
                                pts.add(split[i]);
                            }
                            for (int i = 0; i < bean.getData().size(); i++) {
                                for (String t : pts) {
                                    if (t.equals(bean.getData().get(i).getId())) {
                                        count = count - 1;
                                    }
                                }
                            }
                        }
                        if (count > 0) {
                            rescueCount.setVisibility(View.VISIBLE);
                            rescueCount.setText(count + "");
                        } else {
                            rescueCount.setVisibility(View.INVISIBLE);
                        }

                    }
                }
            }
        }
    }

    private void initBannerView(BannerBean bean) {
        bannerBeanData = bean.getData();
        List<String> images = new ArrayList<>(bannerBeanData.size());
        for (BannerBean.Data tmp : bannerBeanData) {
            images.add(Constant.BASEIMG + tmp.getBannerImg());
        }
        LogUtils.e(images);
        bannerView.setBannerStyle(Banner.CIRCLE_INDICATOR);//设置圆形指示器
        bannerView.setIndicatorGravity(Banner.CENTER);
        bannerView.isAutoPlay(true);
        bannerView.setDelayTime(5000);//设置轮播间隔时间
        bannerView.setImages(images.toArray());
        bannerView.setOnBannerClickListener(new Banner.OnBannerClickListener() {
            @Override
            public void OnBannerClick(View view, int position) {
                BannerBean.Data data = bannerBeanData.get(position - 1);
                AppUtil.getCarApiClient(ac).getBannerDetail(data.getId(), FragmentHome.this);
            }
        });
    }

    private void initMarqueeView(LineBean bean) {
        List<LineBean.DataBean> data = bean.getData();
        List<View> views = new ArrayList<>();

//        news.add("一周年至庆，所有商品八折优惠");
//        news.add("更换刹车油注意事项及视频");
//        news.add("它没有改变速度，只是让你忘了速度");
//        news.add("小炮中规性价比最高？千元打造上路超跑");
////        news.add("送给爱车的朋友，编辑如何测试一辆车");
//        news.add("就是不服！战神GT-R直线挑战保时捷918");

        for (int i = 0; i < data.size(); i++) {
            LineBean.DataBean dataBean = data.get(i);
            TextView tv = new TextView(_activity);
            tv.setTextSize(13);
            tv.setTextColor(Color.parseColor("#232323"));
            tv.setSingleLine();
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setText(dataBean.getLineName());
            tv.setTag(dataBean);
            views.add(tv);
        }
        marqueeTextView.setViews(views);
        marqueeTextView.setOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view) {
                        LineBean.DataBean dataBean = (LineBean.DataBean) view.getTag();
                        AppUtil.getCarApiClient(ac).getLineDetail(dataBean.getId(), FragmentHome.this);
//                        Log.d("OnItemClickListener", "==> dataBean = " + dataBean.getId());
                    }
                }
        );

//        for (int i = 0; i < news.size(); i += 2) {
//            final int position = i;
//            LinearLayout moreView = (LinearLayout) LayoutInflater.from(_activity).inflate(R.layout.item_marqueeview, null);
//            TextView tv1 = (TextView) moreView.findViewById(R.userId.tv1);
//            TextView tv2 = (TextView) moreView.findViewById(R.userId.tv2);
//
//            moreView.findViewById(R.userId.tv1).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Toast.makeText(_activity, position + "你点击了" + news.get(position).toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            moreView.findViewById(R.userId.tv2).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Toast.makeText(_activity, position + "你点击了" + news.get(position).toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            tv1.setText(news.get(i).toString());
//            if (news.size() > i + 1) {
//                tv2.setText(news.get(i + 1).toString());
//            } else {
//                moreView.findViewById(R.userId.tv2).setVisibility(View.GONE);
//            }
//            views.add(moreView);
//            marqueeTextView.setViews(views);
//            marqueeTextView.setOnItemClickListener(new MarqueeTextView.OnItemClickListener() {
//                @Override
//                public void onItemClick(int position, View view) {
//                    LogUtils.e("你点击了第几个items" + position);
//                }
//            });
//        }
    }


    @OnClick({R.id.tvTab1, R.id.tvTab3, R.id.tvTab4, R.id.tvTab5, R.id.tvTab6, R.id.tvTab7, R.id.tvTab8, R.id.tvTab9, R.id.tvTab10, R.id.tvTab11, R.id.tvTab12, R.id.tvTab13, R.id.tvTab14})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tvTab1:
                UIHelper.jump(_activity, ActivityCars.class);
                break;
//            case R.userId.tvTab2:
//                UIHelper.jump(_activity,ActivityBillingRecords.class);
//                break;
            case R.id.tvTab3:
                UIHelper.jump(_activity, ActivityWorkOrder.class);
                break;
            case R.id.tvTab4:
                if ("0".equals(ac.user_type) || "4".equals(ac.user_type)) {
                    UIHelper.jump(_activity, ActivityBusinessStatistics.class);
                } else {
                    UIHelper.t(_activity, "管理权限不够，不能进入");
                }
                break;
            case R.id.tvTab5:
//                UIHelper.t(_activity, "正在建设中，敬请期待！");
                UIHelper.jump(_activity, ActivityRemind.class);
                break;
            case R.id.tvTab6:
//                UIHelper.t(_activity, "正在建设中，敬请期待！");
                UIHelper.jump(_activity, ActivityEnterprises.class);
                break;
            case R.id.tvTab7:
//                UIHelper.t(_activity, "正在建设中，敬请期待！");
                UIHelper.jump(_activity, ActivityMemberManager.class);
                break;
            case R.id.tvTab8:
                bundle.putString("flag", "FragmentHome");
                UIHelper.jump(_activity, ActivityChooseCustomer.class, bundle);
                break;
            case R.id.tvTab9:
                bundle.putString("flag", "FragmentHome");
                UIHelper.jump(_activity, ActivityCarManege.class, bundle);//车辆管理
                break;
            case R.id.tvTab10:
                if ("0".equals(ac.user_type) || "4".equals(ac.user_type)||"6".equals(ac.user_type)) {
                    UIHelper.jump(_activity, ActivityWarehouseManege.class);//库存管理
                } else {
                    UIHelper.t(_activity, "管理权限不够，不能进入");
                }

                break;
//            case R.id.tvTab11:
//                if ("1".equals(ac.user_type))
//                {
//                    UIHelper.t(_activity, "管理权限不够，不能进入");
//                    return;
//                }
//                UIHelper.jump(_activity, ActivityGoodsManager.class);
//                break;
            case R.id.tvTab12:
                if ("0".equals(ac.user_type) || "4".equals(ac.user_type)||"6".equals(ac.user_type)) {
                    UIHelper.jump(_activity, ActivityServiceManager.class);
                } else {
                    UIHelper.t(_activity, "管理权限不够，不能进入");
                }
                break;
            case R.id.tvTab13:
                UIHelper.jump(_activity, ActivityPlaceAnOrder.class);//订货
                break;
            case R.id.tvTab14:
                UIHelper.jump(_activity, ActivityLocateTheRescue.class);       //救援
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        UIHelper.jump(_activity, RectCameraActivity.class);
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
    }

    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        LogUtils.e(permissionName);
        if (permissionName.contains("CAMERA")) {
            UIHelper.t(_activity, "扫描车牌需要开启照相机权限");
            return;
        }
        if (permissionName.contains("READ_EXTERNAL_STORAGE")) {
            UIHelper.t(_activity, "扫描车牌需要开启文件读取权限");
            return;
        }
    }

    @Override
    public void onNoPermissionNeeded() {
        UIHelper.jump(_activity, RectCameraActivity.class);
    }


    @Override
    public void onResume() {
        super.onResume();
        AppUtil.getCarApiClient(ac).selectWeiXinAppList(this);
        AppUtil.getCarApiClient(ac).selectPositioningTheRescue(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
