package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterPlaceAnOrder;
import com.lida.carcare.adapter.AdapterPlaceOrderRecommend;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.BannerBean;
import com.lida.carcare.bean.BannerDetailBean;
import com.lida.carcare.bean.LineBean;
import com.lida.carcare.bean.LineDetailBean;
import com.lida.carcare.bean.OrderAdvertisementBean;
import com.lida.carcare.bean.OrderClassificationBean;
import com.lida.carcare.bean.OrderRecommendedBean;
import com.lida.carcare.fragment.FragmentHome;
import com.lida.carcare.widget.InnerGridView;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订货
 * Created by xkr on 2017/7/24.
 */

public class ActivityPlaceAnOrder extends BaseActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.gridView_class)
    InnerGridView gridViewClass;
    @BindView(R.id.gridView_recommend)
    InnerGridView gridViewRecommend;


    private List<BannerBean.Data> bannerBeanData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_an_order);
        ButterKnife.bind(this);
        topbar.setTitle("订货");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("我的订单", listener);
        //AppUtil.getCarApiClient(ac).getBanner(this);
        AppUtil.getCarApiClient(ac).SelectOrderClassification(this);
        AppUtil.getCarApiClient(ac).SelectOrderRecommended(this);
        AppUtil.getCarApiClient(ac).selectByOrderAdvertisement(this);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jump(_activity,ActivityOrderedList.class);
        }
    };

    @Override
    public void onApiSuccess(NetResult res, String tag)
    {
        if (res.isOK())
        {
          if ("getBanner".equals(tag))
            {
                BannerBean bean = (BannerBean) res;
                initBannerView(bean);
            } else if ("getBannerDetail".equals(tag))
            {
                BannerDetailBean bean = (BannerDetailBean) res;
                Bundle bundle = new Bundle();
                bundle.putString("userId","123");
               // UIHelper.jump(_activity, ActivityGoodDetail.class, bundle);
            }
            else if("SelectOrderClassification".equals(tag)){
              OrderClassificationBean bean = (OrderClassificationBean) res;
              if(bean!=null){
                  if(bean.getData()!=null){
                      gridViewClass.setAdapter(new AdapterPlaceAnOrder(_activity,bean.getData()));
                  }
              }
          }else if("SelectOrderRecommended".equals(tag)){
              OrderRecommendedBean bean = (OrderRecommendedBean)res;
              if(bean!=null){
                  if(bean.getData()!=null){
                      gridViewRecommend.setAdapter(new AdapterPlaceOrderRecommend(_activity,bean.getData()));
                  }
              }
          }else if("selectByOrderAdvertisement".equals(tag)){
              OrderAdvertisementBean bean = (OrderAdvertisementBean)res;
              if(bean!=null){
                  if(bean.getData()!=null){
                      ac.setImage(imageView,Constant.BASE+bean.getData().getImagePath());
                  }
              }
          }
        }
    }

    private void initBannerView(BannerBean bean)
    {
        bannerBeanData = bean.getData();
        List<String> images = new ArrayList<>(bannerBeanData.size());
        for (BannerBean.Data tmp : bannerBeanData)
        {
            images.add(Constant.BASEIMG + tmp.getBannerImg());
        }
        LogUtils.e(images);
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);//设置圆形指示器
        banner.setIndicatorGravity(Banner.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);//设置轮播间隔时间
        banner.setImages(images.toArray());
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener()
        {
            @Override
            public void OnBannerClick(View view, int position)
            {
                BannerBean.Data data = bannerBeanData.get(position - 1);
                AppUtil.getCarApiClient(ac).getBannerDetail(data.getId(),ActivityPlaceAnOrder.this);
            }
        });
    }
}
