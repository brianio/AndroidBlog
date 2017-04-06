package com.geelaro.broadcastrev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Lee on 2017/4/6.
 * 本地广播接收者
 */

public class LocalBroadcastRev extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Receive: ","LocalBroadcastReceiver");
    }
}
