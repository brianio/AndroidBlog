package com.geelaro.broadcastrev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Lee on 2017/4/6.
 */

public class MyBroadRev extends BroadcastReceiver {
    private static final String TAG = MyBroadRev.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Receive a Broadcast.");
    }
}
