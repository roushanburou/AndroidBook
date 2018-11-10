package com.brkc.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 启动服务
    public void onStartService(View view) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }
    // 停止服务
    public void onStopService(View view) {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    MyBindService myBindService;
    private ServiceConnection mConnection = new ServiceConnection() {
        /**
         * 与服务器端交互的接口方法 绑定服务的时候被回调，在这个方法获取绑定Service传递过来的IBinder对象，
         * 通过这个IBinder对象，实现宿主和Service的交互。
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBindService.LocalBinder binder = (MyBindService.LocalBinder) service;
            myBindService = binder.getService();
            //调用服务中的方法
            myBindService.seeMovie();
            myBindService.listenMusic();
        }

        /**
         * 当取消绑定的时候被回调。但正常情况下是不被调用的，它的调用时机是当Service服务被意外销毁时，
         * 例如内存的资源不足时这个方法才被自动调用。
         */
        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };
    // 绑定服务
    public void onBindService(View view) {
        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }
    // 解绑服务
    public void onUnbindService(View view) {
        unbindService(mConnection);
    }
    // 得到服务运行的时间
    public void onGetCount(View view) {
        if (myBindService != null)
            ((Button)view).setText("当前运行的时间为：" + myBindService.getCount());
    }

    private Messenger messService;
    private ServiceConnection messengerConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messService = new Messenger(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };
    // 绑定远程服务
    public void onBindMessengerService(View view) {
        Intent intent = new Intent(this,MessengerService.class);
        bindService(intent,messengerConn,Context.BIND_AUTO_CREATE);
    }
    // 解绑远程服务
    public void onUnbindMessengerService(View view) {
        unbindService(messengerConn);
        messService = null;
    }
    // 发送数据
    public void onData2MessengerService(View view) {
        if (messService == null) return;
        // 创建与服务交互的消息实体Message
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
        // 把接收服务器端回复的Messenger通过Message的replyTo参数传递给服务端
        msg.replyTo = mRecevierReplyMsg;
        try {
            //发送消息
            messService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    // 用于接收服务器返回的信息
    private Messenger mRecevierReplyMsg = new Messenger(new ReceiverReplyMsgHandler());

    private class ReceiverReplyMsgHandler extends Handler {
        private static final String TAG = "ReceiverReplyMsgHandler";

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //接收服务端回复
                case MessengerService.MSG_SAY_HELLO:
                    Log.i(TAG, "receiver message from service:" + msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
