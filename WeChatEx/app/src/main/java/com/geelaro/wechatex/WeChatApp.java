package com.geelaro.wechatex;

import android.app.Application;
import android.content.Context;

/**
 * Created by geelaro on 2017/12/5.
 */

public class WeChatApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }


}
