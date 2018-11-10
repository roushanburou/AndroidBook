package com.bkrc.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bkrc.sqlite.sqlite.R;
import com.bkrc.sqlite.utils.MyDBHelper;
import com.bkrc.sqlite.utils.Order;
import com.bkrc.sqlite.utils.OrderListAdapter;

import java.util.ArrayList;

import static com.bkrc.sqlite.utils.MyDBHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // 查询结果
    ArrayList<Order> queryResult;
    // 列表适配器
    private OrderListAdapter adapter;
    // 列表
    ListView listview;
    // 创建MyDBHelper对象
    private MyDBHelper myDBHelper;
    // 操作数据库对象
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        refresh();
    }


    /**
     * 初始化
     */
    private void initView() {
        myDBHelper = new MyDBHelper(this);
        db = myDBHelper.getWritableDatabase();//初始化SQLiteDatabase对象
        Button insertButton = (Button) findViewById(R.id.insertButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button query1Button = (Button) findViewById(R.id.query1Button);
        listview = (ListView) findViewById(R.id.showDateListView);
        SQLBtnOnclickListener onclickListener = new SQLBtnOnclickListener();
        insertButton.setOnClickListener(onclickListener);
        deleteButton.setOnClickListener(onclickListener);
        updateButton.setOnClickListener(onclickListener);
        query1Button.setOnClickListener(onclickListener);
        adapter = new OrderListAdapter(MainActivity.this, R.layout.show_sql_item);
        listview.setAdapter(adapter);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String Id = Integer.toString(queryResult.get(position).id);
                delete(Id);
                queryResult.remove(position);
                refresh();
                return false;
            }
        });
    }


    public class SQLBtnOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.insertButton:
                    insertDate();
                    break;
                case R.id.deleteButton:
                    String Id = queryResult != null
                            && queryResult.size() > 0 ? Integer.toString(queryResult.get(0).id) : "0";
                    delete(Id);
                    break;
                case R.id.query1Button:
                    break;
                case R.id.updateButton:
                    update();
                    break;
            }
            refresh();
        }
    }

    private void refresh() {
        queryResult = query();
        adapter.clear();
        adapter.addAll(queryResult);
        adapter.notifyDataSetChanged();
    }

    /**
     * 新增数据
     */
    public void insertDate() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CustomName", "Peter");
        contentValues.put("OrderPrice", 700);
        contentValues.put("Country", "China");
        if (db.insert(TABLE_NAME, null, contentValues) == -1) {
            Toast.makeText(this, "主键重复", Toast.LENGTH_SHORT).show();
        }
        contentValues.clear();
    }

    /**
     * 删除
     * @param id 对应的ID号
     */
    private void delete(String id) {
        if (db.delete(TABLE_NAME, "Id=?", new String[]{id}) == 0) {
            Toast.makeText(MainActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 数据查询
     */
    public ArrayList<Order> query() {
        ArrayList<Order> list = new ArrayList<Order>();
        // Cursor cursor = db.rawQuery("select * from Orders ", new String[]{});
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        // 开启事务
        db.beginTransaction();
        if (cursor != null && cursor.getCount() > 0) {
            //判断cursor中是否存在数据
            while (cursor.moveToNext()) {
                Order bean = new Order();
                bean.id = cursor.getInt(0);
                bean.customName = cursor.getString(1);
                bean.orderPrice = cursor.getInt(2);
                bean.country = cursor.getString(3);
                list.add(bean);
            }
            // 设置事务执行成功，若成功，则sql语句生效，否则不生效
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        return list;
    }


    /**
     * 更新修改字段
     */
    private void update() {
        ContentValues values = new ContentValues();
        values.put("customName", "John");// 修改字段
        db.update(TABLE_NAME, values, "customName = 'Peter'", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        myDBHelper.close();
    }
}
