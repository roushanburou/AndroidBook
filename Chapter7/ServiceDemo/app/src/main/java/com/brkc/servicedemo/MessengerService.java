package com.brkc.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

    static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "MessengerService";

    public MessengerService() {
    }

    /**
     * 用于接收从客户端传递过来的数据
     */
    class ReceiverHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Log.i(TAG, "thanks,Service had receiver message from client!");
                    //回复客户端信息,该对象由客户端传递过来
                    Messenger client = msg.replyTo;
                    //获取回复信息的消息实体
                    Message replyMsg = Message.obtain(null, MessengerService.MSG_SAY_HELLO);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "welcome,The service had receiver message from client! ");
                    replyMsg.setData(bundle);
                    //向客户端发送消息
                    try {
                        client.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * 创建Messenger并传入Handler实例对象
     */
    final Messenger mMessenger = new Messenger(new ReceiverHandler());

    /**
     * 当绑定Service时,该方法被调用,将通过mMessenger返回一个实现
     * IBinder接口的实例对象
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return mMessenger.getBinder();
    }
}
