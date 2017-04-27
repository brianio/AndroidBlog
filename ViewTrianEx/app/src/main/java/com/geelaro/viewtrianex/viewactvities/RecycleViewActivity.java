package com.geelaro.viewtrianex.viewactvities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.geelaro.viewtrianex.R;
import com.geelaro.viewtrianex.adapter.MyRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    private MyRecycleViewAdapter mAdapter;
    private List<String> mDataList;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        //
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mDataList.add("Ietm: " + i);
        }
        //
        mAdapter = new MyRecycleViewAdapter(mDataList);
        //
        manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
        //
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
