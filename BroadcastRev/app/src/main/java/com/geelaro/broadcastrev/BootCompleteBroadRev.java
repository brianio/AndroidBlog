package com.geelaro.broadcastrev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Lee on 2017/4/6.
 */

public class BootCompleteBroadRev extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context," Boot complete.",Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onReceive: Boot complete.");
    }
}
