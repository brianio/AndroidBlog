package com.brianio.mysqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button creatrDB =(Button)findViewById(R.id.create_db);
        creatrDB.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "Create New db");
        //传统建表方法
        SQLiteOpenHelper dbHelper=new MySQLite(this,"demo2.db",null,3);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

//        Intent intent=new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.google.com"));
//        startActivity(intent);

        //LitePal 方法
//        SQLiteDatabase db = Connector.getDatabase();


    }
}
