package com.geelaro.fragmentwork.tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.fragmentwork.R;

/**
 * Created by Lee on 2017/5/4.
 */

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View  settingsView = inflater.inflate(R.layout.rl_settings_layout,container,false);
        return settingsView;
    }
}
