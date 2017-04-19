package com.geelaro.notifywork;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        Button btnNotify=(Button)findViewById(R.id.send_notice);
        btnNotify.setOnClickListener(this);

    }

    private void sendNotification(){
        //获取一个NotificationManager实例
        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化通知栏构造器NotificationCompat.Builder：
        NotificationCompat.Builder mBuilder= new NotificationCompat.Builder(this);
        //PendingIntent
        Intent intent=new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        //
        mBuilder.setContentTitle("YY LIVE") //通知标题
                .setContentText("小鸡仔开播了") //通知描述
                .setContentIntent(pendingIntent)
                .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher);

//        Notification notification=mBuilder.build();
//        notification.flags=Notification.FLAG_SHOW_LIGHTS;


        //发送通知请求
        manager.notify(1,mBuilder.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_notice:
                sendNotification();
                break;
            default:
                break;
        }

    }
}
