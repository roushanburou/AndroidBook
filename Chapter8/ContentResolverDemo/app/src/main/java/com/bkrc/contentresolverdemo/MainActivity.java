package com.bkrc.contentresolverdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String newID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public List<String> onReadPhone(View view) {
        List<String> contactsList = new ArrayList<>();
        Cursor cursor = getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(name + "  :  " + number);
                Log.e(contactsList.size()+"", name + "  :  " + number);
            }
            cursor.close();
        }
        return contactsList;
    }

    public void insert(View view) {
        Uri uri = Uri.parse("content://com.bkrc.contentprovider/order");
        ContentValues values = new ContentValues();
        values.put("CustomName", "Peter");
        values.put("OrderPrice", 700);
        values.put("Country", "China");
        Uri nUri = getContentResolver().insert(uri,values);
        newID = nUri.getPathSegments().get(1);
    }

    public void delete(View view) {
        Uri uri = Uri.parse("content://com.bkrc.contentprovider/order/" + newID);
        getContentResolver().delete(uri,null,null);
    }

    public void update(View view) {
        Uri uri = Uri.parse("content://com.bkrc.contentprovider/order");
        ContentValues values = new ContentValues();
        values.put("customName", "John");// 修改字段
        getContentResolver().update(uri, values, "customName = 'Peter'", null);
    }

    public void query(View view) {
        ArrayList<Order> list = new ArrayList<Order>();
        Uri uri = Uri.parse("content://com.bkrc.contentprovider/order");
        Cursor cursor = getContentResolver()
                .query(uri,null,null,null,null);
        if (cursor != null && cursor.getCount() > 0) {
            //判断cursor中是否存在数据
            while (cursor.moveToNext()) {
                Order bean = new Order();
                bean.id = cursor.getInt(cursor.getColumnIndex("id"));
                bean.customName = cursor.getString(cursor.getColumnIndex("customName"));
                bean.orderPrice = cursor.getInt(cursor.getColumnIndex("orderPrice"));
                bean.country = cursor.getString(cursor.getColumnIndex("country"));
                list.add(bean);
                Log.e(list.size()+"", "query: " + bean.toString());
            }
            newID = list.size() + "";
            cursor.close();
        }
    }
}
