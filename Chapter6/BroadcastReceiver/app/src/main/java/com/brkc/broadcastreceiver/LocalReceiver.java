package com.brkc.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 *
 * @作者 小白攻城狮
 *
 * @描述 App应用内广播
 * @日期  
 * @更新内容 
 **/
public class LocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("LocalReceiver", "onReceive: App应用内广播");
    }
}
