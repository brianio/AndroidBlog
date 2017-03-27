package com.brianio.actionbarex;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;


public class MainActivity extends Activity {

    //
    private ShareActionProvider provider;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ACBGo");
//        setContentView(R.layout.activity_main);

        ActionBar actionBar=getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        Log.d(TAG, "MAIN thread id is :" + Thread.currentThread().getId());
        Log.d(TAG, "Process id is " + Process.myPid());

        //添加Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab= actionBar
                .newTab()
                .setText(R.string.artist)
                .setTabListener(new TabListener<ArtistFragment>(this,"artist",ArtistFragment.class));

        actionBar.addTab(tab);

        ActionBar.Tab tab1=actionBar
                .newTab()
                .setText(R.string.album)
                .setTabListener(new TabListener<AlbumFragment>(this,"album",AlbumFragment.class));
        actionBar.addTab(tab1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

//        MenuItem itemSearch=(MenuItem)findViewById(R.id.search);
//        itemSearch.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                Log.d(TAG, "onSearch Expand ");
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                Log.d(TAG, "onSearch Collapse ");
//                return true;
//            }
//        });
        MenuItem itemShare = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) itemShare.getActionProvider();
        provider.setShareIntent(getDefaultIntent());


        return super.onCreateOptionsMenu(menu);
    }

    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                Toast.makeText(this, "Del", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
//                Intent intent = new Intent(this, FirstActivity.class);
//                startActivity(intent);
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}
