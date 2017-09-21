package com.lida.carcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lida.carcare.R;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 到期时间
 * Created by WeiQingFeng on 2017/4/14.
 */

public class ActivityDateExpire extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dateexpire);
        ButterKnife.bind(this);
        topbar.setTitle("到期时间");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Intent intent = new Intent();
                switch (checkedId){
                    case R.id.rb1:
                        intent.putExtra("timeData",rb1.getText().toString());
                        intent.putExtra("time",1);
                        break;
                    case R.id.rb2:
                        intent.putExtra("timeData",rb2.getText().toString());
                        intent.putExtra("time",2);
                        break;
                    case R.id.rb3:
                        intent.putExtra("timeData",rb3.getText().toString());
                        intent.putExtra("time",3);
                        break;
                }
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
