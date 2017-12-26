package com.example.user.gallery;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> ImgList;

    public ImageAdapter(Context c, ArrayList<String> l){
        mContext = c;
        ImgList = l;
    }

    public int getCount(){
        return ImgList.size();
    }

    public Object getItem(int position){
        return null;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0,0,0);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageURI(Uri.parse(ImgList.get(position)));
        return imageView;
    }
}
