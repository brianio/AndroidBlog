package com.geelaro.network.net;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LEE on 2017/5/1.
 */

public class HttpUtil {



    public static void sendRequestWithHttpURLConn(final String address, final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    URL url = new URL(address);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    //Settings
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);//ms
                    urlConnection.setReadTimeout(8000);
                    //输入流
                    InputStream in = urlConnection.getInputStream();
                    //读取输入流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if (listener!=null){
                        //回调onFinish()
                        listener.onFinish(response.toString());
                    }

                } catch (Exception e) {
                    if (listener==null){
                        //回调onError()
                        listener.onError(e);
                    }
                } finally {
                    if (urlConnection != null) {
                        //断开连接
                        urlConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
