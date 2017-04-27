package com.geelaro.viewtrianex.viewactvities;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.viewtrianex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

@SuppressWarnings("DefaultFileTemplate")
public class SqliteListActivity extends AppCompatActivity {

    private final List<String> newsList = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_sqlite);

        ListView newsView = (ListView) findViewById(R.id.sqlite_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, newsList);
        newsView.setAdapter(adapter);
        readContacts();
    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            Uri uri=Uri.parse("content://com.geelaro.mysqlite.media/news");
            cursor = getContentResolver().query(uri, null, null, null, null);
            while (cursor.moveToNext()) {
                //get News's title
                String newsTitle = cursor.getString(cursor.getColumnIndex("title"));
                // get News's content
                String context = cursor.getString(cursor.getColumnIndex("context"));
                newsList.add(newsTitle + "\n" + context);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
