package com.brkc.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
/**
 *
 * @作者 小白攻城狮
 *
 * @描述 普通广播接收器
 * @日期
 * @更新内容
 **/
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到普通广播", Toast.LENGTH_SHORT).show();
    }
}
