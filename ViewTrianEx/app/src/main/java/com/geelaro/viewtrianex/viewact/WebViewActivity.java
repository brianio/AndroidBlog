package com.geelaro.viewtrianex.viewact;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.geelaro.viewtrianex.R;

/**
 * Created by Lee on 2017/4/12.
 */

public class WebViewActivity extends AppCompatActivity {
    private WebView webView;
    private FloatingActionButton fab;
    private long exiTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView =new WebView(this);

        webView.getSettings().getJavaScriptEnabled();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
                return false;
            }
        });

        webView.loadUrl("http://www.hao123.com");
        setContentView(webView);


        WebSettings settings=webView.getSettings();
        settings.setUseWideViewPort(true); // 支持viewport
        settings.setLoadWithOverviewMode(true);//支持自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }else{
            if ((System.currentTimeMillis()-exiTime)>2000){
                Toast.makeText(this,"再按一次退出WebView",Toast.LENGTH_SHORT).show();
                exiTime=System.currentTimeMillis();
            }else {
                finish();
            }
        }
    }

}
