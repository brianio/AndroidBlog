package com.geelaro.layoutex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  {

    private Button btnStart, btnStop, btnReset, btnFormat;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_layout);
//        init();
        String[] str={"One","Two","Three","Four","Five" };
        //Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,str);

        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);


//
//        FrameLayout frame = (FrameLayout) findViewById(R.id.myframelayout);
//        final MeiziView mezi = new MeiziView(MainActivity.this);
//        //为我们的萌妹子添加触摸事件监听器
//        mezi.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                //设置妹子显示的位置
//                mezi.bitmapX = event.getX() - 150;
//                mezi.bitmapY = event.getY() - 150;
//                //调用重绘方法
//                mezi.invalidate();
//                return true;
//            }
//        });
//        frame.addView(mezi);
    }

//    public void init() {
//        chronometer = (Chronometer) findViewById(R.id.chronometers);
//        btnStart = (Button) findViewById(R.id.btn_start);
//        btnStop = (Button) findViewById(R.id.btn_stop);
//        btnReset = (Button) findViewById(R.id.btn_reset);
//        btnFormat = (Button) findViewById(R.id.btn_format);
//
//        chronometer.setOnChronometerTickListener(this);
//        btnStart.setOnClickListener(this);
//        btnStop.setOnClickListener(this);
//        btnReset.setOnClickListener(this);
//        btnFormat.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_start:
//                chronometer.start();
//                break;
//            case R.id.btn_stop:
//                chronometer.stop();
//                break;
//            case R.id.btn_reset:
//                chronometer.setBase(SystemClock.elapsedRealtime());
//                break;
//            case R.id.btn_format:
//                chronometer.setFormat("Time:%s");
//        }
//
//    }
//
//    @Override
//    public void onChronometerTick(Chronometer chronometer) {
//        String time = chronometer.getText().toString();
//        if (time.equals("00:00")) {
//            Toast.makeText(MainActivity.this, " Time is up", Toast.LENGTH_LONG).show();
//        }
//
//    }
}

