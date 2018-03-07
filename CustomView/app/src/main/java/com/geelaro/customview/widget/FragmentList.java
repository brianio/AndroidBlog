package com.geelaro.customview.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geelaro.customview.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LEE on 2018/1/29.
 */

public class FragmentList extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_list, container, false);

        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);
        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);

        return view;

    }

    private void setupViewPager(ViewPager viewPager) {
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getChildFragmentManager());
        customPagerAdapter.addFragment(FragmentView.newInstance(0),"CustomView");
        customPagerAdapter.addFragment(FragmentView.newInstance(1),"PeiView");
        customPagerAdapter.addFragment(FragmentView.newInstance(2),"Bezier2");
        customPagerAdapter.addFragment(FragmentView.newInstance(3),"Bezier3");

        viewPager.setAdapter(customPagerAdapter);
    }

    public static class CustomPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fmList = new ArrayList<>();
        List<String> fmTitles = new ArrayList<>();

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            fmList.add(fragment);
            fmTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fmList.get(position);
        }

        @Override
        public int getCount() {
            return fmList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fmTitles.get(position);
        }
    }
}
