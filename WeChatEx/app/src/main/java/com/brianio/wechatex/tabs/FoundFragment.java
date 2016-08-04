package com.brianio.wechatex.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Brian on 2016/8/3.
 */
public class FoundFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DisplayMetrics dm=getResources().getDisplayMetrics();
        TextView textView = new TextView(getActivity());
        textView.setText("发现");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,6,dm));
        LinearLayout layout = new LinearLayout(getActivity());
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.addView(textView, params);
        return layout;
    }
}
