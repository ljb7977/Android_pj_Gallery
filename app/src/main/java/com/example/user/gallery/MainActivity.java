package com.example.user.gallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.provider.MediaStore.MediaColumns;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> ImgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        } else {
            ImgList = getPathOfAllImages();
        }

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, ImgList));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                //Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                callImageViewer(position);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults)
    {
        switch(requestCode){
            case 0:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    ImgList = getPathOfAllImages();
                }
        }
    }

    private ArrayList<String> getPathOfAllImages()
    {
        ArrayList<String> result = new ArrayList<>();
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaColumns.DATA, MediaColumns.DISPLAY_NAME };

        Cursor cursor = getContentResolver().query(uri, projection, null, null, MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(absolutePathOfImage)) {
                result.add(absolutePathOfImage);
            }
        }

        for (String string : result)
        {
            Log.i("PhotoSelectActivity.java | getPathOfAllImages", "|" + string + "|");
        }
        return result;
    }

    public void callImageViewer(int index){
        Intent i = new Intent(this, ImageViewer.class);
        String path = ImgList.get(index);//getImageInfo();
        i.putExtra("filename", path);
        startActivity(i);
    }
}