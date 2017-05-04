package com.geelaro.fragmentwork;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.geelaro.fragmentwork.tabs.ChartFragment;
import com.geelaro.fragmentwork.tabs.ContactsFragment;
import com.geelaro.fragmentwork.tabs.NewsFragment;
import com.geelaro.fragmentwork.tabs.SettingsFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //TAG
    private static final String TAG = MainActivity.class.getSimpleName();
    //展示聊天界面
    private ChartFragment chartFragment;
    //联系人界面
    private ContactsFragment contactsFragment;
    //动态界面
    private NewsFragment newsFragment;
    //设置界面
    private SettingsFragment settingsFragment;
    //聊天界面布局
    private View chartLayout;
    //联系人界面布局
    private View contactsLayout;
    //动态界面布局
    private View newsLayout;
    //设置界面布局
    private View settingsLayout;
    //聊天界面tab图标
    private ImageView chartImage;
    //联系人界面tab图标
    private ImageView contactsImage;
    //动态界面tab图标
    private ImageView newsImage;
    //设置界面tab图标
    private ImageView settingsImage;
    //聊天界面tab标题
    private TextView chartText;
    //联系人界面tab标题
    private TextView contactsText;
    //动态界面tab标题
    private TextView newsText;
    //设置界面tab标题
    private TextView settingsText;
    //聊天tab下标
    private static final int CODE_CHART_FRAGMENT = 0;
    //联系人tab下标
    private static final int CODE_CONTACTS_FRAGMENT = 1;
    //动态tab下标
    private static final int CODE_NEWS_FRAGMENT = 2;
    //设置tab下标
    private static final int CODE_SETTINGS_FRAGMENT = 3;

    //fragment管理
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ll_activity_main);
        //初始化布局元素
        initViews();
        fragmentManager=getFragmentManager();
        //初始界面为聊天
        setTabsSelection(CODE_CHART_FRAGMENT);

    }

    public void initViews() {
        chartImage = (ImageView) findViewById(R.id.img_chart);
        contactsImage = (ImageView) findViewById(R.id.contacts_image);
        newsImage = (ImageView) findViewById(R.id.img_news);
        settingsImage = (ImageView) findViewById(R.id.img_settings);

        chartText = (TextView) findViewById(R.id.text_chart);
        contactsText = (TextView) findViewById(R.id.contacts_text);
        newsText = (TextView) findViewById(R.id.text_news);
        settingsText = (TextView) findViewById(R.id.text_settings);

        chartLayout = findViewById(R.id.chart_layout);
        contactsLayout = findViewById(R.id.contacts_layout);
        newsLayout = findViewById(R.id.news_layout);
        settingsLayout = findViewById(R.id.settings_layout);

        chartLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingsLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chart_layout:
                setTabsSelection(CODE_CHART_FRAGMENT);
                break;
            case R.id.contacts_layout:
                setTabsSelection(CODE_CONTACTS_FRAGMENT);
                break;
            case R.id.news_layout:
                setTabsSelection(CODE_NEWS_FRAGMENT);
                break;
            case R.id.settings_layout:
                setTabsSelection(CODE_SETTINGS_FRAGMENT);
                break;
        }
    }

    /**
     * @param index 每个tab对应的下标。0是聊天，1是联系人，2是动态，3是设置
     */
    private void setTabsSelection(int index) {
        //每次选中之前先清除上次的状态
        clearSelection();
        //开启一个事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //隐藏所有fragment的，防止有多个fragment存在
        hideFragment(transaction);
        switch (index) {
            case CODE_CHART_FRAGMENT:
                //选择聊天tab时，改变了控件的图片和文字颜色
                chartImage.setImageResource(R.mipmap.message_selected);
                chartText.setTextColor(Color.WHITE);
                if (chartFragment == null){
                    //如果chartFragment为空，则创建一个并添加到界面上
                    chartFragment=new ChartFragment();
                    transaction.add(R.id.content,chartFragment);
                }else {
                    //如果不为空，则把它显示在界面上
                    transaction.show(chartFragment);
                }
                break;
            case CODE_CONTACTS_FRAGMENT:
                //选择联系人tab时，改变了控件的图片和文字颜色
                contactsImage.setImageResource(R.mipmap.contacts_selected);
                contactsText.setTextColor(Color.WHITE);
                if (contactsFragment==null){
                    //如果contactsFragment为空，则创建一个并添加到界面上
                    contactsFragment=new ContactsFragment();
                    transaction.add(R.id.content,contactsFragment);
                } else {
                    //如果不为空，则把它显示在界面上
                    transaction.show(contactsFragment);
                }
                break;
            case CODE_NEWS_FRAGMENT:
                //选择动态tab时，改变了控件的颜色和文字颜色
                newsImage.setImageResource(R.mipmap.news_selected);
                newsText.setTextColor(Color.WHITE);
                if (newsFragment==null){
                    //如果newsFragment为空，则创建一个并把它显示在界面上
                    newsFragment=new NewsFragment();
                    transaction.add(R.id.content,newsFragment);
                } else {
                    //如果不为空，则把它显示出来
                    transaction.show(newsFragment);
                }
                break;
            case CODE_SETTINGS_FRAGMENT:
                //选择设置tab时，改变了控件的图片和文字颜色
                settingsImage.setImageResource(R.mipmap.setting_selected);
                settingsText.setTextColor(Color.WHITE);
                if (settingsFragment==null){
                    //如果settingsFragment为空，则创建一个并把它显示在界面上
                    settingsFragment=new SettingsFragment();
                    transaction.add(R.id.content,settingsFragment);
                }else {
                    //如果不为空，则把它显示出来
                    transaction.show(settingsFragment);
                }
                break;
        }
        transaction.commit();//
    }

    /**
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (chartFragment != null) {
            transaction.hide(chartFragment);
        }
        if (contactsFragment != null) {
            transaction.hide(contactsFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (settingsFragment != null) {
            transaction.hide(settingsFragment);
        }
    }

    /**
     * 清除选中的状态
     */
    private void clearSelection() {
        chartImage.setImageResource(R.mipmap.message_unselected);
        chartText.setTextColor(Color.parseColor("#82858b"));
        contactsImage.setImageResource(R.mipmap.contacts_unselected);
        contactsText.setTextColor(Color.parseColor("#82858b"));
        newsImage.setImageResource(R.mipmap.news_unselected);
        newsText.setTextColor(Color.parseColor("#82858b"));
        settingsImage.setImageResource(R.mipmap.setting_unselected);
        settingsText.setTextColor(Color.parseColor("#82858b"));
    }
}

