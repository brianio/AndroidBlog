package com.geelaro.broadcastrev;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private NetworkReceiver networkReceiver;
    //定义本地广播
    private LocalBroadcastRev localReceiver;
    private IntentFilter intentFilter;
    private Button sendBroadRev;
    private Button sendOrderBroadRev;
    private Button sendLocalBroadRev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态注册广播接收
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceiver = new NetworkReceiver();
        registerReceiver(networkReceiver, intentFilter);
        //注册本地广播接收
        localReceiver=new LocalBroadcastRev();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(localReceiver,new IntentFilter("com.geelaro.localbroadcastrev"));

        //发送无序广播
        sendBroadRev=(Button)findViewById(R.id.send_broadcast);
        sendBroadRev.setOnClickListener(this);
        //发送有序广播
        sendOrderBroadRev=(Button)findViewById(R.id.send_order_broadcast);
        sendOrderBroadRev.setOnClickListener(this);
        //发送本地广播
        sendLocalBroadRev=(Button)findViewById(R.id.send_local_broadcast);
        sendLocalBroadRev.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(localReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //发送标准/无序广播
            case R.id.send_broadcast:
                Intent intent=new Intent("com.geelaro.broadcastrev");
                sendBroadcast(intent);
                break;
            //发送有序广播
            case R.id.send_order_broadcast:
                Intent orderIntent=new Intent("com.geelaro.prioritybroadcastrev");
                sendOrderedBroadcast(orderIntent,null,
                        new PriorityBroadcastRev.LowPriority(),
                        new Handler(),0,null,null); //这样指定LowPriority()类是终止广播
                break;
            //发送本地广播
            case R.id.send_local_broadcast:
                final Intent localIntent= new Intent();
                localIntent.setAction("com.geelaro.localbroadcastrev");
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(localIntent);
                break;
           default:
                break;
        }
    }

    /**NetworkReceiver
     * 网络发生变化时，系统发出广播，onReceive()工作
     */
    public static class NetworkReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "Network is available!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "NetWork change.");
            } else {
                Toast.makeText(context, "Network is closed!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onReceive: Network is closed.");

            }
        }
    }
}
