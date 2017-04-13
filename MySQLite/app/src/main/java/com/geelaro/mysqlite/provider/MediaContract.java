package com.geelaro.mysqlite.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by Brian on 2016/8/12.
 */
public class MediaContract {
    private int id;
    private String title;
    private String context;
    private Date publishdate;
    private int commentcount;

    // generated getters and setters.

    public static final String CONTENT_AUTHORITY = "com.geelaro.mysqlite";
    //
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //
    public static final String PATH_NEWS = "news";
    public static final String PATH_COMMENT = "comment";

    /**
     *
     */
    public static final class NewsEntry implements BaseColumns {

        public static final String TABLE_NAME = "news";

        //
        public static final String  NEWS_ID = "_id";

        public static final String TITLE = "title";

        public static final String CONTEXT = "context";

        public static final String PUBLISH_DATE = "publishdate";

        public static final String COMMENTS_COUNT = "commentscount";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NEWS).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NEWS;
    }

    /**
     *
     */
    public static final class CommentEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENT;
    }
}
