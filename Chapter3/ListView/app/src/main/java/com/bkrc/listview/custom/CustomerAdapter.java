package com.bkrc.listview.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bkrc.listview.R;
import com.bkrc.listview.custom.Customer;

import java.util.List;

/**
 * 作者： 小白攻城狮 on 2018/9/21.
 * 作用：
 * 来源：
 */

class CustomerAdapter extends BaseAdapter{

    private List<Customer> listItems ;
    private Context context = null;
    public CustomerAdapter(Context context,List<Customer> listItems)
    {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }
    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Customer item = (Customer) getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lv_customer, parent, false);//LayoutInflater是用来找layout下xml布局文件，并且实例化

            viewHolder = new ViewHolder();
            //实例化
            viewHolder.iv = view.findViewById(R.id.item_id);
            viewHolder.textView = (TextView) view.findViewById(R.id.item_name);
            view.setTag(viewHolder);
        } else {
            //这里复用了ViewHolder
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.iv.setImageResource(item.getId());
        viewHolder.textView.setText(item.getName().toString());
        return view;
    }

    class ViewHolder {
        private ImageView iv;
        private TextView textView;

    }
}
