package com.lida.carcare.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.lida.carcare.R;
import com.lida.carcare.fragment.FragmentCarInfoDetail;
import com.lida.carcare.fragment.FragmentOffer;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消费详情-车辆信息
 * Created by WeiQingFeng on 2017/4/17.
 */

public class ActivityCarDetailInfo extends BaseFragmentActivity
{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String conclusion = "";//传过去结论

    private static final String[] CHANNELS = new String[]{"车辆信息", "报价"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentCarInfoDetail fragmentCarInfoDetail;
    private String id;//车辆Id;
    private String carNo;//车牌号;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessstatistics);
        ButterKnife.bind(this);
        id = mBundle.getString("id");
        carNo = mBundle.getString("carNo");
        topbar.setTitle(carNo);
        //已完成没有检车
        if(!mBundle.getString("WorkStatus").equals("2")) {
            topbar.setRightText("检车", listener);
        }
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        bundle.putString("WorkStatus",mBundle.getString("WorkStatus"));
        fragmentCarInfoDetail = new FragmentCarInfoDetail();
        FragmentOffer fragmentOffer = new FragmentOffer(id);
        fragmentCarInfoDetail.setArguments(bundle);
        fragmentOffer.setArguments(bundle);
        fragments.add(fragmentCarInfoDetail);
        fragments.add(fragmentOffer);
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position)
            {
                return fragments.get(position);
            }

            @Override
            public int getCount()
            {
                return fragments.size();
            }
        });
        initMagicIndicator();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("id",id);
            if(fragmentCarInfoDetail!=null){
                conclusion = fragmentCarInfoDetail.getConclusion();
            }
            bundle.putString("conclusion",conclusion);
            UIHelper.jumpForResult(_activity, ActivityCheckCar.class,bundle, 1001);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            fragmentCarInfoDetail.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter()
        {
            @Override
            public int getCount()
            {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index)
            {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#636363"));
                clipPagerTitleView.setClipColor(Color.parseColor("#FF5C33"));
                clipPagerTitleView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        viewPager.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(clipPagerTitleView);
                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context)
            {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineHeight(Func.Dp2Px(_activity, 4));
                indicator.setColors(Color.parseColor("#FF5C33"));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index)
            {
                return 1.0f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
}
