package com.geelaro.viewtrianex.viewactvities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import com.geelaro.viewtrianex.R;

/**
 * Created by Administrator on 2016/11/18.
 */

public class ViewFlipperActivity extends AppCompatActivity {

    private ViewFlipper vflp;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flipper);

        Intent vflpIntent=getIntent();

        vflp=(ViewFlipper)findViewById(R.id.vflp_help);
        vflp.startFlipping();

//        float x=getResources().getDisplayMetrics().xdpi;
//        float y=getResources().getDisplayMetrics().ydpi;
//
//        Log.d("Xdpi", "is :"+x);
//        Log.d("Ydpi", "is :"+y);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
