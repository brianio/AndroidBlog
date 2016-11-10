package com.geelaro.viewtrianex.viewact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.geelaro.viewtrianex.R;
import com.geelaro.viewtrianex.adapter.MyAdapter;
import com.geelaro.viewtrianex.data.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class ListViewActivity extends AppCompatActivity {
    private List<Data> mData=null;
    private BaseAdapter adapter=null;
    private ListView listContent;
    private Context mContext;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent listIntent=getIntent();
        setContentView(R.layout.activity_list);

        mContext=ListViewActivity.this;
        listContent=(ListView) findViewById(R.id.list_content);
        mData=new ArrayList<>();
        //??ListView
        mData.add(new Data("狗说：","你是狗嘛？",R.mipmap.ic_launcher));
        mData.add(new Data("鸡说:","你是母鸡嘛？",R.mipmap.ic_launcher));
        mData.add(new Data("鱼说:","你是大鱼嘛？",R.mipmap.ic_launcher));
        mData.add(new Data("猪说:","你是野猪嘛？",R.mipmap.ic_launcher));
        mData.add(new Data("少侠说:","我要吃光楼上的。",R.mipmap.img_long));

        adapter=new MyAdapter<Data>((ArrayList<Data>) mData,R.layout.list_item) {
            @Override
            public void bindView(ViewHolder holder, Data obj) {
                holder.setImageResource(R.id.imgtou,obj.getImgId());
                holder.setText(R.id.name,obj.getName());
                holder.setText(R.id.speaks,obj.getSpeach());
            }
        };

        listContent.setAdapter(adapter);
    }
}
