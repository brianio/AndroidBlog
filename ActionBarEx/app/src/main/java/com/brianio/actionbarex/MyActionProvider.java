package com.brianio.actionbarex;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ShareActionProvider;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MyActionProvider extends ShareActionProvider {

    public MyActionProvider(Context context){
        super(context);
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu){

        subMenu.clear();
        subMenu.add("subItem1").setIcon(R.drawable.ic_launcher).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        subMenu.add("subItem 2").setIcon(R.drawable.ic_launcher).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }
    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public boolean hasSubMenu(){
        return true;
    }
}
