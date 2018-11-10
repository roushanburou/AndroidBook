package com.bkrc.contentproviderdemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "MyDBHelper";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "myTest.db";
    public static final String TABLE_NAME = "Orders";

    public MyDBHelper(Context context) {        //定义构造函数
        super(context,DB_NAME,null,DB_VERSION);  // 重写基类构造函数
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: ");
        String sql = "create table if not exists "
                + TABLE_NAME
                + "(Id integer primary key,CustomName text,OrderPrice integer,Country text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: ");
//        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
//        db.execSQL(sql);
//        onCreate(db);
    }
}
