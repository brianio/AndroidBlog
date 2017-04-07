package com.geelaro.broadcastrevlogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geelaro.broadcastrevlogin.BaseActivity;
import com.geelaro.broadcastrevlogin.R;

/**
 * Created by LEE on 2017/4/7.
 */

public class LoginActivity extends BaseActivity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                //如果用户名为“admin”，密码为“123456”，则登录成功
                if(account.equals("admin")&&password.equals("123456")){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Account or Password is invalid.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //初始化
    private void init(){
        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.btn_login);

    }
}
