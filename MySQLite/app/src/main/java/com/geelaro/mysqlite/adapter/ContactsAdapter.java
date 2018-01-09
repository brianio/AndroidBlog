package com.geelaro.mysqlite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geelaro.mysqlite.R;

/**
 * Created by geelaro on 2017/12/19.
 */

public class ContactsAdapter extends CursorAdapter {
    private static final String TAG = ContactsAdapter.class.getSimpleName();

    public ContactsAdapter(Context context, Cursor c,int flags) {
        super(context, c,flags);
        Log.d(TAG, "ContactsAdapter: ");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_contacts, parent, false);

        Log.d(TAG, "newView: ");
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView itemInfo = (TextView) view.findViewById(R.id.item_contacts);

        itemInfo.setText(convertCursorRowToUXFormat(cursor));
//        itemInfo.setGravity(Gravity.CENTER);
        Log.d(TAG, "bindView: ");
    }

    private String convertCursorRowToUXFormat(Cursor cursor) {
        //get Contacts's Names
        String contactsName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        // get Phone Number
        String phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

        return contactsName + " / " + phoneNum;

    }


}
