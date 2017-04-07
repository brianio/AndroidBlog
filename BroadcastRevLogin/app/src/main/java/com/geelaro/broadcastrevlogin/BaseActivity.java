package com.geelaro.broadcastrevlogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.geelaro.broadcastrevlogin.manage.ActivityCollector;

/**
 * Created by LEE on 2017/4/7.
 * 创建一个基类
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        Log.d(this.getLocalClassName(), "onDestroy: ");
    }
}
