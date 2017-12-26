package com.example.user.gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.util.Log;

public class ImageViewer extends Activity {

    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);
        mContext = this;
        Intent i = getIntent();
        String path = i.getExtras().getString("filename");
        Log.i("PATH", path);
        ImageView iv = findViewById(R.id.imageView);

        iv.setImageURI(Uri.parse(path));
    }
}
