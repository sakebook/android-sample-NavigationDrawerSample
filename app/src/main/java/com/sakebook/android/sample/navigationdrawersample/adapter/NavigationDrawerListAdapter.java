package com.sakebook.android.sample.navigationdrawersample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakebook.android.sample.navigationdrawersample.R;
import com.sakebook.android.sample.navigationdrawersample.model.NavigationItem;

import java.util.List;

/**
 * Created by sakemotoshinya on 15/02/11.
 */
public class NavigationDrawerListAdapter extends ArrayAdapter<NavigationItem>{
    private List<NavigationItem> mItems;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mResource;

    public NavigationDrawerListAdapter(Context context, int resource, List<NavigationItem> objects) {
        super(context, resource, objects);
        this.mItems = objects;
        this.mContext = context;
        this.mResource = resource;
        this.mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public NavigationItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.mIconImage.setImageResource(getItem(position).getIconDrawableId());
        holder.mNameText.setText(getItem(position).getListName());
        holder.mNotificationCountText.setText(getItem(position).getNoftiifcationCount()+"");

        return convertView;
    }

    private static class ViewHolder {
        private ImageView mIconImage;
        private TextView mNameText;
        private TextView mNotificationCountText;

        public ViewHolder(View view) {
            mIconImage = (ImageView)view.findViewById(R.id.image_navigation_icon);
            mNameText = (TextView)view.findViewById(R.id.text_navigation_name);
            mNotificationCountText = (TextView)view.findViewById(R.id.text_navigation_notification);
        }
    }
}
