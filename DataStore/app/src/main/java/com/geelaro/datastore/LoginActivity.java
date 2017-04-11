package com.geelaro.datastore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by LEE on 2017/4/8.
 */

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button btnLogin;
    private CheckBox rememberPass;
    private String account;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        boolean isRemember = preferences.getBoolean("remember_password", false);
        if (isRemember) {
            //把账户信息都保存在文本框里
            String account = preferences.getString("account", "");
            String password = preferences.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = accountEdit.getText().toString();
                password = passwordEdit.getText().toString();
                //如果用户名为“admin”，密码为“123456”，则登录成功
                if (account.equals("admin") && password.equals("123456")) {
                    //检查复选框是否选中，选中保存账户信息，否则不保存
                    saveData(rememberPass.isChecked());
                    //登录成功，跳到首页
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //提示账户信息错误
                    Toast.makeText(LoginActivity.this, "Account or Password is invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //初始化
    private void init() {
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        rememberPass = (CheckBox) findViewById(R.id.remember_password);

    }

    //save data
    private void saveData(Boolean isChecked) {
        if (isChecked) {
            editor.putString("account", account);
            editor.putString("password", password);
            editor.putBoolean("remember_password", true);
        } else {
            editor.clear();
        }

        editor.commit();
    }
}
