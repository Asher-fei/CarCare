package com.lida.carcare.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.adapter.AdapterGoodParams;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.GoodDetailBean;
import com.lida.carcare.widget.DialogIfDelete;
import com.lida.carcare.widget.InnerListView;
import com.lida.carcare.widget.popupwindow.OnItemClickListener;
import com.lida.carcare.widget.popupwindow.PopupCommen;
import com.midian.base.app.Constant;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.Func;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品详情
 * Created by WeiQingFeng on 2017/4/28.
 */

public class ActivityGoodDetail extends BaseActivity implements OnItemClickListener, DialogIfDelete.onOkButtonClick {

    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    InnerListView listView;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ivGood)
    ImageView ivGood;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvClass)
    TextView tvClass;
    @BindView(R.id.tvBrand)
    TextView tvBrand;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvAlwayHave)
    TextView tvAlwayHave;
    @BindView(R.id.tvDes)
    TextView tvDes;

    private PopupCommen popupWindow;
   // private String[] itemsArr = {"编辑", "删除"};
    private String[] itemsArr = {"编辑"};
    private List<String> items = Arrays.asList(itemsArr);

    private DialogIfDelete dialogIfDelete;
    private String id;
    private List<String> names = new ArrayList<>();
    private List<String> params = new ArrayList<>();
    private AdapterGoodParams adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetail);
        ButterKnife.bind(this);
        id = mBundle.getString("userId");
        topbar.setTitle("商品详情");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_plus_small, listener);
        AppUtil.getCarApiClient(ac).findProduct(id, this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if("findProduct".equals(tag)){
                GoodDetailBean bean = (GoodDetailBean) res;
                if(bean!=null&&bean.getData()!=null) {
                    ac.setImage(ivGood, Constant.BASE + bean.getData().getProductImg());
                    tvName.setText(bean.getData().getName());
                    tvClass.setText("分类：" + bean.getData().getTypeName());
                    tvBrand.setText("品牌：" + bean.getData().getBrand());
                    tvPrice.setText("￥" + bean.getData().getPricePlatform());
                    tvDes.setText(bean.getData().getRemark());
                    if ("0".equals(bean.getData().getType())) {
                        tvAlwayHave.setText("是");
                    } else {
                        tvAlwayHave.setText("否");
                    }
                    names = Arrays.asList(bean.getData().getSizeName().split(","));
                    params = Arrays.asList(bean.getData().getSizeParem().split(","));
                    adapter = new AdapterGoodParams(_activity, names, params);
                    listView.setAdapter(adapter);
                }else {
                    tvName.setText("");
                    tvClass.setText("分类：");
                    tvBrand.setText("品牌：");
                    tvPrice.setText("￥");
                    tvDes.setText("");
                }
            }
            if("deleteGoods".equals(tag)){
                dialogIfDelete.dismiss();
                UIHelper.t(_activity, "删除成功！");
                setResult(RESULT_OK);
                finish();
            }
        }else {
            if("deleteGoods".equals(tag)){
                dialogIfDelete.dismiss();
                UIHelper.t(_activity, res.getMsg());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode){
            AppUtil.getCarApiClient(ac).findProduct(id, this);
        }
    }

    /**
     * topbar右按钮
     */
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_ib:
                    showMenu(topbar.getRight_ib());
                    break;
            }
        }
    };

    private void showMenu(View parent) {
        View contentView = LayoutInflater.from(_activity).inflate(R.layout.spinner_member, null);
        popupWindow = new PopupCommen(_activity, contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, items);
        popupWindow.setFocusable(true);
        popupWindow.setOnItemClickListener(this);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(parent, -Func.Dp2Px(_activity, 110), Func.Dp2Px(_activity, 5));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setVisibility(View.GONE);
            }
        });
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void doNext(int positon, String text) {
        switch (positon) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("userId",id);
                UIHelper.jumpForResult(_activity,ActivityEditGoods.class,bundle,1001);
                break;
            case 1:
                dialogIfDelete = new DialogIfDelete(_activity);
                dialogIfDelete.setOnOkButtonClick(this);
                dialogIfDelete.show();
                break;
        }
        popupWindow.dismiss();
    }

    @Override
    public void delete() {
        AppUtil.getCarApiClient(ac).deleteGoods(id,this);
    }
}
