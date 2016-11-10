package com.geelaro.viewtrianex.viewact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import com.geelaro.viewtrianex.R;
import com.geelaro.viewtrianex.adapter.MyAdapter;
import com.geelaro.viewtrianex.data.Icon;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/9.
 */

public class SpinnerActivity extends AppCompatActivity {

    private ArrayList<Icon> mData = null;
    private Context mContext;
    private BaseAdapter aAdapter = null;
    private Spinner spinnerOne;
    private Spinner spinnerTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent spinnerIntent=getIntent();
        setContentView(R.layout.activity_spinner);

        mContext=SpinnerActivity.this;
        mData=new ArrayList<>();
        bindViews();
    }
    private void bindViews(){
        spinnerOne=(Spinner)findViewById(R.id.spinner_one);
        spinnerTwo=(Spinner)findViewById(R.id.spinner_two);

        mData=new ArrayList<>();
        mData.add(new Icon(R.mipmap.ic_launcher,"德布雷"));
        mData.add(new Icon(R.mipmap.ic_launcher,"圣手拿伯尼"));
        mData.add(new Icon(R.mipmap.ic_launcher,"汉拔尼"));
        mData.add(new Icon(R.mipmap.img_long,"龙少侠"));

        aAdapter= new MyAdapter<Icon>(mData,R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon,obj.getiId());
                holder.setText(R.id.text_icon,obj.getiName());
            }
        };

        spinnerTwo.setAdapter(aAdapter);
    }
}
