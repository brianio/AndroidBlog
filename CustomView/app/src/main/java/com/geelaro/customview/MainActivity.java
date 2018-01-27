package com.geelaro.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RadarView view = new RadarView(this);
        setContentView(view);

//        view.setTitles(new String[]{"身高","体重","性格","学历","职位","年薪"});

//        List<PieData> list = new ArrayList<>();
//        PieData pd1 = new PieData("36Kr",30);
//        PieData pd2 = new PieData("Zhihu",  60);
//        PieData pd3 = new PieData("Guoke",40);
//        PieData pd4 = new PieData("Douban",80);
//        PieData pd5 = new PieData("QQ",120);
//
//        list.add(pd1);
//        list.add(pd2);
//        list.add(pd3);
//        list.add(pd4);
//        list.add(pd5);
//
//        view.setData(list);

    }

}
