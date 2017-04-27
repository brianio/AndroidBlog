package com.geelaro.servicework.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.geelaro.servicework.services.LongRunningService;

/**
 * Created by LEE on 2017/4/26.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startLongService=new Intent(context,LongRunningService.class);
        context.startService(startLongService);
    }
}
