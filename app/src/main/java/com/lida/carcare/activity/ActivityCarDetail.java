package com.lida.carcare.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.lida.carcare.R;
import com.lida.carcare.fragment.FragmentCarInfo;
import com.lida.carcare.fragment.FragmentCheckCarHistory;
import com.lida.carcare.fragment.FragmentRecordOfConsum;
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
 * 车辆详情
 * Created by WeiQingFeng on 2017/4/13.
 */

public class ActivityCarDetail extends BaseFragmentActivity
{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String carId;
    private String carNo;
    private String documentId;

    private static final String[] CHANNELS = new String[]{"车辆信息", "消费记录", "检车记录"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessstatistics);
        ButterKnife.bind(this);
        carId = mBundle.getString("carId");
        carNo = mBundle.getString("carNo");
        documentId = mBundle.getString("documentId");
        topbar.setTitle("车辆历史信息");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        FragmentCarInfo info = new FragmentCarInfo();
        FragmentRecordOfConsum fragmentRecordOfConsum = new FragmentRecordOfConsum();
        FragmentCheckCarHistory fragmentCheckCarHistory = new FragmentCheckCarHistory();
        Bundle bundle = new Bundle();
        bundle.putString("carId", carId);
        Log.i("TAG","cardId = "+carId);
        bundle.putString("carNo", carNo);
        bundle.putString("documentId", documentId);
        info.setArguments(bundle);
        fragmentRecordOfConsum.setArguments(bundle);
        fragmentCheckCarHistory.setArguments(bundle);
        fragments.add(info);
        fragments.add(fragmentRecordOfConsum);
        fragments.add(fragmentCheckCarHistory);
        viewPager.setAdapter(new FragmentPagerAdapter(fm)
        {
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

    private void initMagicIndicator()
    {
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
