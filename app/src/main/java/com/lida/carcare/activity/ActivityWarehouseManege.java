package com.lida.carcare.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterRepositories;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupCommen;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 库存管理
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityWarehouseManege extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.view)
    View view;

//    private PopupCommen popupWindow;
//    private String[] itemsArr = {"出入库", "退货", "领料出库", "库存盘点"};
//    private List<String> items = Arrays.asList(itemsArr);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        ButterKnife.bind(this);
        topbar.setTitle("库存管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
//        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
        gridView.setAdapter(new AdapterRepositories(_activity));
    }

//    private void showMenu(View parent, View contentView)
//    {
//        popupWindow = new PopupCommen(_activity, contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, items);
//        popupWindow.setFocusable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
//        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 110), Func.Dp2Px(_activity, 5));
//        popupWindow.setOnItemClickListener(this);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
//        {
//            @Override
//            public void onDismiss()
//            {
//                view.setVisibility(View.GONE);
//            }
//        });
//        view.setVisibility(View.VISIBLE);
//    }

//    View.OnClickListener listener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            View view = LayoutInflater.from(_activity).inflate(R.layout.spinner_member, null);
//            showMenu(v, view);
//        }
//    };
//
//    @Override
//    public void doNext(int positon, String text)
//    {
//        switch (positon)
//        {
//            case 0:
//                UIHelper.jump(_activity, ActivityPaymentsRecord.class);
//                break;
//            case 1:
//                UIHelper.jump(_activity, ActivityReturnGoods.class);
//                break;
//            case 2:
//                UIHelper.jump(_activity, ActivityReadyToReceiveGoodsList.class);
//                break;
//            case 3:
//                UIHelper.jump(_activity, ActivityInventory.class);
//                break;
//        }
//        popupWindow.dismiss();
//    }
}
