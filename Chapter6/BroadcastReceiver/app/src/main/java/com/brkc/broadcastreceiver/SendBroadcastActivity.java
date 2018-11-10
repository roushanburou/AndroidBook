package com.brkc.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SendBroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);
    }

    // 普通广播
    public void sendBroadcast(View view) {
        Intent intent = new Intent("com.brkc.broadcastreceiver.MY_BROADCAST");
        sendBroadcast(intent);
    }

    // 有序广播
    public void sendOrderBroadcast(View view) {
        Intent intent = new Intent("com.brkc.broadcastreceiver.MY_BROADCAST");
        sendOrderedBroadcast(intent,null);
    }

    // 注册本地广播
    LocalBroadcastManager localBroadcastManager;
    LocalReceiver receiver;
    public void onRegisterNativeReceiver(View view) {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.brkc.broadcastreceiver.MY_BROADCAST");
        receiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(receiver,intentFilter);
    }
    // 解除本地广播
    public void onUnregisterNativeReceiver(View view) {
        if (localBroadcastManager != null)
            localBroadcastManager.unregisterReceiver(receiver);
    }
    // 发送本地广播
    public void sendNativeBroadcastReceiver(View view) {
        Intent intent = new Intent("com.brkc.broadcastreceiver.MY_BROADCAST");
        localBroadcastManager.sendBroadcast(intent);
    }

}
