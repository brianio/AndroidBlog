package com.brianio.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MySQLite extends SQLiteOpenHelper {

    private static final String TAG="MySQLite";

    //创建一个新表news,其中title，context,publishdate，commentcount分别表示新闻标题、内容、发布时间和评论数。
    public static final String CREAT_NEWS="create table news("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"context text,"
            +"publishdate integer,"
            +"commnetcount integer)";
    public static final String CREAT_COMMENT="create table comments("
            +"id integer primary key autoincrement,"
            +"context text,"
            +"publishdate integer )";

    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_NEWS);
        db.execSQL(CREAT_COMMENT);
        Log.d(TAG, "Create Table News");
        Log.d(TAG, "Create Table Comments");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREAT_COMMENT);
                Log.d(TAG, "Upgrade: Create Table Comments");
                break;
            case 2:
                db.execSQL("alter table comments add column pushlishdate integer");
                Log.d(TAG, "UPgrade:alter column publishdate");
                break;
            default:

        }

    }
}
