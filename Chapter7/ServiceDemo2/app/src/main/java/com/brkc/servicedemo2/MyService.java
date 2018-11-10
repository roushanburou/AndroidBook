package com.brkc.servicedemo2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    private BroadcastReceiver resetReceiver;
    private IntentFilter mIF;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        resetReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Intent a = new Intent(MyService.this, MyService.class);
                startService(a);
            }
        };
        mIF = new IntentFilter();
        //自定义action
        mIF.addAction("com.brkc.servicedemo2.MyService");
        //注册广播接者
        registerReceiver(resetReceiver, mIF);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent();
        intent.setAction("com.brkc.servicedemo2.MyService");
        //发送广播
        sendBroadcast(intent);

        unregisterReceiver(resetReceiver);
    }
}
