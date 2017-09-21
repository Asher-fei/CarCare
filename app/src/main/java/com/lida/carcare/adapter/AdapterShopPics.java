package com.lida.carcare.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.lida.carcare.R;
import com.lida.carcare.activity.ActivityCommitData;
import com.lida.carcare.widget.DialogChoosePicType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 上传营业执照
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterShopPics extends BaseAdapter {

    private TakePhotoActivity context;
    private DialogChoosePicType dialog;
    private List<String> pics=new ArrayList<>();
    public boolean isUsed = false;

    public AdapterShopPics(TakePhotoActivity context, List<String> pics) {
        this.context = context;
        this.pics = pics;
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
        return pics.size()+1;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shoppics, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(position==pics.size()){
            viewHolder.ivAdd.setVisibility(View.VISIBLE);
            viewHolder.ivSource.setVisibility(View.GONE);
        }else{
            viewHolder.ivAdd.setVisibility(View.GONE);
            viewHolder.ivSource.setVisibility(View.VISIBLE);
            viewHolder.ivSource.setImageBitmap(BitmapFactory.decodeFile(pics.get(position)));
        }
        viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCommitData.flag=2;
                dialog = new DialogChoosePicType(context);
                dialog.setTypeSelectedListener(listener);
                dialog.show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivSource)
        ImageView ivSource;
        @BindView(R.id.ivAdd)
        ImageView ivAdd;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
