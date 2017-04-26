package com.geelaro.servicework.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.geelaro.servicework.MainActivity;
import com.geelaro.servicework.R;

public class CoreService extends Service {

    private static final String TAG = CoreService.class.getSimpleName();
    private DownloadBinder mBinder = new DownloadBinder();

    public class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload: ");
        }

        public int getProgress() {
            Log.d(TAG, "getProgress: ");
            return 10;
        }
    }

    public CoreService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        notifyInit();
        Log.d(TAG, "onCreate: ");
    }

    private void notifyInit() {
        Notification.Builder mBuilder = new Notification.Builder(this);
        Intent noticeIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, noticeIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        //
        mBuilder.setContentTitle("Sunshine天气")
                .setContentText("广州，今日晴，22F")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker("geeNotification");
        startForeground(1, mBuilder.build());

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: ");
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }
}
