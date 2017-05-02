package com.geelaro.network.net;

/**
 * Created by LEE on 2017/5/1.
 */

public interface HttpCallbackListener {

     void onFinish(String response);
     void onError(Exception e);
}
