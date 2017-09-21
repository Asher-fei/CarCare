package com.lida.carcare.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.lida.carcare.R;
import com.lida.carcare.widget.DialogChoosePicType;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.PhotoPicker.PhotoPicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 选择照片
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterPic extends BaseAdapter {

    private TakePhotoActivity context;
    private DialogChoosePicType dialog;
    private List<String> pics=new ArrayList<>();
    private AppContext ac;

    public AdapterPic(TakePhotoActivity context,List<String> pics) {
        this.context = context;
        this.pics = pics;
        ac = (AppContext) context.getApplication();
    }



    DialogChoosePicType.onTypeSelectedListener listener=new DialogChoosePicType.onTypeSelectedListener() {
        @Override
        public void onOpenCamera() {
            TakePhoto takePhoto = context.getTakePhoto();
            File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists())file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            CompressConfig config;
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config=CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config,false);
            takePhoto.onPickFromCapture(imageUri);
        }

        @Override
        public void onOpenPic() {
            TakePhoto takePhoto = context.getTakePhoto();
            CompressConfig config;
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config=CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config,false);
            takePhoto.onPickFromGallery();
        }
    };

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pic, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(pics.size()>0){
            viewHolder.ivAdd.setVisibility(View.GONE);
            viewHolder.ivDel.setVisibility(View.VISIBLE);
            viewHolder.ivSource.setVisibility(View.VISIBLE);
            if(pics.get(position).contains("http")){
                ac.setImage(viewHolder.ivSource,pics.get(position));
            }else{
                viewHolder.ivSource.setImageBitmap(BitmapFactory.decodeFile(pics.get(position)));
            }
        }else{
            viewHolder.ivAdd.setVisibility(View.VISIBLE);
            viewHolder.ivDel.setVisibility(View.GONE);
            viewHolder.ivSource.setVisibility(View.GONE);
        }
        viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DialogChoosePicType(context);
                dialog.setTypeSelectedListener(listener);
                dialog.show();
            }
        });
        viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pics.remove(position);
                notifyDataSetChanged();
            }
        });
//        if(position==pics.size()){
//            viewHolder.ivAdd.setVisibility(View.VISIBLE);
//            viewHolder.ivSource.setVisibility(View.GONE);
//        }else{
//            viewHolder.ivAdd.setVisibility(View.GONE);
//            viewHolder.ivSource.setVisibility(View.VISIBLE);
//            viewHolder.ivSource.setImageBitmap(BitmapFactory.decodeFile(pics.get(position)));
//        }
//        viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(position>0){
//                    UIHelper.t(context,"最多只能添加一张图片！");
//                    return;
//                }
//                dialog = new DialogChoosePicType(context);
//                dialog.setTypeSelectedListener(listener);
//                dialog.show();
//            }
//        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivSource)
        ImageView ivSource;
        @BindView(R.id.ivAdd)
        ImageView ivAdd;
        @BindView(R.id.ivDel)
        ImageView ivDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
