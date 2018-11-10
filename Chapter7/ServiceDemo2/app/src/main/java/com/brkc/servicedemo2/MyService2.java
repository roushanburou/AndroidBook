package com.brkc.servicedemo2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService2 extends Service {
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
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //创建Intent       
        Intent intent = new Intent(this, MyService2.class);
        intent.setAction("com.example.clock");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        //周期触发       
        manager.setRepeating(AlarmManager.RTC, 0, 5 * 1000, pendingIntent);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
