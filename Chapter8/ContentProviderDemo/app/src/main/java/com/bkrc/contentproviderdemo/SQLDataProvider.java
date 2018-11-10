package com.bkrc.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.bkrc.contentproviderdemo.utils.MyDBHelper;

import static com.bkrc.contentproviderdemo.utils.MyDBHelper.TABLE_NAME;

public class SQLDataProvider extends ContentProvider {

    private static final int ORDER_DIR = 0;

    private static final int ORDER_ITEM = 1;

    private static final int CUSTOMNAME_DIR = 2;

    private static final int CUSTOMNAME_ITEM = 3;

    private static final String AUTHORITY = "com.bkrc.contentprovider";

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);// 默认的规则是不匹配的

    private MyDBHelper dbHelper;

    static {
        URI_MATCHER.addURI(AUTHORITY, "order", ORDER_DIR);
        // 使用通配符#，匹配任意数字
        URI_MATCHER.addURI(AUTHORITY, "order/#", ORDER_ITEM);
    }

    public SQLDataProvider() {
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext());
        return true;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri u = null;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case ORDER_DIR:
                long ID = db.insert(TABLE_NAME, null, values);
                Log.e("TAG", "insert: " + ID);
                u = Uri.parse("content://" + AUTHORITY + "/order/" + ID);
                break;
            case ORDER_ITEM:
//                long ID = db.insert(TABLE_NAME, null, values);
//                Log.e("TAG", "insert: " + ID);
//                u = Uri.parse("content://" + AUTHORITY + "/order/" + ID);
                break;
        }
        return u;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRow = 0;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case ORDER_DIR:
                deleteRow = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case ORDER_ITEM:
                String ID = uri.getPathSegments().get(1);
                deleteRow = db.delete(TABLE_NAME, "id=?", new String[]{ID});
                break;
        }
        return deleteRow;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRow = 0;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case ORDER_DIR:
                updateRow = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case ORDER_ITEM:
                String ID = uri.getPathSegments().get(1);
                updateRow = db.update(TABLE_NAME, values, "id=?", new String[]{ID});
                break;
        }
        return updateRow;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case ORDER_DIR:
                // 查询table1 表中的所有数据
                cursor = db.query(TABLE_NAME, projection, selection, selectionArgs
                        , null, null, sortOrder);
                break;
            case ORDER_ITEM:
                // 查询 table1 表中的单条数据
                String ID = uri.getPathSegments().get(1);
                cursor = db.query(TABLE_NAME, projection, "id=?"
                        , new String[]{ID}, null, null, sortOrder);
                break;
        }
        Log.e("TAG", "--->>查询成功，Count=" + cursor.getCount());
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case CUSTOMNAME_DIR:
                return "vnd.android.cursor.dir/vnd.com.bkrc.contentprovider.order";
            case CUSTOMNAME_ITEM:
                return "vnd.android.cursor.item/vnd.com.bkrc.contentprovider.order";
        }
        return null;
    }
}
