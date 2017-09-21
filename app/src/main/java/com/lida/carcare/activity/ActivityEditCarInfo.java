package com.lida.carcare.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterMemCard;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarTypeBean;
import com.lida.carcare.bean.GetCarnewDetailBean;
import com.lida.carcare.fragment.FragmentCartDetail;
import com.lida.carcare.fragment.FragmentDay;
import com.lida.carcare.fragment.FragmentRecordOfConsum;
import com.lida.carcare.fragment.FragmentRecordsConsumption;
import com.lida.carcare.widget.CarClassDialog;
import com.lida.carcare.widget.InnerGridView;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改车辆基本信息
 * Created by Administrator on 2017/6/22.
 */

public class ActivityEditCarInfo extends BaseFragmentActivity{
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;


    private String carId;//车辆id


    private DatePickDialog dialogDate;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    FragmentCartDetail fragmentCartDetail;


    private static final String[] CHANNELS = new String[]{"车辆信息", "消费记录"};
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private String carNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcarinfo);
        ButterKnife.bind(this);
        carId = mBundle.getString("id");
        carNo = mBundle.getString("carNo");
        topbar.setTitle("车辆详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentCartDetail!=null){
                    fragmentCartDetail.save();
                }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString("carId", carId);
        bundle.putString("carNo", carNo);


         fragmentCartDetail = new FragmentCartDetail();
        /*FragmentRecordOfConsum fragmentRecordOfConsum = new FragmentRecordOfConsum();
        fragmentRecordOfConsum.setArguments(bundle);*/
        FragmentRecordsConsumption fragmentRecordsConsumption = new FragmentRecordsConsumption();
        fragmentRecordsConsumption.setArguments(bundle);
        fragmentCartDetail.setArguments(bundle);
        fragments.add(fragmentCartDetail);
        fragments.add(fragmentRecordsConsumption);

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


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    private String getEditText(TextView tv) {
        String text = null;
        if (tv != null) {
            text = tv.getText().toString().trim();
        }
        return text;
    }



    private void initTimeDialog() {
        dialogDate = new DatePickDialog(_activity);
        dialogDate.setYearLimt(5);
        dialogDate.setTitle("选择时间");
        dialogDate.setType(DateType.TYPE_ALL);
        dialogDate.setMessageFormat("yyyy-MM-dd HH:mm");
        dialogDate.show();
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
