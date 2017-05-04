package com.geelaro.fragmentwork.tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.fragmentwork.R;

/**
 * Created by Administrator on 2017/5/4.
 */

public class NewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View newsView=inflater.inflate(R.layout.rl_news_layout,container,false);
        return newsView;
    }
}
