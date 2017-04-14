package com.geelaro.viewtrianex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.geelaro.viewtrianex.viewact.AutoTextViewActivity;
import com.geelaro.viewtrianex.viewact.GridActivity;
import com.geelaro.viewtrianex.viewact.ListViewActivity;
import com.geelaro.viewtrianex.viewact.SpinnerActivity;
import com.geelaro.viewtrianex.viewact.SqliteListActivity;
import com.geelaro.viewtrianex.viewact.ViewFlipperActivity;
import com.geelaro.viewtrianex.viewact.WebViewActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";
    //跳转按钮
    private Button btnToGridView;
    private Button btnToListView;
    private Button btnToSpinner;
    private Button btnToAutoTxView;
    private Button btnToViewFilpper;
    private Button btnToWebView;
<<<<<<< HEAD
    private Button btnSqliteListView;
=======
<<<<<<< HEAD
=======
    private Button btnSqliteListView;
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
>>>>>>> 0fc02281fb84eb8fa6f8baa93b76467c84c1a2e4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    //初始化
    private void init() {

<<<<<<< HEAD
=======
<<<<<<< HEAD
        btnToGridView=(Button)findViewById(R.id.btn_grid_icon);
        btnToListView=(Button)findViewById(R.id.btn_list);
        btnToSpinner=(Button)findViewById(R.id.btn_spinner);
        btnToAutoTxView=(Button)findViewById(R.id.btn_auto_txview);
        btnToViewFilpper=(Button)findViewById(R.id.btn_flipper);
        btnToWebView=(Button)findViewById(R.id.btn_webview);
=======
>>>>>>> 0fc02281fb84eb8fa6f8baa93b76467c84c1a2e4
        btnToGridView = (Button) findViewById(R.id.btn_grid_icon);
        btnToListView = (Button) findViewById(R.id.btn_list);
        btnToSpinner = (Button) findViewById(R.id.btn_spinner);
        btnToAutoTxView = (Button) findViewById(R.id.btn_auto_txview);
        btnToViewFilpper = (Button) findViewById(R.id.btn_flipper);
        btnToWebView = (Button) findViewById(R.id.btn_webview);
        btnSqliteListView = (Button) findViewById(R.id.btn_sqlite);
<<<<<<< HEAD
=======
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
>>>>>>> 0fc02281fb84eb8fa6f8baa93b76467c84c1a2e4

        btnToGridView.setOnClickListener(this);
        btnToListView.setOnClickListener(this);
        btnToSpinner.setOnClickListener(this);
        btnToAutoTxView.setOnClickListener(this);
        btnToViewFilpper.setOnClickListener(this);
        btnToWebView.setOnClickListener(this);
<<<<<<< HEAD
        btnSqliteListView.setOnClickListener(this);
=======
<<<<<<< HEAD
=======
        btnSqliteListView.setOnClickListener(this);
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
>>>>>>> 0fc02281fb84eb8fa6f8baa93b76467c84c1a2e4
    }

    /**
     * @Funcation 跳转到不同的view类
     * @Author geelaro
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_grid_icon:
                Intent gridIntent = new Intent(this, GridActivity.class);
                startActivity(gridIntent);
                break;
            case R.id.btn_list:
                Intent listIntent = new Intent(this, ListViewActivity.class);
                startActivity(listIntent);
                break;
            case R.id.btn_spinner:
                Intent spinnerIntent = new Intent(this, SpinnerActivity.class);
                startActivity(spinnerIntent);
                break;
            case R.id.btn_auto_txview:
                Intent tvIntent = new Intent(this, AutoTextViewActivity.class);
                startActivity(tvIntent);
                break;
            case R.id.btn_flipper:
                Intent vflpIntent = new Intent(this, ViewFlipperActivity.class);
                startActivity(vflpIntent);
                break;
            case R.id.btn_webview:
<<<<<<< HEAD
=======
<<<<<<< HEAD
                Intent webIntent=new Intent(this,WebViewActivity.class);
                startActivity(webIntent);
                break;
=======
>>>>>>> 0fc02281fb84eb8fa6f8baa93b76467c84c1a2e4
                Intent webIntent = new Intent(this, WebViewActivity.class);
                startActivity(webIntent);
                break;
            case R.id.btn_sqlite:
                Intent sqliteIntent = new Intent(this, SqliteListActivity.class);
                startActivity(sqliteIntent);
                break;
<<<<<<< HEAD
=======
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
>>>>>>> 0fc02281fb84eb8fa6f8baa93b76467c84c1a2e4
            default:
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = new MenuInflater(this);
        Inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
