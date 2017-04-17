package com.geelaro.datastore.data;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by LEE on 2017/4/8.
 */

public class BookStore {
    //Authority
    public static final String CONTENT_AUTHORITY = "com.geelaro.datastore.book";
    //Content URI
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //路径
    public static final String PATH_BOOK = "book";
    public static final String PATH_CATEGORY = "category";

    /**
     * 表book的uri、表名、列名
     */
    public static class BookEntry {
        //表book的数据uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BOOK).build();
        //表book的MIME:vnd.android.cursor.dir/com.geelaro.datastore.book/book
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOK;
        //name of table book
        public static final String TABLE_NAME = "book";
        //name of column for id
        public static final String COLUMNS_BOOK_KEY = "id";
        //
        public static final String AUTHOR_NAME = "author";
        //
        public static final String BOOK_PRICE = "price";
        //
        public static final String PAGES_NUM = "pages";
        //
        public static final String BOOK_NAME = "title";
        //
        public static final String CATEGORY_ID = "";

    }

    /**
     * 表category的表名、列名和uri
     */
    public static class CategoryEntry {
        //表category数据uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORY).build();
        //表category的MIME : vnd.android.cursor.dir/com.geelaro.datastore.book/category
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORY;
        //
        public static final String TABLE_NAME = "category";
        //
        public static final String COLUMNS_CAT_KEY = "id";
        //
        public static final String CATEGORY_NAME = "category_name";
        //
        public static final String CATEGORY_CODE = "category_code";



    }


}
