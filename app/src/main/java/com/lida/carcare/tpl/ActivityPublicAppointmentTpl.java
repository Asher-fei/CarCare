package com.lida.carcare.tpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.bean.PublicAppointmentBean;
import com.lida.carcare.widget.DialogCall;
import com.lida.carcare.widget.SharepreferenceUtils;
import com.midian.base.bean.NetResult;
import com.midian.base.view.BaseTpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公众号预约
 * Created by xkr on 2017/8/7.
 */

public class ActivityPublicAppointmentTpl extends BaseTpl<PublicAppointmentBean.DataBean> {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.createDate)
    TextView createDate;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.carNo)
    TextView carNo;
    @BindView(R.id.arrivalTime)
    TextView arrivalTime;
    @BindView(R.id.llContent)
    LinearLayout llContent;
    @BindView(R.id.llItem)
    LinearLayout llItem;

    public ActivityPublicAppointmentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityPublicAppointmentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_public_appointment;
    }

    @Override
    public void setBean(PublicAppointmentBean.DataBean bean, int position) {
        llContent.setVisibility(GONE);
        if (bean != null) {
            List<String> pts = new ArrayList<>();
            String s = SharepreferenceUtils.getPrefString(_activity, "ActivityPublicAppointment", "");
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
                SharepreferenceUtils.setPrefString(_activity, "ActivityPublicAppointment", buffer.toString());
            }

            name.setText(bean.getName() == null ? "" : bean.getName());
            createDate.setText(bean.getOrderTime() == null ? "" : bean.getOrderTime());
            phone.setText(bean.getPhone() == null ? "" : bean.getPhone());
            arrivalTime.setText(bean.getArriveTime() == null ? "" : bean.getArriveTime());
            carNo.setText(bean.getCarNo() == null ? "" : bean.getCarNo());
            phone.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!phone.getText().toString().equals("")) {
                        new DialogCall(_activity, phone.getText().toString()).show();
                    }
                }
            });

            root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (llContent.getVisibility() == GONE) {
                        llContent.setVisibility(VISIBLE);
                    } else {
                        llContent.setVisibility(GONE);
                    }
                }
            });
        }
    }

}
