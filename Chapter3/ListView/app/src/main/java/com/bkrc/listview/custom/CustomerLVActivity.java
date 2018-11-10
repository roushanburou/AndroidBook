package com.bkrc.listview.custom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bkrc.listview.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerLVActivity extends AppCompatActivity {
    ListView listView;
    CustomerAdapter customerAdapter;
    List<Customer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_lv);
        listView = findViewById(R.id.listview);
        //调用填充数据方法
        initList();
        //实现自定义适配器
        customerAdapter = new CustomerAdapter(this,list);
        //指定listview控件的适配器
        listView.setAdapter(customerAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CustomerLVActivity.this, "name：" + list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 填充数据
     */
    public void initList()
    {
        String[] arr = new String[]{"China","America","Japan","Italian","Asia"};
        int[] id = new int[]{R.drawable.china,R.drawable.america,R.drawable.japan,R.drawable.italian,R.drawable.asia};
        for (int i = 0; i < arr.length; i++){
            Customer customer = new Customer(id[i],arr[i]);
            list.add(customer);
        }
    }
}
