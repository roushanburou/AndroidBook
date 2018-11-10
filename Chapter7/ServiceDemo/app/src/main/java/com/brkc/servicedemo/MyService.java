package com.brkc.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{

    private static final String TAG = "MyService";

    public MyService() {
    }

    // 当服务第一次被创建时，系统会调用本方法，用于执行一次性的配置
    // 工作（之前已调用过onStartCommand()或onBind()) 了。如果服务已经运行，
    // 则本方法就不会被调用。
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    // 当其它组件需要通过bindService()绑定服务时（比如执行RPC），系统会调用本方法。
    // 在本方法的实现代码中，你必须返回IBinder来提供一个接口，客户端用它来和服务进
    // 行通信。 你必须确保实现本方法，不过假如你不需要提供绑定，那就返回null即可。
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        // TODO: Return the communication channel to the service.
        return null;
    }

    // 当其它组件，比如一个activity，通过调用startService()请求started方式的服务时，
    // 系统将会调用本方法。 一旦本方法执行，服务就被启动，并在后台一直运行下去。
    // 如果你的代码实现了本方法，你就有责任在完成工作后通过调用stopSelf()或stopService()
    // 终止服务。（如果你只想提供bind方式，那就不需要实现本方法。）
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    // 解除绑定，当所有客户端与特定接口断开连接时调用
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    // 某客户端正用bindService()绑定到服务,而onUnbind()已经被调用过了。
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    // 当服务用不上了并要被销毁时，系统会调用本方法。 你的服务应该实现本方法来进行资源
    // 的清理工作，诸如线程、已注册的侦听器listener和接收器receiver等等。 这将是服务收
    // 到的最后一个调用。
    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
