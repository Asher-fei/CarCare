package com.lida.carcare.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.lida.carcare.R;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.CarManageListBean;
import com.lida.carcare.bean.ComparatorCar;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogCarManege;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.astickyheader.PinnedSectionListView;
import com.midian.base.widget.astickyheader.SimpleSectionedListAdapter;
import com.midian.base.widget.astickyheader.SimpleSectionedListAdapter.Section;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 车辆管理
 * Created by WeiQingFeng on 2017/4/27.
 */

public class ActivityCarManege extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    PinnedSectionListView listView;
    @BindView(R.id.etSearch)
    EditText etSearch;

    private Adapter adapter;
    private List<CarManageListBean.Data> data = null;
    private List<String> mHeaderNames = new ArrayList<>();
    private List<Integer> mHeaderPositions = new ArrayList<>();
    private ArrayList<Section> sections = new ArrayList<>();

    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmanage);
        ButterKnife.bind(this);
        flag = mBundle.getString("flag");
        initView();
        AppUtil.getCarApiClient(ac).getCarList(ac.shopId, "", callback);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    AppUtil.getCarApiClient(ac).getCarList(ac.shopId, etSearch.getText().toString(), callback);
                }
                return false;
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UIHelper.jump(_activity, ActivityAddCar.class);
        }
    };

    private void initView() {
        topbar.setTitle("车辆管理");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("添加新车", listener);
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            hideLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            if(res.isOK()){
                if ("getCarList".equals(tag)) {
                    CarManageListBean bean = (CarManageListBean) res;
                    initData(bean);
                }
            }else{
                ac.handleErrorCode(_activity, res.getMsg());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
        }
    };

    class Adapter extends BaseAdapter {

        DialogCarManege dialogCarManege;

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? null : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_car, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
                viewHolder.tvCarNum.setText(data.get(position).getCarNo());
                viewHolder.tvName.setText(data.get(position).getCustomerName());
                viewHolder.tvPhone.setText(data.get(position).getMobile());
                viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CarManageListBean.Data tmp = data.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("id", tmp.getId());
                        bundle.putString("carNo",tmp.getCarNo());
                        bundle.putString("carNo", tmp.getCarNo());
                        if ("ActivityCreateNotice".equals(flag)) {
                            setResult(RESULT_OK, bundle);
                            finish();
                        } else {
                            UIHelper.jump(_activity, ActivityEditCarInfo.class, bundle);
                        }
                    }
                });

            viewHolder.layout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    dialogCarManege = new DialogCarManege(_activity,data.get(position).getCarNo());
                    dialogCarManege.setOnSelectOperation(new DialogCarManege.onCarManegeOperation() {
                        @Override
                        public void onDelClick() {
                            dialogCarManege.dismiss();
                            AppUtil.getCarApiClient(ac).deleteCarById(data.get(position).getId(),new BaseApiCallback(){
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
                                    if(res.isOK()){
                                        UIHelper.t(_activity,"操作成功");
                                        AppUtil.getCarApiClient(ac).getCarList(ac.shopId, etSearch.getText().toString(), callback);
                                    }else {
                                        UIHelper.t(_activity,res.getMsg());
                                    }
                                }
                            });
                        }
                    });
                    dialogCarManege.show();
                    return false;
                }
            });

            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tvCarNum)
            TextView tvCarNum;
            @BindView(R.id.tvName)
            TextView tvName;
            @BindView(R.id.tvPhone)
            TextView tvPhone;
            @BindView(R.id.layout)
            View layout;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 初始化数据，排序
     */
    private void initData(CarManageListBean bean) {

        data = bean.getData();
        sections.clear();

        //清除之前的数据，搜索时下标越界
        mHeaderNames.clear();
        mHeaderPositions.clear();

        ComparatorCar comparatorCar = new ComparatorCar();
        Collections.sort(data, comparatorCar);
        for (int i = 0; i < data.size(); i++) {
            String firstFont = data.get(i).getCarNo().substring(0, 1);
            if (!mHeaderNames.contains(firstFont)) {
                mHeaderNames.add(firstFont);
                mHeaderPositions.add(i);
            }
        }

        for (int i = 0; i < mHeaderPositions.size(); i++) {
            sections.add(new Section(mHeaderPositions.get(i), mHeaderNames.get(i)));
        }
        adapter = new Adapter();
        SimpleSectionedListAdapter simpleSectionedGridAdapter = new SimpleSectionedListAdapter(this, adapter,
                R.layout.list_item_header, R.id.header);
        simpleSectionedGridAdapter.setSections(sections.toArray(new Section[0]));
        listView.setAdapter(simpleSectionedGridAdapter);
    }
}
