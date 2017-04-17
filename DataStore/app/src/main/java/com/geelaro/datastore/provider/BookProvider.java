package com.geelaro.datastore.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.geelaro.datastore.data.BookDbHelper;
import com.geelaro.datastore.data.BookStore;

import static android.content.ContentValues.TAG;

/**
 * Created by LEE on 2017/4/12.
 */

public class BookProvider extends ContentProvider {
    //
    public static final int BOOK = 1;
    public static final int BOOK_ID = 11;
//    public static final int BOOK_AUTHOR = 12;
//    public static final int BOOK_PRICE = 13;
//    public static final int BOOK_PAGES = 14;
//    public static final int BOOK_TITLE = 15;

    public static final int CATEGORY = 2;
    public static final int CATEGORY_ID = 21;
    //
    private static UriMatcher sUriMatch = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "book", BOOK);
        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "book/#", BOOK_ID);
//        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "book/#/author", BOOK_AUTHOR);
//        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "book/#/price", BOOK_PRICE);
//        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "book/#/pages", BOOK_PAGES);
//        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "book/#/title", BOOK_TITLE);
        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "category", CATEGORY);
        sUriMatch.addURI(BookStore.CONTENT_AUTHORITY, "category/#", CATEGORY_ID);

    }

    //
    private BookDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new BookDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] columns, String selection, String[] selectionArgs, String order) {
        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //
        switch (sUriMatch.match(uri)) {
            case BOOK:
                //查看表book中所有数据
                cursor = db.query(BookStore.BookEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, order);
                Log.d(TAG, "getType: " + BookStore.BookEntry.CONTENT_TYPE);
                Log.d(TAG, "getType: " + BookStore.CategoryEntry.CONTENT_TYPE);

                break;
            case CATEGORY:
                cursor = db.query(BookStore.CategoryEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, order);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri:" + uri);
        }
        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        switch (sUriMatch.match(uri)) {
            case BOOK:
                return BookStore.BookEntry.CONTENT_TYPE;
            case CATEGORY:
                return BookStore.CategoryEntry.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Error uri:" + uri);

        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri newUri;
        switch (sUriMatch.match(uri)) {
            case BOOK: {
                long id = db.insert(BookStore.BookEntry.TABLE_NAME, null, contentValues);
                if (id > 0) {
                    newUri = ContentUris.withAppendedId(uri, id);
                } else
                    throw new SQLiteException("Unable to insert" + contentValues + "for" + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return newUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (sUriMatch.match(uri)) {
            case BOOK:
                count = db.delete(BookStore.BookEntry.TABLE_NAME, s, strings);
                break;
            case BOOK_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(BookStore.BookEntry.TABLE_NAME, BookStore.BookEntry.COLUMNS_BOOK_KEY + "=" + id
                        + (!TextUtils.isEmpty(s) ? " and (" + s + ')' : ""), strings);

                break;
            default:
                throw new IllegalArgumentException("Error uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (sUriMatch.match(uri)) {
            case BOOK:
                count = db.update(BookStore.BookEntry.TABLE_NAME, contentValues, s, strings);
                break;
            case BOOK_ID:
                String id = uri.getPathSegments().get(1);
                count = db.update(BookStore.BookEntry.TABLE_NAME, contentValues, BookStore.BookEntry.COLUMNS_BOOK_KEY + "=" + id
                        + (!TextUtils.isEmpty(s) ? " and (" + s + ')' : ""), strings);

                break;
            default:
                throw new IllegalArgumentException("Error uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
