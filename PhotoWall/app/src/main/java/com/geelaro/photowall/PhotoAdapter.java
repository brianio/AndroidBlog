package com.geelaro.photowall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by LEE on 2018/1/21.
 */

public class PhotoAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {
    private GridView mPhotoWall;
    private Set<BitmapDownloadTask> taskCollections;

    private LruCache<String, Bitmap> mMemoryCache;

    private boolean isFirstEnter = true; //是否首次进入程序

    private int mFirstVisibleItem;
    private int mVisibleItemCount;


    public PhotoAdapter(Context context, GridView photoWall, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        mPhotoWall = photoWall;
        taskCollections = new HashSet<>();
        //获取app最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        //设置图片缓存大小为最大可用内存1/8
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };

        mPhotoWall.setOnScrollListener(this);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String url = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);
        } else {
            view = convertView;
        }
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setTag(url);  //给image一个tag,保证异步加载不会乱。
        setImageView(url, imageView);
        return view;
    }


    private void setImageView(String url, ImageView imageView) {
        Bitmap bitmap = getBitmapFromMemoryCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher_round);
        }
    }

    private Bitmap getBitmapFromMemoryCache(String key) {
        return mMemoryCache.get(key);
    }

    private void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        mMemoryCache.put(key, bitmap);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //只有当GridView静止时才会去下载图片，滑动时取消下载。
        if (scrollState == SCROLL_STATE_IDLE) {
            loadBitmapResources(mFirstVisibleItem, mVisibleItemCount);
        } else {
            cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
        mVisibleItemCount = visibleItemCount;
        Log.d("PhotoWall", "onScroll: first:"+firstVisibleItem+",visible:"+visibleItemCount+",total:"+totalItemCount);
        //下载程序应该在onScrollStateChanged,但首次进入程序不用调用onScrollStateChanged
        //因此首次进入程序在此下载图片
        if (isFirstEnter && visibleItemCount > 0) {
            loadBitmapResources(firstVisibleItem, visibleItemCount);
            isFirstEnter = false;
        }
    }


    private void loadBitmapResources(int firstVisibleItem, int visibleItemCount) {

        for (int i = firstVisibleItem; i < firstVisibleItem + visibleItemCount; i++) {
            String imageUrl = Images.imageThumbUrls[i];
            Bitmap bitmap = getBitmapFromMemoryCache(imageUrl);
            if (bitmap == null) {
                BitmapDownloadTask task = new BitmapDownloadTask();
                taskCollections.add(task);
                task.execute(imageUrl);
            } else {
                ImageView imageView = mPhotoWall.findViewWithTag(imageUrl);
                if (imageView != null && bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }

        }

    }

    public void cancelAllTask() {
        if (taskCollections != null) {
            for (BitmapDownloadTask task : taskCollections) {
                task.cancel(false);
            }
        }
    }


    @SuppressLint("StaticFieldLeak")
    class BitmapDownloadTask extends AsyncTask<String, Void, Bitmap> {

        String imageUrl;

        @Override
        protected Bitmap doInBackground(String... params) {
            imageUrl = params[0];
            Bitmap bitmap = downloadBitmap(params[0]);
            Log.d("Wall", "doInBackground: " + imageUrl);
            if (bitmap != null) {
                addBitmapToMemoryCache(params[0], bitmap);
                Log.d("Wall", "doInBackground: " + bitmap.getHeight());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = mPhotoWall.findViewWithTag(imageUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            taskCollections.remove(this);
        }

        private Bitmap downloadBitmap(String imageUrl) {
            Bitmap bitmap = null;
            HttpURLConnection conn = null;

            try {
                URL url = new URL(imageUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setReadTimeout(10 * 1000);

                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return bitmap;
        }
    }
}
