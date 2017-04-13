package com.geelaro.datastore.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.geelaro.datastore.data.BookDbHelper;
import com.geelaro.datastore.data.BookStore;

/**
 * Created by LEE on 2017/4/12.
 */

public class BookProvider extends ContentProvider {
    //
    public static final int BOOK = 1;
    public static final int BOOK_ID = 11;
    public static final int BOOK_AUTHOR = 12;
    public static final int BOOK_PRICE = 13;
    public static final int BOOK_PAGES = 14;
    public static final int BOOK_TITLE = 15;
    //
    private static UriMatcher sUriMatch = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatch.addURI("bookstore", "book", BOOK);
        sUriMatch.addURI("bookstore", "book/#", BOOK_ID);
        sUriMatch.addURI("bookstore", "book/#/author", BOOK_AUTHOR);
        sUriMatch.addURI("bookstore", "book/#/price", BOOK_PRICE);
        sUriMatch.addURI("bookstore", "book/#/pages", BOOK_PAGES);
        sUriMatch.addURI("bookstore", "book/#/title", BOOK_TITLE);

    }

    //
    private BookDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new BookDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //
        switch (sUriMatch.match(uri)) {
            case BOOK:
                //查看表book中所有数据
                cursor = db.query(BookStore.BookEntry.TABLE_NAME, strings, s, strings1,null,null,s1);
                break;
            default:
                break;
        }
        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        switch (sUriMatch.match(uri)) {
            case BOOK:
                return BookStore.BookEntry.CONTENT_TYPE;

        }
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
