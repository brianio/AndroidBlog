package com.geelaro.okhttpwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //
    private static final String TAG = MainActivity.class.getSimpleName();
    private OkHttpClient mOkHttpClient;
    private String url = "http://52.204.184.31/";
    private String post_url = "http://52.204.184.31/pop/okhttp.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOkHttpClient();

        //
        Button btnGet = (Button) findViewById(R.id.btn_get);
        btnGet.setOnClickListener(this);

    }

    private void initOkHttpClient() {
        File cache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;

        OkHttpClient.Builder mBuild = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(cache.getAbsoluteFile(), cacheSize));
        mOkHttpClient = mBuild.build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                getAsynHttp(url);
                break;
            case R.id.btn_post:
                postAysnHttp(post_url);
                break;
            default:
                break;
        }
    }

    /**
     * @param url
     */
    private void getAsynHttp(String url) {
        Request quest = new Request.Builder()
                .url(url)
                .build();
        Call mCall = mOkHttpClient.newCall(quest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.cacheResponse() != null) {
                    String str = response.cacheResponse().toString();
                    Log.i(TAG, "cache: " + str);
                } else {
                    response.body().string();
                    String str = response.networkResponse().toString();
                    Log.i(TAG, "network: " + str);
                }
                String str = response.headers().toString();
                Log.i(TAG, "headers: " + str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "GET成功", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    /**
     *
     */
    private void postAysnHttp(String url) {
        FormBody formBody = new FormBody.Builder()
                .add("user", "admin")
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i(TAG, "POST: " + str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "POST成功", Toast.LENGTH_SHORT).show();
                    }

                });
            }
        });
    }
}
