package com.geelaro.datastore.data;

/**
 * Created by LEE on 2017/4/8.
 */

public class BookStore {

    /*Authority*/
    public static final String AUTHORITY = "com.geelaro.datastore.book";

    public static class BookEntry{
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
