package com.geelaro.viewtrianex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.geelaro.viewtrianex.viewactvities.AutoTextViewActivity;
import com.geelaro.viewtrianex.viewactvities.GridActivity;
import com.geelaro.viewtrianex.viewactvities.ListViewActivity;
import com.geelaro.viewtrianex.viewactvities.RecycleViewActivity;
import com.geelaro.viewtrianex.viewactvities.SpinnerActivity;
import com.geelaro.viewtrianex.viewactvities.SqliteListActivity;
import com.geelaro.viewtrianex.viewactvities.ViewFlipperActivity;
import com.geelaro.viewtrianex.viewactvities.WebViewActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";
    //跳转按钮
    private Button btnToGridView;
    private Button btnToListView;
    private Button btnToSpinner;
    private Button btnToAutoTxView;
    private Button btnToViewFilpper;
    private Button btnToWebView;
    private Button btnSqliteListView;
    private Button btnRecycelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    //初始化
    private void init() {

        btnToGridView = (Button) findViewById(R.id.btn_grid_icon);
        btnToListView = (Button) findViewById(R.id.btn_list);
        btnToSpinner = (Button) findViewById(R.id.btn_spinner);
        btnToAutoTxView = (Button) findViewById(R.id.btn_auto_txview);
        btnToViewFilpper = (Button) findViewById(R.id.btn_flipper);
        btnToWebView = (Button) findViewById(R.id.btn_webview);
        btnSqliteListView = (Button) findViewById(R.id.btn_sqlite);
        btnRecycelView=(Button) findViewById(R.id.btn_recycle_view);

        btnToGridView.setOnClickListener(this);
        btnToListView.setOnClickListener(this);
        btnToSpinner.setOnClickListener(this);
        btnToAutoTxView.setOnClickListener(this);
        btnToViewFilpper.setOnClickListener(this);
        btnToWebView.setOnClickListener(this);

        btnSqliteListView.setOnClickListener(this);
        btnRecycelView.setOnClickListener(this); //go to RecycleView


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
                Intent webIntent=new Intent(this,WebViewActivity.class);
                startActivity(webIntent);
                break;
            case R.id.btn_sqlite:
                Intent sqliteIntent = new Intent(this, SqliteListActivity.class);
                startActivity(sqliteIntent);
                break;
            case R.id.btn_recycle_view:
                Intent recycleIntent = new Intent(this, RecycleViewActivity.class);
                startActivity(recycleIntent);
                break;
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
