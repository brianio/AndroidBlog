package com.geelaro.mysqlite.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

<<<<<<< HEAD
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
=======
/**
 * Created by Lee on 2016/8/12.
 */
public class MediaContract {
    // generated getters and setters.
    public static final String CONTENT_AUTHORITY = "com.geelaro.mysqlite.media";
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
    //
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //
    public static final String PATH_NEWS = "news";
    public static final String PATH_COMMENT = "comment";

<<<<<<< HEAD
=======
    /*Default sort order*/
    public static final String DEFAULT_SORT_ORDER = "_id asc";

>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
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
<<<<<<< HEAD

=======
        /*Content URI*/
>>>>>>> 3ec1e9c85e62039460d9b285c6b6ac8f3184ddbf
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_COMMENT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMMENT;
    }
}
