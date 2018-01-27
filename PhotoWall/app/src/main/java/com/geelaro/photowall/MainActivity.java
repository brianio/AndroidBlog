package com.geelaro.photowall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private GridView mPhotoWall;
    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhotoWall = findViewById(R.id.photo_wall);
        mAdapter = new PhotoAdapter(this,mPhotoWall,0,Images.imageThumbUrls);
        mPhotoWall.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cancelAllTask();
    }
}
