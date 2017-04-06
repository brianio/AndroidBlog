package com.geelaro.broadcastrev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Lee on 2017/4/6.
 * 不同优先级的广播接收者
 */

public class PriorityBroadcastRev {

    //高优先级广播接收者
    public static class HighPriority extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("onReceive: ", "High");
//            //拦截广播
//            abortBroadcast();
            //传递广播到下一个广播接收器
            int code = 3;
            String data = "Hello";
            Bundle bundle = null;
            setResult(code, data, bundle);
        }
    }

    //中优先级
    public static class MidPriority extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("onReceive: ", "Mid");
            //获取上一个广播传递的内容
            int code = getResultCode();
            String data = getResultData();
            Log.d("onReceive: ", "获取到上一个广播的内容是：" + "code=" + code + ", data=" + data);

        }
    }

    //低优先级
    public static class LowPriority extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("onReceive: ", "Low");
        }
    }
}
