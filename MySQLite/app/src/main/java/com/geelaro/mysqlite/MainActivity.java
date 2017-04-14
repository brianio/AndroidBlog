package com.geelaro.mysqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geelaro.mysqlite.provider.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //
    private static final String TAG = MainActivity.class.getSimpleName();
    //
    private MySQLiteHelper dbHelper;
    //
    private List<String> dataList;
    //
    private TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MySQLiteHelper(getApplicationContext());
        dataList = new ArrayList<>();

        //
        tvDisplay = (TextView) findViewById(R.id.tv_display);

        Button createDb = (Button) findViewById(R.id.create_db);
        createDb.setOnClickListener(this);
        //
        Button getContacts = (Button) findViewById(R.id.get_contacts);
        getContacts.setOnClickListener(this);
        //
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_db:
<<<<<<< HEAD
                Log.d(TAG, "Create New db");
=======
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
                //传统建表方法
                dbHelper.getWritableDatabase();
                addData();

                //        Intent intent=new Intent(Intent.ACTION_VIEW);
                //        intent.setData(Uri.parse("http://www.google.com"));
                //        startActivity(intent);

                //LitePal 方法
                //        SQLiteDatabase db = Connector.getDatabase();
                break;
            case R.id.get_contacts:
                Intent intent = new Intent(this, ContactsData.class);
                startActivity(intent);
                break;
            case R.id.query_data:
                queryData();
                tvDisplay.setText(dataList.toString());

        }
    }

    /**
     * add data to SQLite
     */
    public void addData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //添加第一条数据
        values.put("title", "Human Failed");
        values.put("context", "we lost");
        values.put("publishdate", 20170413);
        values.put("commentcount", 496);
        db.insert("news", null, values); // 插入数据
        values.clear();

        //添加第二条数据
        values.put("title", "The Lost Symbol");
        values.put("context", "Dan Brown");
        values.put("publishdate", 20160323);
        values.put("commentcount", 1923);
        db.insert("news", null, values); // 插入数据

    }

    /**
     * query data from SQLite
     */
    public void queryData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String results = "";
        Cursor cursor = db.query("news", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String context = cursor.getString(cursor.getColumnIndex("context"));
                long publishDate = cursor.getLong(cursor.getColumnIndex("publishdate"));
                long commnetCount = cursor.getLong(cursor.getColumnIndex("commentcount"));
                results = title + "-" + context + "-" + publishDate + "-" + commnetCount;
                Log.d("QueryData", ": " + results);
                dataList.add(results + "\n");

            } while (cursor.moveToNext());
        }

        cursor.close();
    }

}
