package com.geelaro.viewtrianex.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geelaro.viewtrianex.R;
import com.geelaro.viewtrianex.model.ListItemClickListener;
import com.geelaro.viewtrianex.utils.ColorUtils;

import java.util.List;

/**
 * Created by Lee on 2017/4/19.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    private static final String TAG = MyRecycleViewAdapter.class.getSimpleName();
    private List<String> mData;
    private static int viewHolderCount;

    private ListItemClickListener mItemClickListener;


    public MyRecycleViewAdapter(List<String> data, ListItemClickListener listener) {
        mData = data;
        mItemClickListener = listener;
        viewHolderCount = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mLayout = LayoutInflater.from(parent.getContext());
        View view = mLayout.inflate(R.layout.item_recycle, parent, false);

        MyRecycleViewAdapter.ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        int backgroudColorForViewHolder
                = ColorUtils.getViewHolderBackgroundColorFromInstance(parent.getContext(), viewHolderCount);

        viewHolder.itemView.setBackgroundColor(backgroudColorForViewHolder);

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mContentView;
        TextView viewHolderIndex;

        public ViewHolder(View itemView) {
            super(itemView);
            mContentView = (TextView) itemView.findViewById(R.id.tv_item);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_item_instance);
            itemView.setOnClickListener(this);
        }

        void bind(int index) {
            mContentView.setText(mData.get(index));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mItemClickListener.onClickListener(clickedPosition);
        }
    }
}
