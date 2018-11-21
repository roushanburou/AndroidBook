package com.brkc.okhttp.uitls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * @作者
 *
 * @描述 缓存工具类
 * @日期
 * @更新内容
 **/
public class CacheUtils {


    /**
     * 保持数据
     */
    public static  void putString(Context context,String key,String values){
        SharedPreferences sharedPreferences = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,values).commit();
    }

    /**
     * 得到缓存的数据
     */
    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return  sharedPreferences.getString(key,"");
    }




}
