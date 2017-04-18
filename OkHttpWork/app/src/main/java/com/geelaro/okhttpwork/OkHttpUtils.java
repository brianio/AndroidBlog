package com.geelaro.okhttpwork;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

/**
 * Created by geelaro on 2017/4/18.
 */

public class OkHttpUtils {
    private OkHttpClient mOkHttpClient;
    private volatile static OkHttpUtils mInstance;
    private Platform mPlatform;


    public OkHttpUtils(){
        mOkHttpClient=new OkHttpClient();
    }

    public static OkHttpUtils initClient() {
        if (mInstance == null)
        {
            synchronized (OkHttpUtils.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 同步get请求
     * return Response
     */

    private Response _getAsyn(String url) throws IOException{
        final Request request=new Request.Builder()
                .url(url)
                .build();
        Response response=mOkHttpClient.newCall(request).execute();
        return response;
    }
    /**
     * 同步get
     * 返回String
     */
    private String _getAsynString(String url) throws IOException{
        Response response=getAsyn(url);
        return response.body().string();
    }



    public static Response getAsyn(String url) throws IOException
    {
        return initClient()._getAsyn(url);
    }
    public static String getAsynString(String url) throws IOException{
        return initClient()._getAsynString(url);
    }
}
