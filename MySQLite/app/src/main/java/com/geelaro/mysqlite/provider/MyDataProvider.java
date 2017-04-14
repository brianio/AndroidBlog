package com.geelaro.mysqlite.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Lee on 2017/4/13.
 * My Data ContentProvider
 */

public class MyDataProvider extends ContentProvider {

    private static final int NEWS = 1;
    private static final int NEWS_ID = 11;
    private static final int NEWS_TITLE = 12;
    private static final int NEWS_CONTEXT = 13;
    private static final int NEWS_PUBLISH = 14;
    private static final int NEWS_COMMENTSCOUNT = 15;

    //
    private static final int COMMENTS = 2;
    private static final int COMMENTS_ID = 21;
    private static final int COMMENTS_CONTEXT = 22;
    private static final int COMMENTS_PUBLISHDATE = 23;

    private MySQLiteHelper dbHelper;


    private static final UriMatcher sUriMatch = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news", NEWS);
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news/#", NEWS_ID);
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news/#/title", NEWS_TITLE);
//        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news/#/context", NEWS_CONTEXT);
//        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news/#/publishdate", NEWS_PUBLISH);
//        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "new/#/commentcount", NEWS_COMMENTSCOUNT);

        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "comments", COMMENTS);
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "comments/#", COMMENTS_ID);
//        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "comments/#/context", COMMENTS_CONTEXT);
//        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "comments/#/publishdate", COMMENTS_PUBLISHDATE);


    }

    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (sUriMatch.match(uri)) {
            case NEWS:
                cursor = db.query(MediaContract.NewsEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
                break;
            case NEWS_ID:
                cursor=db.query(MediaContract.NewsEntry.TABLE_NAME,projection,"id=?",new String[]{"1"},null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
