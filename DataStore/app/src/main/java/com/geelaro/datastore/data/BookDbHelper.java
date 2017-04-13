package com.geelaro.datastore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by LEE on 2017/4/8.
 */

public class BookDbHelper extends SQLiteOpenHelper {
    //Database version
    private final static int DATABASE_VERSION = 2;
    //Database name
    private final static String DATABASE_NAME = "bookstore.db";
    private Context mContext;

    //创建一个表book，表中有ID、author、price、pages、和书名title
    final String CREATE_TABLE_BOOK = "create table " + BookStore.BookEntry.TABLE_NAME + "("
            + BookStore.BookEntry.COLUMNS_BOOK_KEY + " integer primary key autoincrement,"
            + BookStore.BookEntry.AUTHOR_NAME + " text,"
            + BookStore.BookEntry.BOOK_PRICE + " real,"
            + BookStore.BookEntry.PAGES_NUM + " integer,"
            + BookStore.BookEntry.BOOK_NAME + " text);";
    //新增一个表category，表中有ID、categoryName、categoryCode.同时数据库version+1
    final String CREATE_TABLE_CATEGORY = "create table " + BookStore.CategoryEntry.TABLE_NAME + "("
            + BookStore.CategoryEntry.COLUMNS_CAT_KEY + " integer primary key autoincrement,"
            + BookStore.CategoryEntry.CATEGORY_NAME + " text,"
            + BookStore.CategoryEntry.CATEGORY_CODE + " integer);";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_BOOK);
        db.execSQL(CREATE_TABLE_CATEGORY);
        Toast.makeText(mContext,"Database is created successful",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("drop table if exists " + BookStore.BookEntry.TABLE_NAME);
//        sqLiteDatabase.execSQL("drop table if exists " + BookStore.CategoryEntry.TABLE_NAME);
//        onCreate(sqLiteDatabase);
        switch (oldVersion){
            case 1:
                sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORY);
            default:
        }
    }
}
