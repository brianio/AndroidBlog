package com.geelaro.wechatex;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by Brian on 2016/8/3.
 */
public class PlusActionProvider extends ActionProvider {
    /**
     * Creates a new instance. ActionProvider classes should always implement a
     * constructor that takes a single Context parameter for inflating from menu XML.
     *
     * @param context Context for accessing resources.
     */
    private Context context;
    public PlusActionProvider(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu){
        subMenu.clear();
        subMenu.add(context.getString(R.string.action_group_chat)).setIcon(R.mipmap.ofm_group_chat_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        subMenu.add(context.getString(R.string.action_add_friends)).setIcon(R.mipmap.ofm_add_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        subMenu.add(context.getString(R.string.action_video)).setIcon(R.mipmap.ofm_video_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        subMenu.add(R.string.action_scan).setIcon(R.mipmap.ofm_qrcode_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        subMenu.add(context.getString(R.string.action_card)).setIcon(R.mipmap.ofm_card_icon)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

    }

    @Override
    public boolean hasSubMenu(){
        return true;
    }

}
