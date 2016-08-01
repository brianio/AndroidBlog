package com.brianio.actionbarex;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ShareActionProvider;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    //
    private ShareActionProvider provider;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ACTGo");
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(false);

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
    public void onClick(View v) {

//        switch (v.getId()) {
//
//            case R.id.btn_first:
//                Intent intent = new Intent(this, FirstActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.start_service:
//                Intent startIntent = new Intent(this, MyService.class);
//                startService(startIntent);
//                break;
//            case R.id.stop_service:
//                Intent stopIntent = new Intent(this, MyService.class);
//                stopService(stopIntent);
//                break;
//            //开始这个连接
//            case R.id.bind_service:
//                Intent bindIntent = new Intent(this, MyService.class);
//                bindService(bindIntent, sConnection, BIND_AUTO_CREATE);
//                break;
//            //解开这个连接
//            case R.id.unbind_service:
//                unbindService(sConnection);
//                break;
//            default:
//                break;
//        }

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
//        provider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
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
