package com.lida.carcare.tpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityEditStorage;
import com.lida.carcare.app.AppUtil;
import com.lida.carcare.bean.StorageListBean;
import com.lida.carcare.widget.BaseApiCallback;
import com.lida.carcare.widget.DialogIfDelete;
import com.lida.carcare.widget.DialogStorageEdit;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.view.BaseTpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public class ActivityStorageTpl extends BaseTpl<StorageListBean.DataBean> {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDes)
    TextView tvDes;

    private DialogStorageEdit dialogStorageEdit;
    private DialogIfDelete dialogIfDelete;

    public ActivityStorageTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityStorageTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_activitystorage;
    }

    @Override
    public void setBean(final StorageListBean.DataBean bean, int position) {
        if(bean!=null){
            tvName.setText(bean.getRepertoryName());
            tvDes.setText(bean.getRepertoryRemark());
            root.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    dialogStorageEdit = new DialogStorageEdit(_activity,bean.getRepertoryName());
                    dialogStorageEdit.setOnEditTypeChoosed(new DialogStorageEdit.onEditTypeChoosed() {
                        @Override
                        public void onEditClick() {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("bean", bean);
                            UIHelper.jumpForResult(_activity, ActivityEditStorage.class, bundle, 1001);
                        }
                        @Override
                        public void onDelClick() {
                            dialogIfDelete = new DialogIfDelete(_activity);
                            dialogIfDelete.setOnOkButtonClick(new DialogIfDelete.onOkButtonClick() {
                                @Override
                                public void delete() {
                                    AppUtil.getCarApiClient(ac).deleteRepertory(bean.getId(), new BaseApiCallback(){
                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            super.onApiSuccess(res, tag);
                                            if(res.isOK()){
                                                dialogIfDelete.dismiss();
                                                UIHelper.t(_activity,"删除成功！");
                                                listViewHelper.refresh();
                                            }
                                        }
                                    });
                                }
                            });
                            dialogIfDelete.show();
                        }
                    });
                    dialogStorageEdit.show();
                    return false;
                }
            });
        }
    }
}
