package com.geelaro.wechatex;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.geelaro.wechatex.tabs.ChatFragment;
import com.geelaro.wechatex.tabs.ContactsFragment;
import com.geelaro.wechatex.tabs.FoundFragment;
import com.geelaro.wechatex.tabs.MeFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends FragmentActivity {

    //ChatFragment
    private ChatFragment chatFragment;
    //
    private ContactsFragment contactsFragment;
    //
    private FoundFragment foundFragment;
    //
    private MeFragment meFragment;
    //
    private PagerSlidingTabStrip pagerTabs;
    //屏幕密度
    private DisplayMetrics dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOverFlowShowAlways();
        dm=getResources().getDisplayMetrics();
        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        pagerTabs=(PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pagerTabs.setViewPager(pager);

        setTabsValue();
    }

    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
//        pagerTabs.setShouldExpand(true);
        // 设置Tab的分割线是透明的
//        pagerTabs.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        pagerTabs.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        pagerTabs.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2, dm));
        // 设置Tab标题文字的大小
        pagerTabs.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 14, dm));
        // 设置Tab Indicator的颜色
        pagerTabs.setIndicatorColor(Color.parseColor("#45c01a"));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        pagerTabs.setSelectedTextColor(Color.parseColor("#45c01a"));
        // 取消点击Tab时的背景色
        pagerTabs.setTabBackground(0);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = { "聊天","联系人","发现","我" };

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (chatFragment == null) {
                        chatFragment = new ChatFragment();
                    }
                    return chatFragment;
//
                case 1:
                    if (foundFragment == null) {
                        foundFragment = new FoundFragment();
                    }
                    return foundFragment;
                case 2:
                    if (contactsFragment==null){
                        contactsFragment = new ContactsFragment();
                    }
                    return contactsFragment;
                case 3:
                    if(meFragment==null){
                        meFragment=new MeFragment();
                    }
                    return meFragment;

                default:
                    return null;
            }
        }

    }
    //overflow一直显示
    public void setOverFlowShowAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //overflow内的显示图标
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_album:
                ShowToast.Short("Album");
                return true;
            case R.id.action_collection:
                ShowToast.Short("Collection");
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
