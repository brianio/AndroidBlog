package com.geelaro.viewtrianex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geelaro.viewtrianex.R;
import com.geelaro.viewtrianex.data.App;
import com.geelaro.viewtrianex.data.Book;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/2.
 */

public class MutiLayoutAdapter extends BaseAdapter {
    //定义两个类别标志
    private final static int TYPE_APP = 0;
    private final static int TYPE_BOOK = 1;
    private Context mContext;
    private ArrayList<Object> mData = null;

    public MutiLayoutAdapter(Context context,ArrayList<Object> data){
        this.mContext=context;
        this.mData=data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof App) {
            return TYPE_APP;
        } else if (mData.get(position) instanceof Book) {
            return TYPE_BOOK;
        } else {
            super.getItemViewType(position);
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        aViewHolder aHolder = null;
        bViewHolder bHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_APP:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_app, parent, false);
                    aHolder = new aViewHolder();

                    aHolder.img_icon = (ImageView) convertView.findViewById(R.id.imgtou_app);
                    aHolder.text_aName = (TextView) convertView.findViewById(R.id.name_app);
                    convertView.setTag(R.id.Tag_APP, aHolder);
                    break;
                case TYPE_BOOK:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.list_book, parent, false);
                    bHolder = new bViewHolder();

                    bHolder.text_bName = (TextView) convertView.findViewById(R.id.name_book);
                    bHolder.text_bAuthor = (TextView) convertView.findViewById(R.id.author_book);
                    convertView.setTag(R.id.Tag_BOOK, bHolder);
                    break;
            }

        } else {
            switch (type) {
                case TYPE_APP:
                    aHolder = (aViewHolder) convertView.getTag(R.id.Tag_APP);
                    break;
                case TYPE_BOOK:
                    bHolder = (bViewHolder) convertView.getTag(R.id.Tag_BOOK);
                    break;
            }
        }
        Object obj = mData.get(position);
        switch (type) {
            case TYPE_APP:
                App app = (App) obj;
                if (app != null) {
                    aHolder.img_icon.setImageResource(app.getaIcon());
                    aHolder.text_aName.setText(app.getaName());
                }
                break;
            case TYPE_BOOK:
                Book book=(Book)obj;
                if(book!=null){
                    bHolder.text_bName.setText(book.getbName());
                    bHolder.text_bAuthor.setText(book.getbAuthor());
                }
                break;
        }

        return convertView;
    }

    //两个不同的ViewHolder
    private static class aViewHolder {
        ImageView img_icon;
        TextView text_aName;
    }

    private static class bViewHolder {
        TextView text_bName;
        TextView text_bAuthor;
    }

}
