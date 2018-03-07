package com.geelaro.customview.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.customview.custom.bezier.Bezier;
import com.geelaro.customview.RadarView;
import com.geelaro.customview.custom.bezier.Bezier3;
import com.geelaro.customview.pie.PieData;
import com.geelaro.customview.pie.PieView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LEE on 2018/1/29.
 */

public class FragmentView extends Fragment {

    private int viewNum;

    public static FragmentView newInstance(int view) {
        FragmentView fv = new FragmentView();
        Bundle bundle = new Bundle();
        bundle.putInt("view", view);
        fv.setArguments(bundle);
        return fv;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewNum = getArguments().getInt("view");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
        if (viewNum == 1) {
            PieView view = new PieView(getActivity());
            List<PieData> list = new ArrayList<>();
            PieData pd1 = new PieData("36Kr", 30);
            PieData pd2 = new PieData("Zhihu", 60);
            PieData pd3 = new PieData("Guoke", 40);
            PieData pd4 = new PieData("Douban", 80);
            PieData pd5 = new PieData("QQ", 120);

            list.add(pd1);
            list.add(pd2);
            list.add(pd3);
            list.add(pd4);
            list.add(pd5);

           view.setData(list);
           return view;
        }else {
            return getView(viewNum);
        }

    }

    public View getView(int viewId) {
        View view;
        switch (viewId) {
            case 0:
                view = new RadarView(getActivity());
                break;
//            case 1:
//                view = new PieView(getActivity());
//                break;
            case 2:
                view = new Bezier(getActivity());
                break;
            case 3:
                view = new Bezier3(getActivity());
                break;
            default:
                throw new IllegalArgumentException("ViewId 错误");

        }
        return view;
    }
}
