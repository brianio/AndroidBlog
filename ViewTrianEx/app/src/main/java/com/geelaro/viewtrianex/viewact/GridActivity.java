package com.geelaro.viewtrianex.viewact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.geelaro.viewtrianex.R;
import com.geelaro.viewtrianex.adapter.MyAdapter;
import com.geelaro.viewtrianex.data.Icon;

import java.util.ArrayList;

/**
 * Created by geelaro on 2016/11/9.
 */

public class GridActivity extends AppCompatActivity {
    private final static String TAG="GridActivity";

    private ArrayList<Icon> mData = null;
    private Context mContext;
    private GridView grid_Icon;
    private BaseAdapter aAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent gridIntent=getIntent();
        Log.d(TAG, "getIntent ");

        setContentView(R.layout.activity_grid_icon);


        mContext = GridActivity.this;
        grid_Icon = (GridView) findViewById(R.id.grid_icon);
        mData = new ArrayList<>();

        mData.add(new Icon(R.mipmap.ic_launcher, "111"));
        mData.add(new Icon(R.mipmap.ic_launcher, "222"));
        mData.add(new Icon(R.mipmap.ic_launcher, "333"));
        mData.add(new Icon(R.mipmap.ic_launcher, "444"));
        mData.add(new Icon(R.mipmap.ic_launcher, "555"));
        mData.add(new Icon(R.mipmap.ic_launcher, "666"));
        mData.add(new Icon(R.mipmap.ic_launcher, "777"));
        mData.add(new Icon(R.mipmap.ic_launcher, "888"));
//
        aAdapter=new MyAdapter<Icon>(mData,R.layout.grid_item_icon) {

            @Override
            public void bindView(MyAdapter.ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon,obj.getiId());
                holder.setText(R.id.text_icon,obj.getiName());
            }
        };
        grid_Icon.setAdapter(aAdapter);
//
        grid_Icon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"You click "+ ++position +".", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
