package com.geelaro.broadcastrevlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {
    private Button forceOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //定义一个按钮
        forceOffline=(Button)findViewById(R.id.btn_force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送一个广播，强制账户下线
                Intent intent=new Intent("com.geelaro.broadcastrevlogin.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

    }
}
