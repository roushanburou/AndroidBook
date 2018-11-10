package com.brkc.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 描述：监听网络状态
 *
 * @author 小白攻城狮
 * @e-mail 635032648@qq.com
 * @time Created on 2018/10/8
 * @update 添加更新的内容
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //connectivityManger是一个系统服务类，专门用于管理网络连接
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // 调用 NetworkInfo 的 isAvailable()方法判断是否联网
        if(networkInfo != null && networkInfo.isAvailable()){
            Toast.makeText(context,"网络已连接",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"网络不可用",Toast.LENGTH_SHORT).show();
        }
    }
}
