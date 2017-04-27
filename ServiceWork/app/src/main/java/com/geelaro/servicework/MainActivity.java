package com.geelaro.servicework;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geelaro.servicework.services.CoreIntentService;
import com.geelaro.servicework.services.CoreService;
import com.geelaro.servicework.services.LongRunningService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int UPDATE_TEXT = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvDisplay;
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private CoreService.DownloadBinder downloadBinder;

    private Button startIntentService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (CoreService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    //UI 操作
                    tvDisplay.setText("Hello Java!");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = (TextView) findViewById(R.id.tv_display);
        //
        Button btnChange = (Button) findViewById(R.id.btn_change_text);
        btnChange.setOnClickListener(this);
        //
        startService = (Button) findViewById(R.id.startservice);
        stopService = (Button) findViewById(R.id.stopservice);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        //
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        //
        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
        //首次启动app时即启动LongRunningService
        Intent startLongService=new Intent(this,LongRunningService.class);
        startService(startLongService);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = mHandler.obtainMessage();
                        message.what = UPDATE_TEXT;
                        mHandler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.startservice:
                startService(new Intent(this, CoreService.class));
                break;
            case R.id.stopservice:
                stopService(new Intent(this, CoreService.class));
                break;
            case R.id.bind_service:
                bindService((new Intent(this, CoreService.class)), connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);//先点击unbind会引起app崩溃
                break;
            case R.id.start_intent_service:
                Log.d(TAG, "Thread ID is " + Thread.currentThread().getId());
                startService(new Intent(this, CoreIntentService.class));
                break;
            default:
                break;
        }

    }

    class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
//            progressDialog.show();//
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

}
