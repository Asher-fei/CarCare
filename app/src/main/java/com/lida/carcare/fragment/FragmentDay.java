package com.lida.carcare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityIncomeDetailAdd;
import com.lida.carcare.activity.ActivityIncomeDetails;
import com.lida.carcare.activity.ActivitySpendingDetail;
import com.lida.carcare.activity.ActivitySpendingDetailAdd;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.IncomeExpenditureDean;
import com.midian.base.base.BaseFragment;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by WeiQingFeng on 2017/4/7.
 */

public class FragmentDay extends BaseFragment {

    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.netIncome)
    TextView netIncome;
    @BindView(R.id.totalRevenue)
    TextView totalRevenue;
    @BindView(R.id.totalSpending)
    TextView totalSpending;

    Unbinder unbinder;


    Calendar mCalendar= Calendar.getInstance();
    Date dNow = new Date();   //当前时间

    int days = 0;
    SimpleDateFormat sdf=new SimpleDateFormat("MM,dd"); //设置时间格式
    SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");


    IncomeExpenditureDean bean = null;
    DecimalFormat format = new DecimalFormat("#0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day, null);
        unbinder = ButterKnife.bind(this, v);
        mCalendar.setTime(dNow);//把当前时间赋给日历
        date.setText(sdf.format(dNow));
        AppUtil.getCarApiClient(ac).selectCarIncomeSpendingDayTotalPrice(sd.format(dNow),this);
        return v;
    }

    @OnClick({R.id.tvIncomeDetail,R.id.tvSpendingDetail,R.id.SpendingDetailAdd,R.id.IncomeDetailAdd,R.id.btnBefore,R.id.btnAfter})
    public void onClick(View view){
        Bundle bundle = new Bundle();
        Date t = mCalendar.getTime();
        switch (view.getId()){
            case R.id.tvIncomeDetail:
                bundle.putString("dateTime",sd.format(t));
                bundle.putString("flag","Day");
                UIHelper.jump(_activity, ActivityIncomeDetails.class,bundle);
                break;
            case R.id.tvSpendingDetail:
                bundle.putString("dateTime",sd.format(t));
                bundle.putString("flag","Day");
                UIHelper.jump(_activity, ActivitySpendingDetail.class,bundle);
                break;
            case R.id.SpendingDetailAdd:
                UIHelper.jumpForResult(_activity, ActivitySpendingDetailAdd.class,1001);
                break;
            case R.id.IncomeDetailAdd:
                UIHelper.jumpForResult(_activity, ActivityIncomeDetailAdd.class,1001);
                break;
            case R.id.btnBefore:
                days = days-1;
                mCalendar.add(Calendar.DAY_OF_MONTH, -1);
                Date d  = mCalendar.getTime();
                String defaultStartDate = sdf.format(d);
                date.setText(defaultStartDate);
                AppUtil.getCarApiClient(ac).selectCarIncomeSpendingDayTotalPrice(sd.format(d),this);
                break;
            case R.id.btnAfter:

                if(days==0){
                    UIHelper.t(_activity,"时间超出");
                }else {

                    days = days+1;
                    mCalendar.add(Calendar.DAY_OF_MONTH, +1);
                    Date dt  = mCalendar.getTime();
                    String de = sdf.format(dt);
                    date.setText(de);
                    AppUtil.getCarApiClient(ac).selectCarIncomeSpendingDayTotalPrice(sd.format(dt),this);

                }
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        _activity.hideLoadingDlg();
        if(res.isOK()){
            bean = (IncomeExpenditureDean)res;
            if(bean!=null){
                totalSpending.setText("¥ "+format.format(bean.getData().getTotalSpending()));
                totalRevenue.setText("¥ "+format.format(bean.getData().getTotalRevenue()));
                netIncome.setText("¥ "+format.format(bean.getData().getNetIncome()));
            }
        }else {
            UIHelper.t(_activity,"数据获取失败");
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        _activity.hideLoadingDlg();

    }



    public void refresh(){
        Date d = new Date();
        mCalendar.setTime(d);//把当前时间赋给日历
        date.setText(sdf.format(d));
        AppUtil.getCarApiClient(ac).selectCarIncomeSpendingDayTotalPrice(sd.format(d),this);
    }
}
