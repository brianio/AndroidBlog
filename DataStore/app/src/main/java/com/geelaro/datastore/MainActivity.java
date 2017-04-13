package com.geelaro.datastore;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geelaro.datastore.data.BookDbHelper;
import com.geelaro.datastore.provider.ReadBook;
import com.geelaro.datastore.provider.ReadContacts;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button saveData;
    private Button restoreData;
    private TextView tvDisplay;
    private BookDbHelper dbHelper;
    private Button createDb;
    private Button addData;
    private Button updateData;
    private Button delData;
    private Button queryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        dbHelper = new BookDbHelper(getApplicationContext());
        createDb = (Button) findViewById(R.id.btn_createdb);
        createDb.setOnClickListener(this);

        tvDisplay = (TextView) findViewById(R.id.tv_display);
        //存数据
        saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(this);
        //读数据
        restoreData = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(this);
        //add 数据
        addData = (Button) findViewById(R.id.btn_add_data);
        addData.setOnClickListener(this);
        //update 数据
        updateData = (Button) findViewById(R.id.btn_update_data);
        updateData.setOnClickListener(this);
        //delete 数据
        delData = (Button) findViewById(R.id.btn_del_data);
        delData.setOnClickListener(this);
        //query 数据
        queryData = (Button) findViewById(R.id.btn_query_data);
        queryData.setOnClickListener(this);
        //replace 数据
        Button replaceData = (Button) findViewById(R.id.btn_replace_data);
        replaceData.setOnClickListener(this);
        //
        Button getContacts = (Button) findViewById(R.id.btn_get_contacts);
        getContacts.setOnClickListener(this);
        //
        Button getBooks=(Button)findViewById(R.id.btn_get_books);
        getBooks.setOnClickListener(this);

    }

    //写入数据
    public void saveData() {
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("sample", "Nice");
        editor.putString("name", "Lucy");
        editor.putInt("age", 28);
        editor.putBoolean("isfemale", true);
        editor.apply();

    }

    //读数据
    public void restoreData() {
        SharedPreferences preferences = getSharedPreferences("data", MODE_PRIVATE);
        String sample = preferences.getString("sample", "");
        String name = preferences.getString("name", "");
        int age = preferences.getInt("age", 0);
        boolean isfemale = preferences.getBoolean("isfemale", false);
        tvDisplay.setText(name + "\n" + isfemale + "\n" + age + "\n" + sample);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_data:
                saveData();//存数据
                break;
            case R.id.restore_data:
                restoreData();//读数据
                break;
            case R.id.btn_createdb:
                dbHelper.getWritableDatabase();
                break;
            case R.id.btn_add_data:
                DbAction.addData(dbHelper);
                break;
            case R.id.btn_update_data:
                DbAction.updateData(dbHelper);
                break;
            case R.id.btn_del_data:
                DbAction.deleteData(dbHelper);
                break;
            case R.id.btn_query_data:
                tvDisplay.setText(DbAction.queryData(dbHelper).toString());
                break;
            case R.id.btn_replace_data:
                DbAction.replaceData(dbHelper);
                break;
            case R.id.btn_get_contacts:
                Intent intent = new Intent(this, ReadContacts.class);
                startActivity(intent);
                break;
            case R.id.btn_get_books:
                Intent bookIntent = new Intent(this, ReadBook.class);
                startActivity(bookIntent);
                break;
            default:
                break;
        }

    }
}

/**
 * 对数据库SQLite进行数据操作
 */
class DbAction {

    /**
     * add data to SQLite
     */
    static void addData(BookDbHelper bookDbHelper) {
        SQLiteDatabase db = bookDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //添加第一条数据
        values.put("title", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        db.insert("book", null, values); // 插入数据
        values.clear();

        //添加第二条数据
        values.put("title", "The Lost Symbol");
        values.put("author", "Dan Brown");
        values.put("pages", 510);
        values.put("price", 19.23);
        db.insert("book", null, values); // 插入数据

    }

    /**
     * update data to SQLite
     */
    static void updateData(BookDbHelper bookDbHelper) {
        SQLiteDatabase db = bookDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 10.99);
        db.update("book", values, "title=?", new String[]{"The Da Vinci Code"});
    }

    /**
     * delete data from SQLite
     */
    static void deleteData(BookDbHelper bookDbHelper) {
        SQLiteDatabase db = bookDbHelper.getWritableDatabase();
//        db.delete("book", "pages>?", new String[]{"500"});
        db.delete("book", null, null);
    }

    /**
     * query data from SQLite
     */
    static ArrayList<String> queryData(BookDbHelper bookDbHelper) {
        SQLiteDatabase db = bookDbHelper.getWritableDatabase();
        String results = "";
        ArrayList<String> resultList = new ArrayList<>();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                results = title + "-" + author + "-" + pages + "-" + price;
                Log.d("QueryData", ": " + results);
                resultList.add(results + "\n");
            } while (cursor.moveToNext());
        }

        cursor.close();
        return resultList;
    }

    /**
     * replace data / beginTransaction()
     */
    static void replaceData(BookDbHelper bookDbHelper) {
        SQLiteDatabase db = bookDbHelper.getWritableDatabase();
        db.beginTransaction();//开启事务
        try{
            db.delete("book",null,null);
//            if (true){
//                //在这里手动抛出一个异常，让事务失败
//                throw new NullPointerException();
//            }

            ContentValues values = new ContentValues();
            //添加第一条数据
            values.put("title", "Game of Thrones");
            values.put("author", "George Martin");
            values.put("pages", 800);
            values.put("price", 88.65);
            db.insert("book", null, values); // 插入数据
            db.setTransactionSuccessful();//事务已经执行成功

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();//结束事务
        }
    }
}
