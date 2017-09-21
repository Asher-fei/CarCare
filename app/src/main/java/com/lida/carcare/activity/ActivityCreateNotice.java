package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.widget.BaseApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建提醒
 * Created by WeiQingFeng on 2017/4/14.
 */

public class ActivityCreateNotice extends BaseActivity implements OnSureLisener {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.llRelationCar)
    LinearLayout llRelationCar;
    @BindView(R.id.tvChooseDate)
    TextView tvChooseDate;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.tvCarNo)
    TextView tvCarNo;

    private String carId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnotice);
        ButterKnife.bind(this);
        topbar.setTitle("创建提醒");
        topbar.setRightText("创建", listener);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String maturityDate = tvChooseDate.getText().toString();
            String reminderDetails = etContent.getText().toString();
            if ("".equals(carId) || "".equals(maturityDate) || "".equals(reminderDetails)) {
                UIHelper.t(_activity, "请填写完整");
                return;
            }
            AppUtil.getCarApiClient(ac).saveIntelligentReminder(carId, "", maturityDate, reminderDetails, new BaseApiCallback() {
                @Override
                public void onApiStart(String tag) {
                    super.onApiStart(tag);
                    showLoadingDlg();
                }

                @Override
                public void onApiSuccess(NetResult res, String tag) {
                    super.onApiSuccess(res, tag);
                    hideLoadingDlg();
                    if (res.isOK()) {
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        ac.handleErrorCode(_activity, res.getMsg());
                    }
                }

                @Override
                public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                    super.onApiFailure(t, errorNo, strMsg, tag);
                    hideLoadingDlg();
                }
            });
        }
    };

    @OnClick({R.id.llRelationCar, R.id.tvChooseDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llRelationCar:
                Bundle bundle = new Bundle();
                bundle.putString("flag", "ActivityCreateNotice");
                UIHelper.jumpForResult(_activity, ActivityCarManege.class, bundle, 1002);
                break;
            case R.id.tvChooseDate:
//                UIHelper.jumpForResult(_activity, ActivityDateExpire.class, 1001);
                initTimeDialog();
                break;
        }
    }

    private void initTimeDialog()
    {
        DatePickDialog dialog = new DatePickDialog(_activity);
        dialog.setYearLimt(50);
        dialog.setTitle("选择时间");
        dialog.setType(DateType.TYPE_YMD);
        dialog.setMessageFormat("yyyy-MM-dd");
        dialog.setOnSureLisener(this);
        dialog.show();
    }

    @Override
    public void onSure(Date date)
    {
        Date d = new Date();
        if(date.getTime()<d.getTime()){
            UIHelper.t(_activity, "提醒时间不能早于当前时间！");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        tvChooseDate.setText(format);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            if (requestCode == 1001) {
//                tvChooseDate.setText(data.getStringExtra("data"));
//            }
            if (requestCode == 1002) {
                tvCarNo.setText(data.getStringExtra("carNo"));
                carId = data.getStringExtra("id");
            }
        }
    }
}
