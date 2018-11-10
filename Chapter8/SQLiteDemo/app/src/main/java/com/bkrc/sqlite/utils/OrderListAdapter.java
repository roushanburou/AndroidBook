package com.bkrc.sqlite.utils;

import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.bkrc.sqlite.sqlite.R;

public class OrderListAdapter extends ArrayAdapter<Order> {

    private final LayoutInflater mInflater;
    private int mResource;

    public OrderListAdapter(Context context, int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    // 系统调用此方法，获取要显示至ListView的View对象
// position:是return的View对象所对应的数据在集合中的位置
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mResource, parent, false);

        TextView id = (TextView) convertView.findViewById(R.id.id_tv);
        TextView custom = (TextView) convertView.findViewById(R.id.custom_tv);
        TextView country = (TextView) convertView.findViewById(R.id.country_tv);
        TextView order = (TextView) convertView.findViewById(R.id.order_tv);

        Order order1 = getItem(position);
        id.setText(order1.id + "");
        custom.setText(order1.customName);
        country.setText(order1.orderPrice + "");
        order.setText(order1.country);
        return convertView;
    }

}
