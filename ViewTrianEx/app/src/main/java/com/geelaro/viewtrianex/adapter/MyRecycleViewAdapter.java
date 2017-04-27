package com.geelaro.viewtrianex.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geelaro.viewtrianex.R;

import java.util.List;

/**
 * Created by Lee on 2017/4/19.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private List<String> mData;
    public MyRecycleViewAdapter(List<String> data) {
        mData =  data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mLayout=LayoutInflater.from(parent.getContext());
        View view=mLayout.inflate(R.layout.item_recycle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mContentView.setText(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData!=null?mData.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mContentView;

        public ViewHolder(View itemView) {
            super(itemView);
            mContentView=(TextView)itemView.findViewById(R.id.tv_item);
        }
    }
}
