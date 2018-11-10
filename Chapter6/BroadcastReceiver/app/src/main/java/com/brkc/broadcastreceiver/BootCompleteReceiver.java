package com.brkc.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 *
 * @作者 小白攻城狮
 *
 * @描述 
 * @日期  
 * @更新内容 
 **/
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hello Android！！！", Toast.LENGTH_LONG).show();
    }
}
