package com.geelaro.fragmentwork.tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.fragmentwork.R;

/**
 * Created by Administrator on 2017/5/4.
 */

public class ContactsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View contactsView=inflater.inflate(R.layout.rl_contacts_layout,container,false);
        return contactsView;
    }
}
