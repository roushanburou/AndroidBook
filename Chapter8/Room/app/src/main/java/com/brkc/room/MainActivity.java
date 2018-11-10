package com.brkc.room;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ArrayList<User> queryResult;

    private UserListAdapter adapter;

    User onSelectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Button insertButton = (Button) findViewById(R.id.insertButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button updateButton = (Button) findViewById(R.id.updateButton);
        Button query1Button = (Button) findViewById(R.id.query1Button);
        ListView listview = (ListView) findViewById(R.id.showDateListView);

        SQLBtnOnclickListener onclickListener = new SQLBtnOnclickListener();
        insertButton.setOnClickListener(onclickListener);
        deleteButton.setOnClickListener(onclickListener);
        updateButton.setOnClickListener(onclickListener);
        query1Button.setOnClickListener(onclickListener);

        adapter = new UserListAdapter(MainActivity.this, R.layout.show_user_item);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onSelectedUser = queryResult.get(position);
                Toast.makeText(MainActivity.this, "已选中 id: "+onSelectedUser.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        delete(queryResult.get(position));
                        query();
                    }
                }).start();
                return true;
            }
        });
    }

    public       class SQLBtnOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.insertButton:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            insertDate();
                            query();
                        }
                    }).start();
                    break;
                case R.id.deleteButton:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            delete(onSelectedUser);
                            query();
                        }
                    }).start();
                    break;
                case R.id.query1Button:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            query();
                        }
                    }).start();
                    break;
                case R.id.updateButton:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            update(onSelectedUser);
                            query();
                        }
                    }).start();
                    break;
            }
        }
    }

    @SuppressLint("HandlerLeak")
    Handler refreshHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.clear();
            adapter.addAll(queryResult);
            adapter.notifyDataSetChanged();
        }
    };

    private void insertDate() {
        User user = new User();
        user.setName("name1");
        user.setAge(18);
        UserDatabase.getInstance(MainActivity.this).getUserDao().insert(user);
    }

    private void delete(final User user) {
        UserDatabase.getInstance(MainActivity.this).getUserDao().delete(user);
    }

    private void update(final User user) {
        user.setName("哈哈大王");
        user.setAge(28);
        UserDatabase.getInstance(MainActivity.this).getUserDao().update(user);
    }

    private void query() {
        queryResult = (ArrayList<User>) UserDatabase
                .getInstance(MainActivity.this)
                .getUserDao()
                .getAllUsers();
        refreshHandler.sendEmptyMessage(1);
    }
}
