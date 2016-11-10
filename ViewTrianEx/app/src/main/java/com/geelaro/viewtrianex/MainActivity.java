package com.geelaro.viewtrianex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.geelaro.viewtrianex.viewact.AutoTextViewActivity;
import com.geelaro.viewtrianex.viewact.GridActivity;
import com.geelaro.viewtrianex.viewact.ListViewActivity;
import com.geelaro.viewtrianex.viewact.SpinnerActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//    private final static int TYPE_APP = 0;
//    private final static int TYPE_BOOK = 1;

    private final static String TAG="MainActivity";
    private Button btnToGridView; //
    private Button btnToListView;
    private Button btnToSpinner;
    private Button btnToAutoTxView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    //初始化
    private void init(){

        btnToGridView=(Button)findViewById(R.id.btn_grid_icon);
        btnToListView=(Button)findViewById(R.id.btn_list);
        btnToSpinner=(Button)findViewById(R.id.btn_spinner);
        btnToAutoTxView=(Button)findViewById(R.id.btn_auto_txview);
        btnToGridView.setOnClickListener(this);
        btnToListView.setOnClickListener(this);
        btnToSpinner.setOnClickListener(this);
        btnToAutoTxView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_grid_icon:
                Intent gridIntent=new Intent(this,GridActivity.class);
                startActivity(gridIntent);
                break;
            case R.id.btn_list:
                Intent listIntent=new Intent(this, ListViewActivity.class);
                startActivity(listIntent);
                break;
            case R.id.btn_spinner:
                Intent spinnerIntent=new Intent(this, SpinnerActivity.class);
                startActivity(spinnerIntent);
                break;
            case R.id.btn_auto_txview:
                Intent tvIntent=new Intent(this, AutoTextViewActivity.class);
                startActivity(tvIntent);
                break;
            default:
                break;
        }
    }
}
