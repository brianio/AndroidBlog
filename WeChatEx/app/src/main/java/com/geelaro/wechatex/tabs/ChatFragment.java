package com.geelaro.wechatex.tabs;

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

import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * Created by Brian on 2016/8/3.
 */
public class ChatFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DisplayMetrics dm=getResources().getDisplayMetrics();
        TextView textView = new TextView(getActivity());
        textView.setText("聊天");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.applyDimension(COMPLEX_UNIT_SP,6,dm));
        LinearLayout layout = new LinearLayout(getActivity());
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layout.addView(textView, params);
        return layout;
    }
}
