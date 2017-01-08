package com.huang.greendaodemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huang.greendaodemo.R;
import com.huang.greendaodemo.bean.User;

import java.util.List;

/**
 * Created by huang-pc on 2016/12/31.
 */

public class MyAdapter extends BaseAdapter {

    private List<User> userList;
    private Context context;

    public MyAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }


    @Override
    public int getCount() {
        return userList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.listview_item_id);//id
            holder.name = (TextView) convertView.findViewById(R.id.listview_item_name);//名称
            holder.text = (TextView) convertView.findViewById(R.id.listview_item_text);//文本
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 设置资源
        holder.id.setText(userList.get(position).getId() + "");
        holder.name.setText(userList.get(position).getName());
        holder.text.setText(userList.get(position).getText());

        return convertView;
    }

    class ViewHolder {
        TextView id, name, text;
    }
}