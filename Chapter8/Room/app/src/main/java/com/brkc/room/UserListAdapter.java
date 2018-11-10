package com.brkc.room;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class UserListAdapter extends ArrayAdapter<User> {

    private final LayoutInflater mInflater;
    private int mResource;

    private View view;

    public UserListAdapter(Context context, int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    // 系统调用此方法，获取要显示至ListView的View对象
    // position:是return的View对象所对应的数据在集合中的位置
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = mInflater.inflate(mResource, parent, false);

        TextView id = (TextView) view.findViewById(R.id.id_tv);
        TextView name = (TextView) view.findViewById(R.id.name_tv);
        TextView age = (TextView) view.findViewById(R.id.age_tv);

        User user = getItem(position);
        id.setText(Integer.toString(user.getId()));
        name.setText(user.getName());
        age.setText(Integer.toString(user.getAge()));

        return view;
    }
}
