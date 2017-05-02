package com.geelaro.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.text.SimpleDateFormat;

/**
 * Created by LEE on 2017/4/28.
 */

public class WBAuthActivity extends AppCompatActivity {
    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
    //
    private SsoHandler mSsoHandler;
    /** 显示认证后的信息，如 AccessToken */
    private TextView mTokenText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mTokenText=(TextView)findViewById(R.id.token_text_view);

        //
        mSsoHandler = new SsoHandler(WBAuthActivity.this);

        //SSO授权，仅客户端
        findViewById(R.id.obtain_token_via_sso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSsoHandler.authorizeClientSso(new SelfWbAuthListener());
            }
        });
        //SSO授权，仅web
        findViewById(R.id.obtain_token_via_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorizeWeb(new SelfWbAuthListener());
            }
        });
        //SSO授权，All in
        findViewById(R.id.obtain_token_via_signature).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSsoHandler.authorize(new SelfWbAuthListener());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            WBAuthActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
//                        // 显示 Token
                        updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);
                        Toast.makeText(WBAuthActivity.this,
                                R.string.weibosdk_toast_auth_success, Toast.LENGTH_SHORT).show();
                    }
                }


            });
        }


        @Override
        public void cancel() {
            Toast.makeText(WBAuthActivity.this,
                    R.string.weibosdk_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(WBAuthActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * 显示当前 Token 信息。
         *
         * @param hasExisted 配置文件中是否已存在 token 信息并且合法
         */
        private void updateTokenView(boolean hasExisted) {
            String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                    new java.util.Date(mAccessToken.getExpiresTime()));
            String format = getString(R.string.weibosdk_token_to_string_format_1);
            mTokenText.setText(String.format(format, mAccessToken.getToken(), date));

            String message = String.format(format, mAccessToken.getToken(), date);
            if (hasExisted) {
                message = getString(R.string.weibosdk_token_has_existed) + "\n" + message;
            }
            mTokenText.setText(message);
        }
    }

}
