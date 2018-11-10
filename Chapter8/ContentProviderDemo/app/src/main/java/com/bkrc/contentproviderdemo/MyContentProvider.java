package com.bkrc.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 描述：添加类的描述
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/14
 * @update 添加更新的内容
 */
public class MyContentProvider extends ContentProvider{

    public static final int TABLE1_DIR = 0;

    public static final int TABLE1_ITEM = 1;

    public static final int TABLE2_DIR = 2;

    public static final int TABLE2_ITEM = 3;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);// 默认的规则是不匹配的

    // 往UriMatcher中添加匹配规则。注意，这里面的url不要写错了，我就是因为写错了，半天没调试出来。哎···
    static {
        URI_MATCHER.addURI("com.bkrc.contentproviderdemo", "table1", TABLE1_DIR);
        // 使用通配符#，匹配任意数字
        URI_MATCHER.addURI("com.bkrc.contentproviderdemo", "table1/#", TABLE1_ITEM);
        URI_MATCHER.addURI("com.bkrc.contentproviderdemo", "table2", TABLE2_DIR);
        URI_MATCHER.addURI("com.bkrc.contentproviderdemo", "table2/#", TABLE2_ITEM);
    }

    // 功能：初始化内容提供器的时候调用：
    // 参数描述：通常会在这里完成对数据库的创建和升级等操作，返回true表
    //           示内容提供器初始化成功,返回false则表示失败。注意,只有当
    //           存在ContentResolver尝试!访问我们程序中的数据时,内容提供
    //           器才会被初始化。
    @Override
    public boolean onCreate() {
        return false;
    }

    // 功能：从内容提供器中查询数据：
    // 参数描述：使用uri参数来确定查询哪张表, projection参数用于确定查,询
    //           哪些列, selection和selectionArgs参数用于约束查询哪些行,
    //           sortOrder参数用于对结果进行排序,查询的结果存放在Cursor对象中返回。
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        int flag = URI_MATCHER.match(uri);
        switch (flag) {
            case TABLE1_DIR:
                // 查询table1 表中的所有数据
                break;
            case TABLE1_ITEM:
                // 查询 table1 表中的单条数据
                break;
            case TABLE2_DIR:
                // 查询table2 表中的所有数据
                break;
            case TABLE2_ITEM:
                // 查询 table2 表中的单条数据
                break;
        }
        Log.e("TAG", "--->>查询成功，Count=" + cursor.getCount());
        return cursor;
    }


    // 功能：根据传入的内容URI来返回相应的MIME类型。
    // 参数描述：
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case TABLE1_DIR :
                return "vnd.android.cursor.dir/vnd.com.bkrc.contentproviderdemo.tablel";
            case TABLE1_ITEM :
                return "vnd.android.cursor.item/vnd.com.bkrc.contentproviderdemo.tablel";
            case TABLE2_DIR :
                return "vnd.android.cursor.dir/vnd.com.bkrc.contentproviderdemo.table2";
            case TABLE2_ITEM :
                return "vnd.android.cursor.item/vnd.com.bkrc.contentproviderdemo.table2";
        }
        return null;
    }

    // 功能：向内容提供器中添加一条数据。
    // 参数描述：使用uri参数来确定要添加到的表,待添加的数据保存在 values参
    //           数中。添加完成后,返回一个用于表示这条新记录的URI。
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    // 功能：从内容提供器中删除数据。
    // 参数描述：使用uri参数来确定删除哪一张表中的数据, selection和
    //           selectionArgs参数用于约束删除哪些行,被删除的行数将作为返回值返回。
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    // 功能：更新内容提供器中已有的数据。
    // 参数描述：使用uri参数来确定更新哪一张表中的数据,新数据保存在,
    //           values参数中, selection和selectionArgs参数用于约束更新哪些
    //           行,受影响的行数将作为返回值返回。
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
