# AndroidBlog

Android的模块训练项目

## ActionBarEx

ActionBar

## WeChatEx

参考郭神博客，模仿实现微信主界面，其中滑动部分使用了开源项目 [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip) , 感谢astuetz的开源。 

## ViewTrianEx

Android View 类练习Demo

#### 1. 通过ContentProvider使用其他应用数据
MySQLite应用提供的ContentProvider

```
Cursor cursor = null;
        try {
            Uri uri=Uri.parse("content://com.geelaro.mysqlite.media/news");
            cursor = getContentResolver().query(uri, null, null, null, null);
            while (cursor.moveToNext()) {
                //get News's title
                String newsTitle = cursor.getString(cursor.getColumnIndex("title"));
                // get News's content
                String content = cursor.getString(cursor.getColumnIndex("content"));
                newsList.add(newsTitle + "\n" + content);
            }
```


## LayoutEx

Android Layout 布局Demo

## MySQLite

Android 数据库SQLite项目。
#### 1. 对数据库的操作
```
//新建一个对象，MySQLiteHelper 继承 SQLiteOpenHelper
MySQLiteHelper dbHelper=new MySQLiteHelper(getApplicationContext());
//创建数据库对象
SQLiteDatabase db = dbHelper.getWritableDatabase();
ContentValues values = new ContentValues();
//对数据库操作
db.insert(...); //insert data
db.update(...); //update data
db.query(...);  //query data
db.delete(...); //delete data
```
[更多请访问SQLiteDatabase](http://developer.android.youdaxue.com/reference/android/database/sqlite/SQLiteDatabase.html#deleteDatabase(java.io.File))

#### 2. 提供ContentProvider 

```
private static final UriMatcher sUriMatch = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news", NEWS);
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "news/#", NEWS_ID);
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "comments", COMMENTS);
        sUriMatch.addURI(MediaContract.CONTENT_AUTHORITY, "comments/#", COMMENTS_ID);
        }
        
//manifests.xml
<provider
    android:authorities="com.geelaro.mysqlite.media"
    android:name=".provider.MyDataProvider"
    android:readPermission="com.geelaro.mysqlite.READ"
    android:exported="true">
</provider>
```

## BroadcastRev

Android BroadcastReceiver, 包括创建、注册、发送（普通广播、有序广播），和本地传播的注册和发送。

## BroadcastRevLogin

BroadcastReceiver实践项目：通过广播机制，强制账户下线的功能.

## DataStore
Android 数据存储方案：File、SharedPreferences和SQLite