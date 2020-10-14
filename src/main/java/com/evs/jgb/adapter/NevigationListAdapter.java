package com.evs.jgb.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evs.jgb.R;

import java.util.LinkedHashMap;

public class NevigationListAdapter extends BaseAdapter {

    private LayoutInflater inflater = null;
    private Context context;
    private LinkedHashMap<String, Integer> data;

    public NevigationListAdapter(Context a, LinkedHashMap<String, Integer> data) {
        context = a;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.keySet().toArray()[ position ];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        TextView textView_list_item;//, count;
        ImageView icon;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_adapter_item, null);
            holder = new ViewHolder();

            holder.textView_list_item = (TextView) convertView.findViewById(R.id.textView_list_item);
            holder.icon = (ImageView) convertView.findViewById(R.id.imageView_icons);
//            holder.count = (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView_list_item.setText((""+data.keySet().toArray()[ position ]));
        Glide.with(context).load(data.get((data.keySet().toArray())[ position ])).dontAnimate().into(holder.icon);
//        icon.setImageResource(icons.get(position));

        return convertView;
    }

    public int getCounter() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("registerData", Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt("TotalNotifications", 0);
        Log.e("Counter: person_id", " = " + count);
        return count;
    }


}
