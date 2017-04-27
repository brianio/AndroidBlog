package com.geelaro.servicework.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.geelaro.servicework.receiver.AlarmReceiver;

import java.util.Date;

/**
 * Created by LEE on 2017/4/26.
 */

public class LongRunningService extends Service {
    private static final String TAG=LongRunningService.class.getSimpleName();
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run at "+new Date().toString());
            }
        }).start();
        //定时任务
        AlarmManager manager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int anHour=60*60*1000;//一小时的毫秒数
        long triggerAtTime= SystemClock.elapsedRealtime()+anHour;
        Intent it=new Intent(this, AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(this,0,it,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
