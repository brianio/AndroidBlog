package com.geelaro.mysqlite;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;

import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.geelaro.mysqlite.adapter.ContactsAdapter;

/**
 * Created by LEE on 2017/12/20.
 */

public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int INIT_LOADER = 0;
    private ContactsAdapter mAdapter;
    private ListView mListView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(INIT_LOADER,null,this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contactsView = inflater.inflate(R.layout.fragment_contacts,container,false);

        mAdapter = new ContactsAdapter(getActivity(),null,0);

        mListView  = (ListView) contactsView.findViewById(R.id.contact_listview);
        mListView.setAdapter(mAdapter);

        return contactsView;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        Uri contactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] mProjection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        return new CursorLoader(getActivity(),
                contactsUri,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.swapCursor(null);

    }
}
