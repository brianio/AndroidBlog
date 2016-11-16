package com.geelaro.viewtrianex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.geelaro.viewtrianex.R;

/**
 * Created by Administrator on 2016/11/11.
 */

public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup holderGroup;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.grid_item_icon,parent,false);
            holderGroup=new ViewHolderGroup();
            holderGroup.tv_group=(TextView)convertView.findViewById(R.id.tv_group);
        }

        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private static class ViewHolderGroup {
        private TextView tv_group;
    }

    private static class ViewHolderItem {
        private ImageView img_icon;
        private TextView tv_name;
    }
}
