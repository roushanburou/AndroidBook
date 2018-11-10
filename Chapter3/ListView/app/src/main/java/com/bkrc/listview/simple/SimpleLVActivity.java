package com.bkrc.listview.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bkrc.listview.R;

public class SimpleLVActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;//创建ArrayAdapter对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_lv);
        //实例化ListView控件
        listView = findViewById(R.id.listview);
        //定义字符串数组，用来存储列表项信息
        String[] arr = new String[]{"China","america","japan","italian","Asia"};
        //使用字符串数组初始化 ArrayAdapter 对象
        arrayAdapter = new ArrayAdapter<String>(SimpleLVActivity.this,
                android.R.layout.simple_list_item_1,arr);
        listView.setAdapter(arrayAdapter);//将适配器与ListView相关联，为ListView列表设置数据源

        //listview中item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(SimpleLVActivity.this, result+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
