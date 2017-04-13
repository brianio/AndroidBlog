package com.geelaro.datastore.provider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.datastore.R;
import com.geelaro.datastore.data.BookStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEE on 2017/4/14.
 */

public class ReadBook extends AppCompatActivity {
    private ListView bookView;
    private ArrayAdapter<String> adapter;
    private List<String> bookList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ll_book_listview);
        bookView = (ListView) findViewById(R.id.book_listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookList);
        bookView.setAdapter(adapter);
        readBooks();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void readBooks() {
        Cursor cursor = null;
        try {
            //
            cursor = getContentResolver().query(BookStore.BookEntry.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                //获取书名
                String bookTitle = cursor.getString(cursor.getColumnIndex(BookStore.BookEntry.BOOK_NAME));
                Log.d("Book", ": " + bookTitle);
                //获取作者名
                String author = cursor.getString(cursor.getColumnIndex(BookStore.BookEntry.AUTHOR_NAME));
                bookList.add(bookTitle + "\n" + author);
                Log.d("Book", ": " + author);
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
