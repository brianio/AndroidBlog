package com.geelaro.broadcastrevlogin.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

import com.geelaro.broadcastrevlogin.LoginActivity;
import com.geelaro.broadcastrevlogin.manage.ActivityCollector;

/**
 * Created by LEE on 2017/4/7.
 * 收到广播，弹出一个确认对话框，强制用户下线
 */

public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        //建一个对话确认框
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Warning");
        builder.setMessage("You are forced to be offline.Please try to login again.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCollector.finishAll();
                Intent restartIntent=new Intent(context, LoginActivity.class);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(restartIntent); //重启LoginActivity
            }
        });

        AlertDialog alertDialog=builder.create();
        //设置AlertDialog的类型，保证在广播接收器中可以正常弹出
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
