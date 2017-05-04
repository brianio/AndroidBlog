package com.geelaro.fragmentwork.tabs;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.fragmentwork.R;

/**
 * Created by Lee on 2017/5/4.
 */

public class ChartFragment extends Fragment {


    public ChartFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.rl_chart_layout,container,false);
        return rootView;
    }
}
