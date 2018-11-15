package com.hhl.weatherforecast.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 作者： 小白攻城狮 on 2017/10/17.
 * 作用： 检测网络连接状态
 * 修改： networkInfo 多加了一个判断条件 networkInfo.isConnected()
 */

public class CheckNet {
    public final static int NET_NONE = 0;
    public final static int NET_WIFI = 1;
    public final static int NET_MOBILE = 2;

    public static int getNetState(Context context)
    {

        ConnectivityManager connectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //
        if(networkInfo == null && !networkInfo.isConnected())
            return NET_NONE;
        int type = networkInfo.getType();
        if(type == ConnectivityManager.TYPE_MOBILE)
            return NET_MOBILE;
        else if(type == ConnectivityManager.TYPE_WIFI)
            return NET_WIFI;
        return NET_MOBILE;
    }
}
