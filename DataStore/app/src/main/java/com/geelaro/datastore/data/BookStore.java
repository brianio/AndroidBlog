package com.geelaro.datastore.data;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by LEE on 2017/4/8.
 */

public class BookStore {
    //
    public static final String CONTENT_AUTHORITY = "com.geelaro.datastore";
    //
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //
    public static final String PATH_BOOK = "book";
    public static final String PATH_CATEGORY = "category";
    //

    /*Authority*/
    public static final String AUTHORITY = "com.geelaro.datastore.book";

    public static class BookEntry{
        //
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BOOK).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOK;
        //
        public static final String TABLE_NAME="book";
        //
        public static final String COLUMNS_BOOK_KEY="id";
        //
        public static final String AUTHOR_NAME="author";
        //
        public static final String BOOK_PRICE="price";
        //
        public static final String PAGES_NUM="pages";
        //
        public static final String BOOK_NAME="title";
        //
        public static final String CATEGORY_ID="";

    }

    public static class CategoryEntry{
        //
        public static final String TABLE_NAME="category";
        //
        public static final String COLUMNS_CAT_KEY="id";
        //
        public static final String CATEGORY_NAME="category_name";
        //
        public static final String CATEGORY_CODE="category_code";

    }


}
