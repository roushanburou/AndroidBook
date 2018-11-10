package com.brkc.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service implements ServiceInterface{

    private static final String TAG = "MyBindService";
    private final IBinder mBinder = new LocalBinder();
    private boolean quit;
    private Thread thread;
    private int count;

    public MyBindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 每间隔一秒count加1 ，直到quit为true。
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        });
        thread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        this.quit = true;
        super.onDestroy();
    }

    public int getCount() {
        return count;
    }

    @Override
    public void seeMovie() {
        Log.e(TAG, "seeMovie: ");
    }

    @Override
    public void listenMusic() {
        Log.i(TAG, "listenMusic: ");
    }

    public class LocalBinder extends Binder {
        public MyBindService getService() {
            return MyBindService.this;
        }
    }
}
