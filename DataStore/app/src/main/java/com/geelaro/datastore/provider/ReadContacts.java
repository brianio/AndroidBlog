package com.geelaro.datastore.provider;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.geelaro.datastore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEE on 2017/4/12.
 */

public class ReadContacts extends AppCompatActivity {
    private ListView contactsView;
    private ArrayAdapter<String> adapter;
    private List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        contactsView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);
        readContacts();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void readContacts() {
        Cursor cursor = null;
        try {
            //
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()) {
                //获取联系人姓名
                String ContactName = cursor.getString(cursor.getColumnIndex(ContactsContract
                        .CommonDataKinds.Phone.DISPLAY_NAME));
                Log.d("Contacts", "readContacts: "+ContactName);
                //获取手机号码
                String phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract
                        .CommonDataKinds.Phone.NUMBER));
                contactsList.add(ContactName + "\n" + phoneNum);
                Log.d("Contacts", "readContacts: "+phoneNum);
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
