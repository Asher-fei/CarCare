package com.lida.carcare.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarInShopBean;
import com.lida.carcare.fragment.FragmentCarBeauty;
import com.lida.carcare.fragment.FragmentCarMainTain;
import com.lida.carcare.fragment.FragmentCarRefit;
import com.lida.carcare.fragment.FragmentCarRepair;
import com.lida.carcare.widget.BaseOnPageChangeListener;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 在店车辆
 * Created by WeiQingFeng on 2017/4/12.
 */

public class ActivityCars extends BaseFragmentActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.llCarBeauty)
    LinearLayout llCarBeauty;
    @BindView(R.id.llMainTain)
    LinearLayout llMainTain;
    @BindView(R.id.llRepair)
    LinearLayout llRepair;
    @BindView(R.id.llRefit)
    LinearLayout llRefit;
    @BindView(R.id.tvCarBeautyNum)
    TextView tvCarBeautyNum;
    @BindView(R.id.tvMainTainNum)
    TextView tvMainTainNum;
    @BindView(R.id.tvCarRepairNum)
    TextView tvCarRepairNum;
    @BindView(R.id.tvCarRefitNum)
    TextView tvCarRefitNum;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);
        ButterKnife.bind(this);
        topbar.setTitle("在店车辆");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
        fragments.add(new FragmentCarBeauty());
        fragments.add(new FragmentCarMainTain());
        fragments.add(new FragmentCarRepair());
        fragments.add(new FragmentCarRefit());
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
        viewPager.setOnPageChangeListener(new BaseOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                if (position == 0)
                {
                    setButton(R.id.llCarBeauty);
                } else if (position == 1)
                {
                    setButton(R.id.llMainTain);
                } else if (position == 2)
                {
                    setButton(R.id.llRepair);
                } else if (position == 3)
                {
                    setButton(R.id.llRefit);
                }
            }
        });
        refresh();
    }

    public void refresh(){
        //请求数量
        AppUtil.getCarApiClient(ac).getCarProject1("美容", ac.shopId, this);
        AppUtil.getCarApiClient(ac).getCarProject2("保养", ac.shopId, this);
        AppUtil.getCarApiClient(ac).getCarProject3("维修", ac.shopId, this);
        AppUtil.getCarApiClient(ac).getCarProject4("改装", ac.shopId, this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag)
    {
        super.onApiSuccess(res, tag);
        if (res.isOK())
        {
            CarInShopBean bean = (CarInShopBean) res;
            String str;
            int size = bean.getData().size();
            if (size > 99)
            {
                str = "99+";
            } else
            {
                str = String.valueOf(size);
            }
            if ("getCarProject1".equals(tag))
            {
                tvCarBeautyNum.setText(str);
            } else if ("getCarProject2".equals(tag))
            {
                tvMainTainNum.setText(str);
            } else if ("getCarProject3".equals(tag))
            {
                tvCarRepairNum.setText(str);
            } else if ("getCarProject4".equals(tag))
            {
                tvCarRefitNum.setText(str);
            }
        }
    }

    View.OnClickListener listener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            UIHelper.jump(_activity, ActivityAddCar.class);
        }
    };

    @OnClick({R.id.llCarBeauty, R.id.llMainTain, R.id.llRepair, R.id.llRefit})
    public void onViewClicked(View view)
    {
        setButton(view.getId());
        switch (view.getId())
        {
            case R.id.llCarBeauty:
                viewPager.setCurrentItem(0);
                break;
            case R.id.llMainTain:
                viewPager.setCurrentItem(1);
                break;
            case R.id.llRepair:
                viewPager.setCurrentItem(2);
                break;
            case R.id.llRefit:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    private void setButton(int id)
    {
        llCarBeauty.setBackgroundColor(Color.WHITE);
        llMainTain.setBackgroundColor(Color.WHITE);
        llRepair.setBackgroundColor(Color.WHITE);
        llRefit.setBackgroundColor(Color.WHITE);
        LinearLayout view = (LinearLayout) findViewById(id);
        view.setBackgroundColor(Color.parseColor("#FFEEEE"));
    }
}
