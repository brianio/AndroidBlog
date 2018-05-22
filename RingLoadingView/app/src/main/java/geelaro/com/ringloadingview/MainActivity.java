package geelaro.com.ringloadingview;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RingLoadingView ringLoadingView = findViewById(R.id.ring_view);

//        ringLoadingView.setFirstColor(Color.GREEN);//更改第一个半圆环颜色

    }

}
