package com.geelaro.okhttpwork;

import android.app.Application;

/**
 * Created by geelaro on 2017/4/18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .hostnameVerifier(new HostnameVerifier()
//                {
//                    @Override
//                    public boolean verify(String hostname, SSLSession session)
//                    {
//                        return true;
//                    }
//                })
//                .build();
//        OkHttpUtils.initClient();
    }
}
